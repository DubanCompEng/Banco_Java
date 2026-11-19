
import Controlador.Banco;
import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame {
    protected Banco banco;
    
    public BaseFrame(String titulo, Banco banco) {
        super(titulo);
        this.banco = banco;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}
    
    public abstract void mostrar();
}