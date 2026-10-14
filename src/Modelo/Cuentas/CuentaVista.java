// [REQUISITO: Herencia]

package Modelo.Cuentas; // [REQUISITO: Package]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.TipoTransaccion;

import java.io.*;

//--Constructor
public class CuentaVista extends Cuenta implements Serializable{
    private final double COMISION = 300;

    public CuentaVista(String numeroCuenta, Double saldo, Cliente titular) {
        super(numeroCuenta, saldo, titular);
    }

    @Override
    public void retirar(double monto) {
        if (saldo < monto + COMISION){
            System.out.println("No tines saldo suficiente");
        }else{
            saldo -= monto + COMISION;
            this.historial.add(new Transaccion(this.numeroCuenta, "CAJERO", TipoTransaccion.RETIRO, monto));
            System.out.println("Retiro exitoso");
        }
    }
}
