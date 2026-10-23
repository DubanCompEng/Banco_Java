/*

    [REQUISITO: Package],  [REQUISITO: Menu-Scanner]
*/


package Vista;

import Controlador.Banco;
import Modelo.Cliente;
import Modelo.Cuentas.Cuenta;
import Modelo.enums.TipoCuenta;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.MontoInvalidoException;
import Modelo.interfaces.Listable;

import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    private final Scanner sc;
    private final Banco banco;
    
    public Menu(Banco banco){
        this.banco = banco;
        this.sc = new Scanner(System.in);
    }
    
    public void menuInicio(){
        int opcion;
        do{
            System.out.println("--------------------");
            System.out.println("||   Menu Inicio   ||");
            System.out.println("--------------------");
            System.out.println("|1. Iniciar sesion ");
            System.out.println("|2. Registrar cliente");
            System.out.println("|3. Cerrar");
            System.out.print("|Elegir opcion:");
            opcion = ExceptionScannerInt();
            System.out.println("--------------------");
            
            switch(opcion){
                case 1 -> {
                    Cliente c = banco.iniciarSesion();
                    if (c != null) {
                        System.out.println("Iniciando Seseion ");
                        menuPaginaPrincipal(c);
                    }
                }
                case 2 -> {
                    String[] info = new String[3];
                    System.out.println("-------------------");
                    System.out.println("|  Ingrese su rut: ");
                    info[0] = sc.nextLine();
                    System.out.println("|  Ingrese su nombre: ");
                    info[1] = sc.nextLine();
                    System.out.println("|  Cree clave de acceso: ");
                    info[2] = sc.nextLine();
                    
                    Cliente c = banco.registrarCliente(info);
                    if (c != null) {
                        System.out.println("Cliente registrado correctamente.");
                        System.out.println("Iniciando sesion.");
                        menuPaginaPrincipal(c);
                    } else {
                    System.out.println(
                         "Error al registrar cliente" + "Verifique los datos.");
                    }
                }
                case 3 -> {System.out.println("Saliendo del sistema");}
                default -> {System.out.println("Opcion invalida.");}
            }
        }while(opcion != 3);
    }
    
    public String[] menuInicioSesion(){
        String[] info = new String[2];
        System.out.println("-------------------");
        System.out.print("|  Ingrese su rut: ");
        info[0] = sc.nextLine();
        System.out.print("|  Ingrese su contrasenia: ");
        info[1] = sc.nextLine();            
        
        return info;
    }
    
    // crear metodo menuRegistro, hacer lo mismo que con menuInicioSesion
    
    public void menuPaginaPrincipal(Cliente c){
        int opcion;
        do{
            System.out.println("----------------------");
            System.out.println("||  Menu Principal  ||");
            System.out.println("----------------------");
            System.out.println("|1. Administrar cuentas");
            System.out.println("|2. Realizar transaccion");
            System.out.println("|3. Retirar");
            System.out.println("|4. depositar");
            System.out.println("|5. Cerrar sesion");
            System.out.println("|--------------------|");
            System.out.print("|Elegir opcion: ");
            opcion = ExceptionScannerInt();
            System.out.println("----------------------");         
        
            switch(opcion){
                case 1 ->{
                    administrarCuentas(c);
                }
                case 2 ->{
                    if (c.getCuentas().isEmpty()) {
                        System.out.println("No tienes cuentas para transferir.");
                        break;
                    }                    
                    System.out.println("------------------------");
                    System.out.println("| Realizar transaccion |");
                    System.out.println("------------------------");
                    System.out.println("| Selec. cuenta origen |");

                    Cuenta o = selecCuenta(c.getCuentas());
                    System.out.println("-------------------------");
                    System.out.println("| Selec. cuenta destino |");
                    //seleccionar cliente y cuenta de destino
                    //un arraylist de string, de ruts de conocidos, for-each para imprimir
                    //escribir el rut a elegir, puede ser uno de esos o de un nuevo conocido(siempre que este en el hashmap de clientes)
                    //seleccionar cuenta del conocido, si no una predefinida, i = 0
                    if (c.getConocidos().isEmpty()) {
                        System.out.println("no tienes conocidos registrados.");
                    } else {
                        System.out.println("Tus conocidos:");

                        for (String rutConocido : c.getConocidos()) {
                            Cliente conocido = banco.buscarCliente(rutConocido);

                            if (conocido != null) {
                                System.out.println("- " + conocido.getNombre()+ " | RUT: " + rutConocido);
                            }
                        }
                    }
                    System.out.print("Ingrese el RUT del destinatario ");
                    String rut = sc.nextLine().trim();
                    Cliente cd = banco.buscarCliente(rut);
                    if (cd == null) {System.out.println("No existe un cliente con ese RUT.");
                        break;
                    }  
              
                    System.out.println("-------------------------");
                    System.out.println("| Seleccionar cuenta destino |");
                    Cuenta d = selecCuenta(cd.getCuentas());
                    //Cuenta d = selecCuenta(c.getCuentas());
                    System.out.println("---------------------");
                    System.out.println("| Ingresar monto: ");

                    try{
                        double monto = ExceptionScannerDouble();
                        banco.realizarTransaccion(o, d, TipoTransaccion.TRANSFERENCIA, monto);
                        c.agregarConocido(rut);
                    }catch(MontoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() );
                    }
                    
                }
                case 3 ->{
                    if (c.getCuentas().isEmpty()) {
                        System.out.println("No tienes cuentas para retirar.");
                        break;
                    }
                    System.out.println("| Selecciona la cuenta de origen |");
                    Cuenta origen = selecCuenta(c.getCuentas());
                    System.out.print("Ingrese el monto a retirar: ");
                    try {
                        double monto = ExceptionScannerDouble();
                        banco.realizarTransaccion(origen, null, TipoTransaccion.RETIRO, monto);
                    } catch (MontoInvalidoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    }
                case 4 -> {
                    if (c.getCuentas().isEmpty()) {
                        System.out.println("No tienes cuentas para depositar.");
                        break;
                    }
                    System.out.println("| Selecciona la cuenta de destino |");
                    Cuenta destino = selecCuenta(c.getCuentas());        
                    System.out.print("Ingrese el monto a depositar: ");

                    try {
                        double monto = ExceptionScannerDouble();
                            if (monto <= 0) {System.out.println("Monto invalido");
                        break;
                    }
                    banco.realizarTransaccion(null,destino,TipoTransaccion.DEPOSITO,monto);
                    } catch (MontoInvalidoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    }
                case 5 -> {
                    System.out.println("Cerrando sesion.");
                }  
            }
        } while(opcion != 5); 
    }       
    public void administrarCuentas(Cliente c){
        int opcion;
        
        do{
            System.out.println("----------------------------");
            System.out.println("| Administrador de cuentas |");
            System.out.println("|--------------------------|");
            System.out.println("|1. Ver una cuenta");
            System.out.println("|2. Listar cuentas");
            System.out.println("|3. Crear cuenta");
            System.out.println("|4. Eliminar cuenta");
            System.out.println("|5. Cerrar");
            System.out.println("|--------------------------|");
            System.out.println("|Seleccionar opcion: ");
            opcion = ExceptionScannerInt();
            System.out.println("----------------------------");
            
            switch(opcion){
                case 1 ->{
                    if (!c.getCuentas().isEmpty()){
                        Cuenta cu = selecCuenta(c.getCuentas());
                        menuCuenta(cu);                        
                    } else System.out.println("");
                }
                case 2 -> {
                    if (!c.getCuentas().isEmpty()){
                        ArrayList<Listable> cuentas = new ArrayList<>(c.getCuentas());
                        listarInfo(cuentas);
                    }
                }
                case 3 ->{
                    int selec;
                    do{
                        System.out.println("--------------------");
                        System.out.println("| Tipos de cuentas |");
                        System.out.println("|------------------|");
                        System.out.println("|1. vista ");
                        System.out.println("|2. Ahorro");
                        System.out.println("|3. Corriente");
                        System.out.println("|4. Cancelar");
                        System.out.println("|------------------|");
                        System.out.print("|Elegir opcion: ");
                        selec = ExceptionScannerInt();
                    }while(selec < 1 || selec > 4);
                    switch(selec){
                        case 1 ->  {if (banco.crearCuenta(c, TipoCuenta.VISTA)) System.out.println("|Cuenta vista creada|");}
                        case 2 -> {if (banco.crearCuenta(c, TipoCuenta.AHORRO))System.out.println("|Cuenta ahorro creada|");} 
                        case 3 -> {
                            if (banco.crearCuenta(c, TipoCuenta.CORRIENTE))System.out.println("|Cuenta corriente aprobada|");
                            else System.out.println("|Cuenta corriente rechazada|");
                        } 
                    }
                }
                case 4 ->{
                    if (!c.getCuentas().isEmpty()){
                        Cuenta cu = selecCuenta(c.getCuentas());
                        if (banco.eliminarCuenta(c, cu))System.out.println("|Cuenta eliminada con exito|");
                        else System.out.println("|Eliminacion de cuenta no aprobado, deuda activa|");                    
                    }
                }
            }
        }while(opcion != 5);
    }
    
    public void menuCuenta(Cuenta cu){
        int opcion;
        do{
            System.out.println("--------------------");
            System.out.println("| Cuenta n°"+ cu.getNumeroCuenta() +" |");
            System.out.println("--------------------");
            System.out.println("|Saldo: " + cu.getSaldo());
            System.out.println("|Titular: " + cu.getC().getNombre());
            System.out.println("|------------------");
            System.out.println("|1. Ver historial");
            System.out.println("|2. Cerrar");
            System.out.println("|------------------");
            System.out.print("|Elegir opcion: ");
            opcion = ExceptionScannerInt();
            System.out.println("\n--------------------");
            
            if (opcion > 2 || opcion < 0) System.out.println("Opcion no disponible");
            else if (opcion == 1){
                ArrayList<Listable> historial = new ArrayList<>(cu.getHistorial());
                listarInfo(historial);
            }
        }while(opcion != 2);
    }
    
    public Cuenta selecCuenta(ArrayList<Cuenta> cuentas){
        if (cuentas.isEmpty()) return null;
        int seleccion;
        System.out.println("---------------------");
        ArrayList<Listable> lista = new ArrayList<>(cuentas);
        do{
            listarInfo(lista);
            System.out.println("---------------------");
            System.out.print("|Seleccionar Cuenta: ");
            seleccion = ExceptionScannerInt();
            if (seleccion < 1 || seleccion > lista.size()) System.out.println(" Cuenta no encontrada");
        }while(seleccion < 1 || seleccion > lista.size());
        
        return (Cuenta)cuentas.get(seleccion-1);
    }
    
    public int ExceptionScannerInt(){
        try{
            return Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Error: contenido de entrada no valido");
            return -1;
        }
    }
    
    public double ExceptionScannerDouble(){
        try{
            return Double.parseDouble(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Error: contenido de entrada no valido");
            return -1.0;
        }
    }
    
    public void listarInfo(ArrayList<Listable> l){
        if (l.isEmpty()){
            System.out.println("|No hay nada que listar|");
            return;
        }
        
        System.out.println("--------------------");
        for (int i = 0; i < l.size(); i++){
            System.out.println("|" + (i+1) + ") " + l.get(i).mostrarInfo() + " |");
        }
        System.out.println("--------------------");
    }
    
}
