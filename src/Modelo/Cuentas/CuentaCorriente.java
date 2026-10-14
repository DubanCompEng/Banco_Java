package Modelo.Cuentas;// [REQUISITO: Herencia]

import Modelo.Cliente;
import Modelo.enums.EstadoCredito;
import java.io.*;

public class CuentaCorriente extends Cuenta implements Serializable{
    private final double COMISION = 4500;
    private double LINEA_CREDITO = 5000000;
    private EstadoCredito estado;

    //---Constructor
    public CuentaCorriente(String numeroCuenta, Double saldo, Cliente c) {
        super(numeroCuenta, saldo, c);
        estado = EstadoCredito.ACTIVO;
    }

    //---Metodos
    @Override
    public void retirar(double monto) {
        if(monto + COMISION <= this.saldo + LINEA_CREDITO){
            this.saldo -= monto;
        }
    }

    @Override
    public String toString() {
        return "";
    }
    
    //---Gets y Sets
    public double getLINEA_CREDITO() {return LINEA_CREDITO;}
    public void setLINEA_CREDITO(double LINEA_CREDITO) {this.LINEA_CREDITO = LINEA_CREDITO;}

    public EstadoCredito getEstado() {return estado;}
    public void setEstado(EstadoCredito estado) {this.estado = estado;}
    
}