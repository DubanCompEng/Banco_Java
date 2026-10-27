package Modelo;

import Modelo.enums.TipoTransaccion;
import Modelo.excepciones.MontoInvalidoException;
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
    public static int contadorTransaccion = 0;

    //--- Constructor
    public Transaccion(String cuentaOrigen, String cuentaDestino, TipoTransaccion tipo, double monto) throws MontoInvalidoException {
        if (monto <= 0) {
            throw new MontoInvalidoException();
        }
        
        this.numeroTransaccion = cuentaOrigen +"0"+contadorTransaccion; // cada transaccion de cuentas empezaba en 00 agregue 
        this.fecha = LocalDateTime.now();                               // cuenta de origen para que no sean iguales.
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.tipo = tipo;
        this.monto = monto;
        contadorTransaccion++;
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
    public LocalDateTime getFecha() {return fecha;}

    
}
