
import Controlador.Banco;
import Modelo.Cliente;
import javax.swing.*;

public class LoginVista extends BaseFrame {
    
    public LoginVista(Banco banco) {
        super("Banco — Login", banco);
        setSize(400, 300);
        setResizable(false);
    }

    @Override
    public void mostrar() {
        JButton btnLogin = new JButton("Iniciar Sesion");
        
        btnLogin.addActionListener(e -> {
            //Autenticar
            dispose();
            new MenuCliente(banco).mostrar();
        });
        
        super.add(btnLogin);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}