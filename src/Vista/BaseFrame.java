package Vista;

import Controlador.Banco;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame {
    protected final Banco banco;

    public BaseFrame(String titulo, Banco banco) {
        super(titulo);
        this.banco = banco;
        setSize(850, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // guarda automáticamente cuando el usuario cierra la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                banco.guardarDatos();
            }
        });
    }
    
    public abstract void mostrar();
}