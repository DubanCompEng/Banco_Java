// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.MaxRetirosException;
import Modelo.excepciones.SaldoInsuficienteException;
import java.io.*;

public class CuentaAhorro extends Cuenta implements Serializable{
    // implementar fechas y calcular(metodo) intereses con ella
    private static final double TASAINTERES = 0.1;
    private static final int MAXRETIROS= 3;
    private int retiros;

    //---Constructor
    public CuentaAhorro(Double saldo, Cliente titular) {
        super(saldo, titular);
        this.retiros = 0;
    }

    @Override
    public void validarRetiro(double monto){
        if (monto > saldo) throw new SaldoInsuficienteException();
        if (retiros == MAXRETIROS) throw new MaxRetirosException();
    }
    
    @Override
    public boolean retirar(Transaccion t){
        saldo -= t.getMonto();
        historial.add(t);
                if(t.getTipo() == TipoTransaccion.RETIRO){
            System.out.println("Retiro realizado");
        }
        retiros++;
        return true;
    
    }
    
    @Override
    public String mostrarInfo() {
        return "Ahorro | " + super.mostrarInfo() + " | Saldo: $" + saldo + " | Retiros: " + retiros + "/" + MAXRETIROS;
    }
    
    //--- Get y Set
    public int getRetiros() { return retiros;}
}
