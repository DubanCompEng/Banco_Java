// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.SaldoInsuficienteException;

import java.io.*;

//--Constructor
public class CuentaVista extends Cuenta implements Serializable{
    private static final double COMISION = 300;

    public CuentaVista(Double saldo, Cliente titular) {
        super(saldo, titular);
    }

    @Override
    public boolean retirar(Transaccion t) {
    saldo -= t.getMonto() + COMISION;
    historial.add(t);
    System.out.println("Retiro realizada.");
    return true;
    }
    
    @Override
    public void validarRetiro(double monto){
        if (saldo < monto + COMISION) {
            throw new SaldoInsuficienteException();
        }        
    }
    
    @Override
    public String mostrarInfo() {
        return "Vista | " + super.mostrarInfo() + " | Saldo: $" + saldo;
    }
}