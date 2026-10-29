/*
    [REQUISITO: Package],  [REQUISITO: Menu-Scanner]
*/

/*
Datos de precargados
Clientes: 
    Nombre: pepe, Rut: 123, Clave: 123
    Nombre: pepa, Rut: 456, Clave: 456
    Nombre: Luis, Rut: 12345678-9, Clave: 4321
    Nombre: Carlos, Rut: 98765432-1, CLave: 6789
    Nombre: Maria, Rut: 10202303-4, Clave: 123

Cuentas:
    Titular: pepe, Tipo: Vista, num: 0123000
    Titular: pepa, Tipo: Vista, num: 0123003
    Titular: Luis, Tipo: Vista, num: 0123005
    Titular: Carlos, Tipo: Vista, num: 0123006
    Titular: Maria, Tipo: Vista, num: 0123008

    Titular: pepe, Tipo: Ahorro, num: 0123001
    Titular: Maria, Tipo: Ahorro, num: 0123009
    Titular: pepa, Tipo: Ahorro, num: 0123004

    Titular: Pepe, Tipo: Corriente, num: 0123002
    Titular: Carlos, Tipo: Corriente, num: 0123007
    Titular: Maria, Tipo: Corriente, num: 01230010

Transacciones:
    Origen: pepe-Caja, Destino: pepe-Vista, Monto: 120000
    Origen: Luis-Caja, Destino: Luis-Vista, Monto: 50000
    Origen: Luis-vista, Destino: Carlos-Vista, Monto: 30000
    Origen: Carlos-Cajero, Destino: Carlos-Vista, Monto: 100000
    Origen: Carlos-Vista, Destino: Carlos-Corriente, Monto: 50000
    Origen: Carlos-Corriente, Destino: pepe-Ahorro, Monto: 20000
    Origen: pepe-Vista, Destino: Maria-Vista, Monto: 100000
    Origen: Maria-Vista, Destino: Maria-Ahorro, Monto: 50000
    Origen: Maria-Vista, Destino: Maria-Cajero, Monto: 30000
   
*/


package Vista;

import Controlador.Banco;
import Modelo.Cliente;
import Modelo.Cuentas.Cuenta;
import Modelo.enums.TipoCuenta;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.MaxRetirosException;
import Modelo.excepciones.MontoInvalidoException;
import Modelo.excepciones.SaldoInsuficienteException;
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
            System.out.println("|3. Iniciar administrador");
            System.out.println("|4. Cerrar");
            System.out.print("|Elegir opcion:");
            opcion = ExceptionScannerInt();
            System.out.println("--------------------\n");
            
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
                    System.out.print("|  Ingrese su rut: ");
                    info[0] = sc.nextLine();
                    System.out.print("|  Ingrese su nombre: ");
                    info[1] = sc.nextLine();
                    System.out.print("|  Cree clave de acceso: ");
                    info[2] = sc.nextLine();
                    
                    Cliente c = banco.registrarCliente(info);
                    if (c != null) {
                        System.out.println("\nCliente registrado correctamente.");
                        System.out.println("Iniciando sesion...");
                        menuPaginaPrincipal(c);
                    } else {
                    System.out.println(
                         "Error al registrar cliente" + "Verifique los datos.");
                    }
                }
                case 3 -> menuAdmin();
                case 4 -> {System.out.println("Saliendo del sistema");}
                default -> {System.out.println("Opcion invalida.");}
            }
        }while(opcion != 4);
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
     
    public void menuPaginaPrincipal(Cliente c){
        int opcion;
        do{
            System.out.println("\n----------------------");
            System.out.println("||  Menu Principal  ||");
            System.out.println("----------------------");
            System.out.println("|1. Ver perfil");
            System.out.println("|2. Administrar cuentas");
            System.out.println("|3. Realizar transaccion");
            System.out.println("|4. Retirar");
            System.out.println("|5. depositar");
            System.out.println("|6. Cerrar sesion");
            System.out.println("|--------------------|");
            System.out.print("|Elegir opcion: ");
            opcion = ExceptionScannerInt();
            System.out.println("----------------------\n");
        
            switch(opcion){
                case 1 -> verPerfil(c);
                case 2 ->{
                    administrarCuentas(c);
                }
                case 3 ->{
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

                    if (!c.getConocidos().isEmpty()) {
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
                    }if (cd.getCuentas().isEmpty()) {
                        System.out.println("El destinatario no tiene cuentas");
                        break;
                    }
                    
                    System.out.println("-------------------------");
                    System.out.println("| Seleccionar cuenta destino |");
                    Cuenta d = selecCuenta(cd.getCuentas()); 
                    if (d == o){
                        System.out.println("La cuenta de origen y destino no pueden ser la misma");
                        
                    } else {
                        System.out.println("---------------------");
                        System.out.println("| Ingresar monto: ");
                        try{
                            double monto = ExceptionScannerDouble();
                            banco.realizarTransaccion(o, d, TipoTransaccion.TRANSFERENCIA, monto);
                            if(!c.getRut().equals(rut)) c.agregarConocido(rut);
                        }catch(MontoInvalidoException | SaldoInsuficienteException | MaxRetirosException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                }
                case 4 ->{
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
                    } catch (MontoInvalidoException | SaldoInsuficienteException | MaxRetirosException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> {
                    if (c.getCuentas().isEmpty()) {
                        System.out.println("No tienes cuentas para depositar.");
                        break;
                    }
                    System.out.println("| Selecciona la cuenta de destino |");
                    Cuenta destino = selecCuenta(c.getCuentas());
                    System.out.print("Ingrese el monto a depositar: ");

                    try {
                        double monto = ExceptionScannerDouble();
                        banco.realizarTransaccion(null,destino,TipoTransaccion.DEPOSITO,monto);
                    }catch (MontoInvalidoException | SaldoInsuficienteException | MaxRetirosException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 6 -> {
                    System.out.println("Cerrando sesion...");
                }
            }
        }while(opcion != 6);
    }
    
    public void administrarCuentas(Cliente c){
        int opcion;
        
        do{
            System.out.println("\n----------------------------");
            System.out.println("| Administrador de cuentas |");
            System.out.println("|--------------------------|");
            System.out.println("|1. Ver una cuenta");
            System.out.println("|2. Listar cuentas");
            System.out.println("|3. Crear cuenta");
            System.out.println("|4. Eliminar cuenta");
            System.out.println("|5. Cerrar");
            System.out.println("|--------------------------|");
            System.out.print("|Seleccionar opcion: ");
            opcion = ExceptionScannerInt();
            System.out.println("----------------------------\n");
            
            switch(opcion){
                case 1 ->{
                    if (!c.getCuentas().isEmpty()){
                        Cuenta cu = selecCuenta(c.getCuentas());
                        menuCuenta(cu);                        
                    } else System.out.println("\nCliente no tiene ninguna cuenta bancaria\n");
                }
                case 2 -> {
                    if (!c.getCuentas().isEmpty()){
                        ArrayList<Listable> cuentas = new ArrayList<>(c.getCuentas());
                        listarInfo(cuentas);
                    } else System.out.println("\nCliente no tiene ninguna cuenta bancaria\n");
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
                        case 1 ->  {if (banco.crearCuenta(c, TipoCuenta.VISTA)) System.out.println("\n|Cuenta vista creada|");}
                        case 2 -> {if (banco.crearCuenta(c, TipoCuenta.AHORRO))System.out.println("\n|Cuenta ahorro creada|");} 
                        case 3 -> {
                            if (banco.crearCuenta(c, TipoCuenta.CORRIENTE))System.out.println("\n|Cuenta corriente aprobada|");
                            else System.out.println("\n|Cuenta corriente rechazada|");
                        } 
                    }
                }
                case 4 ->{
                    if (!c.getCuentas().isEmpty()){
                        Cuenta cu = selecCuenta(c.getCuentas());
                        if (banco.eliminarCuenta(c, cu))System.out.println("\n|Cuenta eliminada con exito|");
                        else System.out.println("\n|Eliminacion de cuenta no aprobado, deuda activa|");                    
                    } else System.out.println("\nCliente no tiene ninguna cuenta bancaria\n");
                }
            }
        }while(opcion != 5);
    }
    
    public void menuCuenta(Cuenta cu){
        int opcion;
        do{
            System.out.println("--------------------");
            System.out.println("| Cuenta num "+ cu.getNumeroCuenta() +" |");
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
            System.out.println("|No hay nada que listar|\n");
            return;
        }
        
        System.out.println("--------------------");
        for (int i = 0; i < l.size(); i++){
            System.out.println("|" + (i+1) + ". " + l.get(i).mostrarInfo() + " |");
        }
        System.out.println("");
    }
    
    public void verPerfil(Cliente c) {
        System.out.println("\n----------------------------");
        System.out.println("| Perfil de " + c.getNombre());
        System.out.println("| RUT: " + c.getRut());
        System.out.println("| Cuentas: " + c.getCuentas().size());
        System.out.println("| Saldo total: $" + c.getValorRanking());
        System.out.println("----------------------------");
    }
    
    public void mostrarRanking() {
        ArrayList<Cliente> ranking = banco.rankingClientes();
        System.out.println("---- Ranking de clientes por saldo total ----");
        for (int i = 0; i < ranking.size(); i++) {
            Cliente cl = ranking.get(i);
            System.out.println("|" + (i + 1) + ") " + cl.getNombre() + " | $" + cl.getValorRanking());
        }
        System.out.println("---------------------------------------------");
    }
    
    public void menuAdmin() {
        System.out.print("|Clave de administrador: ");
        String clave = sc.nextLine();
        if (!clave.equals(Banco.CLAVE_ADMIN)) {
            System.out.println("Clave incorrecta.");
            return;
        }
        int opcion;
        do {
            System.out.println("-------------------------------");
            System.out.println("||     Menu Administrador    ||");
            System.out.println("-------------------------------");
            System.out.println("|1. Ranking de clientes");
            System.out.println("|2. Historial global de operaciones");
            System.out.println("|3. Clientes ordenados por nombre");
            System.out.println("|4. Buscar cuenta por numero");
            System.out.println("|5. Volver");
            System.out.print("|Elegir opcion: ");
            opcion = ExceptionScannerInt();
            System.out.println("-------------------------------\n");

            switch (opcion) {
                case 1 -> mostrarRanking();
                case 2 -> listarInfo(new ArrayList<>(banco.getHistorial()));
                case 3 -> listarInfo(new ArrayList<>(banco.clientesOrdenadosPorNombre()));
                case 4 -> {
                    if(banco.getCuentas().isEmpty()) break;
                    System.out.print("|Numero de cuenta: ");
                    Cuenta cu = banco.buscarCuenta(sc.nextLine().trim());
                    if (cu == null) {
                        System.out.println("No existe esa cuenta.");
                    } else {
                        ArrayList<Listable> uno = new ArrayList<>();
                        uno.add(cu);
                        listarInfo(uno);
                    }
                }
                case 5 -> System.out.println("Volviendo.");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);
        }
}
