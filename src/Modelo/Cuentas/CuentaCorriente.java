package Modelo.Cuentas;// [REQUISITO: Herencia]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.EstadoCredito;
import Modelo.excepciones.SaldoInsuficienteException;
import java.io.*;

public class CuentaCorriente extends Cuenta implements Serializable{
    private final double COMISION = 1500;
    private final double LINEA_CREDITO;
    private EstadoCredito estado;

    //---Constructor
    public CuentaCorriente(String numeroCuenta, Double saldo, Cliente c, double credito) {
        super(numeroCuenta, saldo, c);
        this.LINEA_CREDITO = credito;
        estado = EstadoCredito.SIN_USAR;
    }

    //---Metodos
    @Override
    public void retirar(Transaccion t) {
        if(t.getMonto() + COMISION <= this.saldo + LINEA_CREDITO){
            this.saldo -= t.getMonto()+COMISION;
            setEstado();
        } else throw new SaldoInsuficienteException();
    }

    
    @Override
    public String toString() {
        return "";
    }
    
    //---Gets y Sets
    public double getLINEA_CREDITO() {return LINEA_CREDITO;}
    
    public EstadoCredito getEstado() {return estado;}
    public void setEstado() {
        if (saldo < 0)this.estado = EstadoCredito.ACTIVO;
        else if(saldo >= 0) this.estado = EstadoCredito.SIN_USAR; 
        else if((saldo + LINEA_CREDITO) == 0) this.estado = EstadoCredito.COPADO;
    }
}