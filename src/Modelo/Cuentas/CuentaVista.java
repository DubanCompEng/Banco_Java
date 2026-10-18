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
    public void retirar(Transaccion t) {
        if (saldo < t.getMonto() + COMISION){
            System.out.println("No tines saldo suficiente");
        }else{
            saldo -= t.getMonto() + COMISION;
            this.historial.add(new Transaccion(""+Transaccion.contadorTransaccion, this.numeroCuenta, "CAJERO", TipoTransaccion.RETIRO, t.getMonto()));
            System.out.println("Retiro exitoso");
        }
    }
}
