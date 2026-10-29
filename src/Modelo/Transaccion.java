package Modelo;

import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.MontoInvalidoException;
import Modelo.interfaces.Listable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion implements Serializable, Listable{
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    private final String numeroTransaccion;
    private final String cuentaOrigen;
    private final String cuentaDestino;
    private final TipoTransaccion tipo;
    private final double monto;
    private final LocalDateTime fecha;
    private static int contadorTransaccion = 0;

    //--- Constructor
    public Transaccion(String cuentaOrigen, String cuentaDestino, TipoTransaccion tipo, double monto) throws MontoInvalidoException {
        if (monto <= 0) {
            throw new MontoInvalidoException();
        }
        
        contadorTransaccion++;
        this.numeroTransaccion = "0"+contadorTransaccion; 
        this.fecha = LocalDateTime.now();                               
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.tipo = tipo;
        this.monto = monto;
    }
    
    @Override
    public String mostrarInfo() {
        return "numero de transaccion: " + numeroTransaccion + " | realizada el " + fecha.format(FORMATO) + " | " + tipo + " | Origen: " + cuentaOrigen + " | Destino: " + cuentaDestino + " | Monto: $" + monto;
    }

    //---Gets
    public String getNumeroTransaccion() {return numeroTransaccion;}
    public String getCuentaOrigen() {return cuentaOrigen;}
    public String getCuentaDestino() {return cuentaDestino;}
    public TipoTransaccion getTipo() {return tipo;}
    public double getMonto() {return monto;}
    public int getContadorTransaccion() {return contadorTransaccion;}
    public static void setContadorTransaccion(int valor){ Transaccion.contadorTransaccion = valor; }
    public LocalDateTime getFecha() {return fecha;}

    
}
