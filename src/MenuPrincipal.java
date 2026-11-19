
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
        JButton btn2 = new JButton("Registrar cliente");
        JButton btn3 = new JButton("Iniciar administrador");
        
        btn1.addActionListener(e -> {
        dispose();
            new LoginVista(banco).mostrar();
        });
        
        btn2.addActionListener(e -> {
            dispose();
            new RegistrarVista(banco).mostrar();});
        
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