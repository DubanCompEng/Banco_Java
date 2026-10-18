// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.MontoInvalidoException;
import java.io.*;

public class CuentaAhorro extends Cuenta implements Serializable{
    private final int MAXRETIROS= 3;
    private int retiros;
    private final double TASAINTERES = 0.1;

    //---Constructor
    public CuentaAhorro(String numeroCuenta, Double saldo, Cliente titular) {
        super(numeroCuenta, saldo, titular);
        this.retiros = 0;
    }

    @Override
    public void retirar(Transaccion t){
        if (retiros < MAXRETIROS) {
            if (t.getMonto() > saldo) {
                throw new MontoInvalidoException();
            } else if (t.getMonto() <= saldo) {
                saldo -= t.getMonto();
                this.historial.add(new Transaccion(""+Transaccion.contadorTransaccion, this.numeroCuenta, "CAJERO", TipoTransaccion.RETIRO, t.getMonto()));
                System.out.println("Monto Retirado con exito");
                retiros++;
            }
        }else {
            System.out.println("El maximo de retiros se ha alcanzado");
        }
    }
    
    //--- Get y Set
    public int getRetiros() { return retiros;}
    public void setRetiros() { this.retiros += 1;}
}
