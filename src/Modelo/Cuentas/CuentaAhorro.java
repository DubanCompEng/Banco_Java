// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.SaldoInsuficienteException;
import java.io.*;

public class CuentaAhorro extends Cuenta implements Serializable{
    // implementar fechas y calcular(metodo) intereses con ella
    private final int MAXRETIROS= 3;
    private int retiros;
    private final double TASAINTERES = 0.1;

    //---Constructor
    public CuentaAhorro(String numeroCuenta, Double saldo, Cliente titular) {
        super(numeroCuenta, saldo, titular);
        this.retiros = 0;
    }

    @Override
    public boolean retirar(Transaccion t){
        if (t.getMonto() <= 0) {
                throw new Modelo.excepciones.MontoInvalidoException();
                }
                if (retiros >= MAXRETIROS) {
                    System.out.println("Se alcanzo el maximo de retiros. ");
                return false;
                }
                if (t.getMonto() > saldo) {
                    throw new SaldoInsuficienteException();
                }
                saldo -= t.getMonto();
                historial.add(t);
                retiros++;
                System.out.println("Retiro realizado con exito.");
                    return true;
                }
    //--- Get y Set
    public int getRetiros() { return retiros;}
    public void setRetiros() { this.retiros += 1;}
}
