// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.enums.TipoCuenta;

import java.io.*;

public class CuentaVista extends Cuenta implements Serializable{
    private final double COMISION = 300;

    public CuentaVista(String numeroCuenta, Double saldo, Cliente titular, TipoCuenta tipo) {
        super(numeroCuenta, saldo, titular, tipo);
    }

    @Override
    public void retirar(double monto) {
        if (saldo < monto + COMISION){
            System.out.println("No tines saldo suficiente");
        }else{
            saldo -= monto + COMISION;
            System.out.println("Retiro exitoso");
        }
    }
}
