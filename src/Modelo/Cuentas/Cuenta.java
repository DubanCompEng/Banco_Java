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
    protected Double saldo;
    protected final Cliente cliente;
    protected ArrayList<Transaccion> historial;
   
    // Constructor
    public Cuenta(String numeroCuenta, Double saldo, Cliente titular) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = titular;
        this.historial = new ArrayList<>();
    }

    public void depositar(Transaccion t){
        if (t.getMonto() >0){
            saldo+= t.getMonto();
            historial.add(new Transaccion("" + Transaccion.contadorTransaccion, this.numeroCuenta, this.numeroCuenta, TipoTransaccion.DEPOSITO, t.getMonto()));
            System.out.println("Deposito realizado");
            
        }else throw new MontoInvalidoException();
    }
    
    //---Metodo Abstract
    public abstract void retirar (Transaccion t);
    
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