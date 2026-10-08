package Modelo.Cuentas;// [REQUISITO: Herencia]

import Modelo.Cliente;
import Modelo.enums.EstadoCredito;
import Modelo.enums.TipoCuenta;

import java.io.*;

public class CuentaCorriente extends Cuenta implements Serializable{
    private final double COMISION = 4500;
    private double LINEA_CREDITO = 5000000;
    private EstadoCredito estado;

    public CuentaCorriente(String numeroCuenta, Double saldo, Cliente c, TipoCuenta tipo) {
        super(numeroCuenta, saldo, c, tipo);
        estado = estado.ACTIVO;
    }

    @Override
    public void retirar(double monto) {
        if(monto > super.saldo + LINEA_CREDITO){
            super.saldo -= monto;
        }
    }

    @Override
    public String toString() {
        return "";
    }
}