package Modelo.excepciones;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(){ 
        super("Saldo insuficiente"); 
    }
}
