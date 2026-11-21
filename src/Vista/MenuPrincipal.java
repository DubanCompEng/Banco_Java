package Vista;

import Controlador.Banco;
import java.awt.*;
import javax.swing.*;

public class MenuPrincipal extends BaseFrame {
    public MenuPrincipal(Banco banco) {
        super("Banco — Menú", banco);
        setSize(600, 400);
    }
    
    @Override
    public void mostrar() {
        JLabel lblTitulo = new JLabel("Menu Principal");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12,0,5,0));
        super.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel botones = new JPanel(new GridLayout(3,0, 8, 8));
        botones.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        
        JButton btn1 = new JButton("Iniciar sesion");
        btn1.addActionListener(e -> {
            dispose();
            new LoginVista(banco).mostrar();
        });
        botones.add(btn1);
        
        JButton btn2 = new JButton("Registrar cliente");
        btn2.addActionListener(e -> {
            dispose();
            new RegistrarVista(banco).mostrar();
        });
        botones.add(btn2);
        
        JButton btn3 = new JButton("Iniciar administrador");
        btn3.addActionListener(e -> {
            dispose();
            new MenuAdmin(banco).mostrar();
        });
        botones.add(btn3, BorderLayout.SOUTH);
        
        super.add(botones);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}