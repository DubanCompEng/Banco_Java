// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.MontoInvalidoException;
import Modelo.interfaces.Listable;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cuenta implements Serializable, Listable {
    public static int contadorCuentas = 0;
    
    protected final String numeroCuenta;
    protected final Cliente cliente;
    protected Double saldo;
    protected ArrayList<Transaccion> historial;
   
    // Constructor
    public Cuenta(String numeroCuenta, Double saldo, Cliente titular) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = titular;
        this.historial = new ArrayList<>();
    }

    // hay que tratar a depositar como un deposito en la cuenta, desde donde sea
    public void depositar(Transaccion t){
        saldo+= t.getMonto();
        historial.add(t);
        System.out.println("Deposito realizado");
    }
    
    //---Metodo Abstract
    // hay que tratar a retirar como retirar dinero de la cuenta, desde done y hacia donde, depende de mas
    //del contexto en el que se llame al metodo, ya sea para transferir o retirar en caja
    //el metodo recibe una transaccion t ysi el monto es mayor a el saldo guardar t, no crear uno nuevo
    public abstract boolean retirar (Transaccion t);
    
    @Override
    public String mostrarInfo(){
        return "" + cliente.getNombre() + "|Numero de cuenta: " + numeroCuenta;
    }
    
    //---Gets y Sets
    public String getNumeroCuenta() {return numeroCuenta;}
    public Double getSaldo() {return saldo;}
    public Cliente getC() {return cliente;}
    public ArrayList<Transaccion> getHistorial() {return historial;}
 
}