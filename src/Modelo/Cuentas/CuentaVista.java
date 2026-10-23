// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.SaldoInsuficienteException;

import java.io.*;

//--Constructor
public class CuentaVista extends Cuenta implements Serializable{
    private static double COMISION = 300;

    public CuentaVista(String numeroCuenta, Double saldo, Cliente titular) {
        super(numeroCuenta, saldo, titular);
    }

    @Override
    public boolean retirar(Transaccion t) {
        if (t.getMonto() <= 0) {
            throw new Modelo.excepciones.MontoInvalidoException();
        }
        if (saldo < t.getMonto() + COMISION) {
            throw new SaldoInsuficienteException();
        }
    saldo -= t.getMonto() + COMISION;
    historial.add(t);
    System.out.println("Retiro realizada.");
    return true;
    }
    
    @Override
    public String mostrarInfo() {
        return "Vista | " + super.mostrarInfo() + " | Saldo: $" + saldo;
    }
}