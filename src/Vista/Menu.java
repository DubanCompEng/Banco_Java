/*

    [REQUISITO: Package],  [REQUISITO: Menu-Scanner]
*/


package Vista;

import Controlador.Banco;
import Modelo.Cliente;

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
            System.out.println("|4. listar clientes");
            System.out.print("|Elegir opcion:");
            opcion = Integer.parseInt(sc.nextLine());
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
                    if (c != null){
                        System.out.println("Iniciando Seseion ");
                        menuPaginaPrincipal(c);
                    }
                }
                case 3 -> {System.out.println("Saliendo del sistema");}
                case 4 -> {banco.clientes();}
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
    
    private void menuPaginaPrincipal(Cliente c){
        int opcion;
        do{
            System.out.println("----------------------");
            System.out.println("||  Menu Principal  ||");
            System.out.println("----------------------");
            System.out.println("|1. Administrar cuentas");
            System.out.println("|2. Abrir cuenta");
            System.out.println("|3. Listar cuentas");
            System.out.println("|4. Informacion Sucursales");
            System.out.println("|5. cerrar");
            opcion = Integer.parseInt(sc.nextLine());
            System.out.println("----------------------");         
        
            switch(opcion){
                case 1 ->{}
                case 2 ->{}
                case 3 ->{}
            }
            
        }while(opcion != 6);
       
        
    }
    
    public void listarInfo(ArrayList<Object> o){
        for ( Object obj: o){
            System.out.println(obj);
        }
    }
}
