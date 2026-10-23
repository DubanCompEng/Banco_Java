    package Modelo.Cuentas;// [REQUISITO: Herencia]

import Modelo.Cliente;
import Modelo.Transaccion;
import Modelo.enums.EstadoCredito;
import Modelo.excepciones.SaldoInsuficienteException;
import java.io.*;

public class CuentaCorriente extends Cuenta implements Serializable{
    private static double COMISION = 1500;
    private static double LINEA_CREDITO;
    private EstadoCredito estado;

    //---Constructor
    public CuentaCorriente(String numeroCuenta, Double saldo, Cliente c, double credito) {
        super(numeroCuenta, saldo, c);
        CuentaCorriente.LINEA_CREDITO = credito;
        estado = EstadoCredito.SIN_USAR;
    }

    //---Metodos
    @Override
    public boolean retirar(Transaccion t) {
        if (t.getMonto() <= 0) {
            throw new Modelo.excepciones.MontoInvalidoException();
        }
        double total = t.getMonto() + COMISION;
        if (total > saldo + LINEA_CREDITO) {
            throw new SaldoInsuficienteException();
        }
        
        saldo -= total;
        historial.add(t);
        setEstado();
        System.out.println("Retiro o transferencia realizado con exito.");
        return true;
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