package Modelo.Cuentas;// [REQUISITO: Herencia]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.EstadoCredito;
import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.SaldoInsuficienteException;
import java.io.*;

public class CuentaCorriente extends Cuenta implements Serializable{
    private final static double COMISION = 1500;
    private final double LINEA_CREDITO;
    private EstadoCredito estado;

    //---Constructor
    public CuentaCorriente(Double saldo, Cliente c, double credito) {
        super(saldo, c);
        this.LINEA_CREDITO = credito;
        estado = EstadoCredito.SIN_USAR;
    }

    //---Metodos
    @Override
    public boolean retirar(Transaccion t) {        
        saldo -= t.getMonto()+COMISION;
        historial.add(t);
        setEstado();
                if(t.getTipo() == TipoTransaccion.RETIRO){
            System.out.println("Retiro realizado");
        }
        return true;
    }
    
    @Override
    public void validarRetiro(double monto){
        if (monto + COMISION > saldo + LINEA_CREDITO) {
            throw new SaldoInsuficienteException();
        }
    }

    
    @Override
    public String mostrarInfo() {
        return "Corriente | " + super.mostrarInfo() + " | Saldo: $" + saldo + " | Linea: $" + LINEA_CREDITO + " | Estado: " + estado;
    }
    
    //---Gets y Sets
    public double getLINEA_CREDITO() {return LINEA_CREDITO;}
    
    public EstadoCredito getEstado() {return estado;}
    public void setEstado() {
        if (saldo <= -LINEA_CREDITO){
            this.estado = EstadoCredito.COPADO;
        } else if (saldo < 0) {
            this.estado = EstadoCredito.ACTIVO;
        } else {
            this.estado = EstadoCredito.SIN_USAR;
        }
    }
}