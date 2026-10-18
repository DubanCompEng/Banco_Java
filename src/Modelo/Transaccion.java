package Modelo;

import Modelo.enums.TipoTransaccion;
import Modelo.interfaces.Listable;
import java.io.Serializable;

public class Transaccion implements Serializable, Listable{
    private final String numeroTransaccion;
    private final String cuentaOrigen;
    private final String cuentaDestino;
    private final TipoTransaccion tipo;
    private final double monto;
    public static int contadorTransaccion = 0;

    //--- Constructor
    public Transaccion(String numeroTransaccion,String cuentaOrigen, String cuentaDestino, TipoTransaccion tipo, double monto) {
        this.numeroTransaccion = numeroTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        Transaccion.contadorTransaccion++;
        this.tipo = tipo;
        this.monto = monto;
    }
    
    @Override
    public String mostrarInfo(){
        return ""+ tipo + " |Origen: " + cuentaOrigen + "|Destino: " + cuentaDestino + "|Monto: " + monto;
    }

    //---Gets
    public String getNumeroTransaccion() {return numeroTransaccion;}
    public String getCuentaOrigen() {return cuentaOrigen;}
    public String getCuentaDestino() {return cuentaDestino;}
    public TipoTransaccion getTipo() {return tipo;}
    public double getMonto() {return monto;}
    public int getContadorTransaccion() {return contadorTransaccion;}
}
