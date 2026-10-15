// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cuenta implements Serializable {
    public static int contadorCuentas = 0;
    
    protected String numeroCuenta;
    protected Double saldo;
    protected final Cliente cliente;
    ArrayList<Transaccion> historial;
   
    // Constructor
    public Cuenta(String numeroCuenta, Double saldo, Cliente titular) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = titular;
        this.historial = new ArrayList<>();
    }

    public void depositar(double monto){
        if (monto>0){
            saldo+= monto;
            this.historial.add(new Transaccion(this.numeroCuenta, this.numeroCuenta, TipoTransaccion.DEPOSITO, monto));
            System.out.println("Deposito realizado");
            
        }else{
            System.out.println("Ingrese un monto valido");
        }
    }
    
    //---Metodo Abstract
    public abstract void retirar (double monto);
    
    //---Gets y Sets
    public String getNumeroCuenta() {return numeroCuenta;}
    public void setNumeroCuenta(String numeroCuenta) {this.numeroCuenta = numeroCuenta;}

    public Double getSaldo() {return saldo;}
    public void setSaldo(Double saldo) {this.saldo = saldo;}

    public Cliente getC() {return cliente;}

    public ArrayList<Transaccion> getHistorial() {return historial;}
    public void setHistorial(ArrayList<Transaccion> historial) {this.historial = historial;}
 
}