package Modelo;

import Modelo.enums.EstadoTransaccion;
import Modelo.enums.TipoTransaccion;
import Modelo.interfaces.Listable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion implements Serializable, Listable{
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    private final String numeroTransaccion;
    private final String cuentaOrigen;
    private final String cuentaDestino;
    private final TipoTransaccion tipo;
    private final double monto;
    private final LocalDateTime fecha;
    private EstadoTransaccion estado;
    public static int contadorTransaccion = 0;

    //--- Constructor
    public Transaccion(String numeroTransaccion, String cuentaOrigen, String cuentaDestino, TipoTransaccion tipo, double monto, EstadoTransaccion estado) {
        this.numeroTransaccion = numeroTransaccion;
        this.fecha = LocalDateTime.now();
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.tipo = tipo;
        this.monto = monto;
        this.estado = estado;
        contadorTransaccion++;
    }
    
    @Override
    public String mostrarInfo() {
        return numeroTransaccion + " | " + fecha.format(FORMATO) + " | " + tipo + " | " + estado + " | Origen: " + cuentaOrigen + " | Destino: " + cuentaDestino + " | Monto: $" + monto;
    }

    //---Gets
    public String getNumeroTransaccion() {return numeroTransaccion;}
    public String getCuentaOrigen() {return cuentaOrigen;}
    public String getCuentaDestino() {return cuentaDestino;}
    public TipoTransaccion getTipo() {return tipo;}
    public double getMonto() {return monto;}
    public int getContadorTransaccion() {return contadorTransaccion;}
    public static DateTimeFormatter getFORMATO() {return FORMATO;}
    public LocalDateTime getFecha() {return fecha;}
    public EstadoTransaccion getEstado() {return estado;}
    public void setEstado(EstadoTransaccion estado) {this.estado = estado;}
    
}
