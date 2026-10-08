// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.enums.TipoCuenta;
import java.io.*;

public class CuentaAhorro extends Cuenta implements Serializable{
    private final int MAXRETIROS= 3;
    private int retiros;
    private final double TASAINTERES= 0.1;

    public CuentaAhorro(String numeroCuenta, Double saldo, Cliente titular, TipoCuenta tipo) {
        super(numeroCuenta, saldo, titular, tipo);
        this.retiros = 0;
    }

    @Override
    public void retirar(double monto){
        if (retiros < MAXRETIROS) {
            if (monto > saldo) {
                System.out.println("El monto excede el saldo");
            } else if (monto <= saldo) {
                saldo -= monto;
                System.out.println("Monto Retirado con exito");
                retiros++;
            }
        }else {
            System.out.println("El maximo de retiros se ha alcanzado");
        }
    }

}
