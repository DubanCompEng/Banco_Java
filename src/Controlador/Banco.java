/*
    [REQUISITO: Package],  [REQUISITO: Coleciones], [REQUISITO: Persistencia]
*/


package Controlador;

import Vista.Menu;
import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.Cuentas.Cuenta;
import Modelo.Cuentas.CuentaAhorro;
import Modelo.Cuentas.CuentaCorriente;
import Modelo.Cuentas.CuentaVista;
import Modelo.enums.TipoCuenta;
import Modelo.enums.TipoTransaccion;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Banco implements Serializable{
    private static final long serialVersionUID = 1L;

    private HashMap<String, Cliente> clientes = new HashMap<>();
    private HashMap<String, Cuenta> cuentas = new HashMap<>();
    private ArrayList<Transaccion> historial = new ArrayList<>();//
    private transient Menu menu;
    
    public static final String nombre = "Pepe bank";
    public static final String archivo = "datos";
    public static final String CLAVE_ADMIN = "admin123";
        
    public static void main(String[] args) {                
        Banco banco = cargarDatos();
        
        banco.menu = new Menu(banco);
        banco.menu.menuInicio();
        
        banco.guardarDatos();
    }
    
    
    
    
    //-----CLIENTES
    public Cliente buscarCliente(String rut){
        if (clientes.containsKey(rut)){
            return clientes.get(rut);
        }
        return null;
    }
    
    public Cliente iniciarSesion(){
        if(clientes.isEmpty()) return null;
        
        String[] info;
        int intentos = 3;
        
        do{
            info = menu.menuInicioSesion();
            
            if (clientes.containsKey(info[0])){
                if (clientes.get(info[0]).getClave().equals(info[1])){
                    return clientes.get(info[0]);
                }
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
    
    public Cliente registrarCliente(String[] info){
        
        String rut = info[0].trim();
        String nombre = info[1].trim();
        String clave = info[2].trim();
        
        if (rut.isEmpty() || nombre.isEmpty() || clave.isEmpty()) {return null;}
        
        if (clientes.containsKey(rut)) {return null;}
        
        Cliente cliente = new Cliente(rut, nombre, clave);
        clientes.put(rut, cliente);
                
        return cliente;
    }
    
    
    //-----CUENTAS
    public boolean crearCuenta(Cliente c, TipoCuenta tc){
        switch(tc){
            case TipoCuenta.VISTA -> {
                Cuenta cu = new CuentaVista(0.0, c);
                c.agregarCuenta(cu);
                cuentas.put(cu.getNumeroCuenta(), cu);
            }
            case TipoCuenta.AHORRO ->{
                Cuenta cu = new CuentaAhorro(0.0, c);
                c.agregarCuenta(cu);
                cuentas.put(cu.getNumeroCuenta(), cu);
            }
            case TipoCuenta.CORRIENTE ->{
                double ranking = c.getValorRanking();
                int credito;
                if (ranking < 100000) return false;
                else if (ranking < 1000000)credito = 500000;
                else if (ranking < 10000000)credito = 5000000;
                else credito = 20000000;
                Cuenta cu = new CuentaCorriente(0.0, c, credito);
                c.agregarCuenta(cu);
                cuentas.put(cu.getNumeroCuenta(), cu);
            }
        }
        return true;
    }
    
    public boolean eliminarCuenta(Cliente c, Cuenta cu){
        if (cu.getSaldo() > 0) {//cuenta que se elimina, tranfiere su saldo a otra cuenta, solo si tiene y es positivo
            if (c.getCuentas().size() > 1){
                int i = c.getCuentas().indexOf(cu);
                Cuenta temp;
                if (i == 0) temp = c.getCuentas().get(1);
                else temp = c.getCuentas().get(0);
                
                realizarTransaccion(cu, temp, TipoTransaccion.TRANSFERENCIA, cu.getSaldo());
            } else if (c.getCuentas().size() == 1) realizarTransaccion(cu, null, TipoTransaccion.TRANSFERENCIA, cu.getSaldo());
        }else if(cu.getSaldo() < 0) return false;
        
        c.removerCuenta(cu);
        return true;
    }
    
    public Cuenta buscarCuenta(String numero) {return cuentas.get(numero);}
    
    //-----TRANSACCIONES
    public void realizarTransaccion(Cuenta o, Cuenta d, TipoTransaccion tt, double monto){
        if (o != null && d != null){
            // transaccion entre 2 cuentas
            
            o.validarRetiro(monto); // valida que se pueda sacar ese monto de la cuenta
            //al crear la transaccion solo si se valida el monto, se crea la transaccion
            Transaccion t = new Transaccion( o.getNumeroCuenta(), d.getNumeroCuenta(), tt, monto);

            o.retirar(t);
            d.depositar(t);
            historial.add(t);
        } else {
            if (o == null && d != null){ //deposito de dinero desde una caja hacia una cuenta de destino
                
                //al crear la transaccion solo si se valida el monto, se crea la transaccion            
                Transaccion t = new Transaccion("Cajero", d.getNumeroCuenta(), tt, monto);
                
                d.depositar(t);
                historial.add(t);
                
            }else if(o != null && d == null){ //retiro de dinero desde una cuenta, en un cajero
                
                o.validarRetiro(monto);// valida que se pueda sacar ese monto de la cuenta
                //al crear la transaccion solo si se valida el monto, se crea la transaccion
                Transaccion t = new Transaccion(o.getNumeroCuenta(), "Cajero", tt, monto);
                
                o.retirar(t);
                historial.add(t);
            }
        }
    }
    
    //-----REPORTES
    public ArrayList<Cliente> rankingClientes() {
        ArrayList<Cliente> lista = new ArrayList<>(clientes.values());
        lista.sort((a, b) -> Double.compare(b.getValorRanking(), a.getValorRanking()));
        return lista;
    }
    
    public ArrayList<Cliente> clientesOrdenadosPorNombre() {
        ArrayList<Cliente> lista = new ArrayList<>(clientes.values());
        lista.sort((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre()));
        return lista;
    }
    
    //-----PERSISTENCIA
    public static Banco cargarDatos() {
        if (!new File(archivo).exists()) return new Banco();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Banco banco = (Banco)ois.readObject();
            Cuenta.setContadorCuentas(banco.cuentas.size());
            Transaccion.setContadorTransaccion(banco.historial.size());
            return banco;
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return new Banco();
        }
    }
    
    public void guardarDatos(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

    }

    public ArrayList<Transaccion> getHistorial() { return historial; }
    public HashMap<String, Cuenta> getCuentas(){return cuentas;}
}
