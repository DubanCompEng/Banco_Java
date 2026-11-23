package Vista;


import Controlador.Banco;
import Modelo.Cliente;
import java.awt.*;
import javax.swing.*;

public class MenuCliente extends BaseFrame {

    public MenuCliente(Banco banco, Cliente cliente) {
        super("Banco — Login", banco);
        setSize(400, 300);
        setResizable(false);
    }

    @Override
    public void mostrar() {
        
        JPanel panelSuperior = new JPanel();
        
        JLabel lblTitulo = new JLabel("Menu Cliente");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12,0,5,0));
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setHorizontalAlignment(SwingConstants.RIGHT);
        btnSalir.addActionListener(e ->{
            dispose();
            new MenuPrincipal(banco).mostrar();
        });
        panelSuperior.add(btnSalir, BorderLayout.WEST);
        
        super.add(panelSuperior, BorderLayout.NORTH);
        
        JPanel botones = new JPanel(new GridLayout(6,0, 8, 8));
        botones.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        
        JButton btn1 = new JButton("Ver Perfil");
        btn1.addActionListener(e -> {
            dispose();
            //Panel del perfil con saldo y transacciones el scrolbr
        });
        botones.add(btn1);
        
        JButton btn2 = new JButton("Administrar cuentas");
        btn2.addActionListener(e -> {
            dispose();
            //Lista desplegable o seleccionador de cunetas
        });
        botones.add(btn2);
        
        JButton btn3 = new JButton("Realizar transaccion");
        btn3.addActionListener(e -> {
            dispose();
            //Formulario de transaccion
        });
        botones.add(btn3);
        
        JButton btn4 = new JButton("Realizar retiro");
        btn4.addActionListener(e -> {
            dispose();
            //Formulario de transaccion
        });
        botones.add(btn4);
    
        JButton btn5 = new JButton("Depositar");
        btn5.addActionListener(e -> {
            dispose();
            //Formulario de transaccion
        });
        botones.add(btn5);
        
        super.add(botones, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
