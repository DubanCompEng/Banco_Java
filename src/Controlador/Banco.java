/*

    [REQUISITO: Package],  [REQUISITO: Coleciones], [REQUISITO: Persistencia]
*/


package Controlador;

import Vista.Menu;
import Modelo.Cliente;
import Modelo.Cuentas.Cuenta;
import Modelo.Cuentas.CuentaAhorro;
import Modelo.Cuentas.CuentaCorriente;
import Modelo.Cuentas.CuentaVista;
import Modelo.Transaccion;
import Modelo.enums.TipoCuenta;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.MontoInvalidoException;
import Modelo.excepciones.SaldoInsuficienteException;
import java.util.HashMap;
import java.io.*;

public class Banco {
    private HashMap< String, Cliente> clientes = new HashMap<>();
    private Menu menu;
    public static final String nombre = "Pepe bank";
    public static final String archivo = "datos";
        
    public static void main(String[] args) {
        Banco banco = new Banco();
        
        banco.clientes = banco.cargarDatos();
        
        banco.menu = new Menu(banco);
        banco.menu.menuInicio();
        
        banco.guardarDatos();
    }
    
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
    
    public Cliente registrarCliente(String[] info){
 
        // considerar si la cuenta ya existe, modificar metodo
        Cliente c = new Cliente(info[0], info[1], info[2]);
        clientes.put(info[0], c);
        
        return c;
    }
    
    public boolean crearCuenta(Cliente c, TipoCuenta tc){
        switch(tc){
            case TipoCuenta.VISTA -> {
                String numCuenta = "00" + (Cuenta.contadorCuentas + 1000);
                c.agregarCuenta(new CuentaVista(numCuenta, 0.0, c));
            }
            case TipoCuenta.AHORRO ->{
                String numCuenta = "00" + (Cuenta.contadorCuentas + 1000);
                c.agregarCuenta(new CuentaAhorro(numCuenta, 0.0, c));
            }
            case TipoCuenta.CORRIENTE ->{
                double ranking = c.getValorRanking();
                int credito;
                if (ranking < 100000) return false;
                else if (ranking < 1000000)credito = 500000;
                else if (ranking < 10000000)credito = 5000000;
                else credito = 20000000;
                String numCuenta = "00" + (Cuenta.contadorCuentas + 1000);
                c.agregarCuenta( new CuentaCorriente(numCuenta, 0.0, c, credito));
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
    
    public void realizarTransaccion(Cuenta o, Cuenta d, TipoTransaccion tt, double monto){
        if (o != null && d != null){
            // transaccion entre 2 cuentas
            Transaccion t = new Transaccion(""+Transaccion.contadorTransaccion, o.getNumeroCuenta(), d.getNumeroCuenta(), tt, monto);
            try{
                o.retirar(t);
                d.depositar(t);
            }catch(SaldoInsuficienteException e){
                System.out.println("Error:" + e.getMessage());
            }
        } else {
            if (o == null && d != null){ //deposito de dinero desde una caja hacia una cuenta de destino
                Transaccion t = new Transaccion(""+Transaccion.contadorTransaccion, "Cajero", d.getNumeroCuenta(), tt, monto);
                try{
                    d.depositar(t);
                }catch(SaldoInsuficienteException e){
                    System.out.println("Error:" + e.getMessage());
                }
            }else if(o != null && d == null){ //retiro de dinero desde una cuenta, en un cajero
                Transaccion t = new Transaccion(""+Transaccion.contadorTransaccion, o.getNumeroCuenta(), "Cajero", tt, monto);
                try{
                    o.retirar(t);
                }catch(SaldoInsuficienteException e){
                    System.out.println("Error:" + e.getMessage());
                }
            }
        }
        
        
    }
    
    public HashMap<String, Cliente> cargarDatos() {
        if (!new File(archivo).exists()) return new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (HashMap<String, Cliente>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return new HashMap<>();
        }
    }
    
    public void guardarDatos(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

    }
    
    
}
