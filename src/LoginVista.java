
import Controlador.Banco;
import Modelo.Cliente;
import javax.swing.*;

public class LoginVista extends BaseFrame {
    
    public LoginVista(Banco banco) {
        super("Sistema — Login", banco);
        setSize(400, 300);
        setResizable(false);
    }

    @Override
    public void mostrar() {
        JButton btnLogin = new JButton("Iniciar Sesion");
        
        btnLogin.addActionListener(e -> {
            
            dispose();
            new MenuPrincipal(banco).mostrar();
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }
}