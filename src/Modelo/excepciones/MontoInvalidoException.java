package Modelo.excepciones;

public class MontoInvalidoException extends RuntimeException {
    public MontoInvalidoException(){ 
        super("El monto ingresado no es valido"); 
    }
}

