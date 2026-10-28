// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import Modelo.interfaces.Listable;
import Modelo.interfaces.Selecionable;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cuenta implements Serializable, Listable, Selecionable {
    public static int contadorCuentas = 0;
    
    protected final String numeroCuenta;
    protected final Cliente cliente;
    protected Double saldo;
    protected ArrayList<Transaccion> historial;
   
    // Constructor
    public Cuenta(Double saldo, Cliente titular) {
        this.numeroCuenta = "012300"+contadorCuentas;
        this.saldo = saldo;
        this.cliente = titular;
        this.historial = new ArrayList<>();
        contadorCuentas++;
    }

    public void depositar(Transaccion t){
        saldo+= t.getMonto();
        historial.add(t);
        switch (t.getTipo()) {
            case TipoTransaccion.TRANSFERENCIA -> System.out.println("Transferencia realizada");
                
            case TipoTransaccion.DEPOSITO -> System.out.println("Deposito realizado");
        }
    }
    
    
    //---Metodo Abstract
    public abstract boolean retirar (Transaccion t);
    public abstract void validarRetiro(double monto);

    
    @Override
    public String mostrarInfo(){
        return "" + cliente.getNombre() + " |Numero de cuenta: " + numeroCuenta;
    }
    
    //---Gets y Sets
    public String getNumeroCuenta() {return numeroCuenta;}
    public Double getSaldo() {return saldo;}
    public Cliente getC() {return cliente;}
    public ArrayList<Transaccion> getHistorial() {return historial;}
    
    public static int getContadorCuentas() { return contadorCuentas; }
    public static void setContadorCuentas(int valor) { contadorCuentas = valor; }
}