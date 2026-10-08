/*

    [REQUISITO: Package],  [REQUISITO: Coleciones], [REQUISITO: Persistencia]
*/


package Controlador;

import Vista.Menu;
import Modelo.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Banco {
    private HashMap< String, Cliente> clientes = new HashMap<>();
    private Menu menu;
    
    public static void main(String[] args) {
        Banco banco = new Banco();
        
        banco.clientes = banco.cargarDatos("datos");
        
        banco.menu = new Menu(banco);
        banco.menu.menuInicio();
        
        banco.guardarDatos("datos");
    }
    
    public Cliente iniciarSesion(){
        if(clientes.isEmpty()) return null;
        
        String[] info;
        int intentos = 3;
        
        do{
            info = menu.menuInicioSesion();
            
            if (clientes.containsKey(info[0])){
                if (clientes.get(info[0]).getClave().equals(info[1]))return clientes.get(info[0]);
                else {
                    System.out.println("Contrasenia incorrecta");
                    intentos--;
                }
            } else {
                System.out.println("\nCliente no encontrado \n");
                intentos--;
            }
            System.out.println("Intentos disponibles actuales " + intentos);
        }while(intentos != 0);
        
        System.out.println("Numero de intentos disponibles agotados");
        return null;
    }
    
    public void clientes(){
        ArrayList<Object> c = new ArrayList<>(clientes.values());
        menu.listarInfo(c);
    }
    
    public Cliente registrarCliente(String[] info){
 
        Cliente c = new Cliente(info[0], info[1], info[2]);
        clientes.put(info[0], c);
        
        return c;
    }
    
    public HashMap<String, Cliente> cargarDatos(String archivo) {
        if (!new File(archivo).exists()) return new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (HashMap<String, Cliente>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return new HashMap<>();
        }
    }
    
    public void guardarDatos(String archivo){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

    }
    
}
