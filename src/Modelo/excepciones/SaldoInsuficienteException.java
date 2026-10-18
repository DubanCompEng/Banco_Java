package Modelo.excepciones;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(){ 
        super("Saldo insuficiente"); 
    }
}
