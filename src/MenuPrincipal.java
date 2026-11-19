
import Controlador.Banco;
import javax.swing.*;

public class MenuPrincipal extends BaseFrame {
    public MenuPrincipal(Banco banco) {
        super("Banco — Menú", banco);
        setSize(600, 400);
    }
    
    @Override
    public void mostrar() {
        JTextField titulo = new JTextField("Menu Principal");
        
        JButton btn1 = new JButton("Iniciar sesion");
        btn1.addActionListener(e -> {
            dispose();
            new LoginVista(banco).mostrar();
        });

        JButton btn2 = new JButton("Registrar cliente");
        btn2.addActionListener(e -> {
            dispose();
            new RegistrarVista(banco).mostrar();
        });
        
        JButton btn3 = new JButton("Iniciar administrador");
        btn3.addActionListener(e -> {
            dispose();
            new MenuAdmin(banco).mostrar();
        });
        
        super.add(titulo);
        super.add(btn1);
        super.add(btn2);
        super.add(btn3);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}