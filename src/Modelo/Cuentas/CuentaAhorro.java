// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;
import java.io.*;

public class CuentaAhorro extends Cuenta implements Serializable{
    private final int MAXRETIROS= 3;
    private int retiros;
    private final double TASAINTERES= 0.1;

    //---Constructor
    public CuentaAhorro(String numeroCuenta, Double saldo, Cliente titular) {
        super(numeroCuenta, saldo, titular);
        this.retiros = 0;
    }

    @Override
    public void retirar(double monto){
        if (retiros < MAXRETIROS) {
            if (monto > saldo) {
                System.out.println("El monto excede el saldo");
            } else if (monto <= saldo) {
                saldo -= monto;
                this.historial.add(new Transaccion(this.numeroCuenta, "CAJERO", TipoTransaccion.RETIRO, monto));
                System.out.println("Monto Retirado con exito");
                retiros++;
            }
        }else {
            System.out.println("El maximo de retiros se ha alcanzado");
        }
    }
    
    //--- Get y Set
    public int getRetiros() { return retiros;}
    public void setRetiros(int retiros) { this.retiros = retiros;}
}
