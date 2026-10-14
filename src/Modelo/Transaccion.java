package Modelo;

import Modelo.enums.TipoTransaccion;
import java.io.Serializable;

public class Transaccion implements Serializable{
    private String numeroTransaccion;
    private String cuentaOrigen;
    private String cuentaDestino;
    private TipoTransaccion tipo;
    private double monto;
    static int contadorTransaccion = 0;

    //--- Constructor
    public Transaccion(String numeroTransaccion, String cuentaOrigen, TipoTransaccion tipo, double monto) {
        this.numeroTransaccion = numeroTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        Transaccion.contadorTransaccion += 1;
        this.tipo = tipo;
        this.monto = monto;
    }

    //---Gets y Sets
    public String getNumeroTransaccion() {return numeroTransaccion;}
    public void setNumeroTransaccion(String numeroTransaccion) {this.numeroTransaccion = numeroTransaccion;}

    public String getCuentaOrigen() {return cuentaOrigen;}
    public void setCuentaOrigen(String cuentaOrigen) {this.cuentaOrigen = cuentaOrigen;}

    public String getCuentaDestino() {return cuentaDestino;}
    public void setCuentaDestino(String cuentaDestino) {this.cuentaDestino = cuentaDestino;}

    public TipoTransaccion getTipo() {return tipo;}
    public void setTipo(TipoTransaccion tipo) {this.tipo = tipo;}

    public double getMonto() {return monto;}
    public void setMonto(double monto) {this.monto = monto;}

    public int getContadorTransaccion() {return contadorTransaccion;}
    public void setContadorTransaccion(int contadorTransaccion) {Transaccion.contadorTransaccion = contadorTransaccion;}
}
