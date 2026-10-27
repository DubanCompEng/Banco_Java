
package Modelo.excepciones;

public class MaxRetirosException extends RuntimeException{
    public MaxRetirosException(){
        super("Maximos de retiros permitidos alcanzados.");
    }
}
