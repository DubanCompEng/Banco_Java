// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoCuenta;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cuenta implements Serializable {
    protected String numeroCuenta;
    protected Double saldo;
    protected Cliente c;
    protected TipoCuenta tipo;
    ArrayList<Transaccion> historial;

    public Cuenta(String numeroCuenta, Double saldo, Cliente titular, TipoCuenta tipo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.c = titular;
        this.tipo = tipo;
        this.historial = new ArrayList<>();
    }

    public void depositar(double monto){
        if (monto>0){
            saldo+= monto;
            System.out.println("Deposito realizado");
        }else{
            System.out.println("Ingrese un monto valido");
        }
    }
    public abstract void retirar (double monto);
}