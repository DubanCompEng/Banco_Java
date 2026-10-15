package Modelo.excepciones;

public class MontoInvalidoException extends Exception {
    public MontoInvalidoException() 
    { 
        super("El monto ingresado no es válido"); }
}

