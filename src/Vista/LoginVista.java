package Vista;


import Controlador.Banco;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class LoginVista extends BaseFrame {
    
    public LoginVista(Banco banco) {
        super("Banco — Login", banco);
        setSize(400, 300);
        setResizable(false);
    }

    @Override
    public void mostrar(){
        JLabel lblTitulo = new JLabel("Login");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        super.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel formulario = new JPanel(new GridLayout(2,2,8,8));
        formulario.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        
        JLabel lblRut = new JLabel("Rut");
        JTextField txtRut = new JTextField();
        formulario.add(lblRut);
        formulario.add(txtRut);
        
        JLabel lblClave = new JLabel("Clave");
        JTextField txtClave = new JPasswordField();
        formulario.add(lblClave);
        formulario.add(txtClave);
        
        JPanel botones = new JPanel(new BorderLayout(2, 1));
        botones.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        
        JLabel lblText = new JLabel(" ");
        lblText.setHorizontalAlignment(SwingConstants.CENTER);
        lblText.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        
        JButton btn1 = new JButton("Iniciar sesion");
        btn1.addActionListener(e -> {
            if (banco.autenticar(txtRut.getText(), txtClave.getText()) != null){
                dispose();
                new MenuCliente(banco, banco.autenticar(txtRut.getText(), txtClave.getText())).mostrar();
            }else{
                lblText.setText("Las credenciales no coinciden.");
            }
        });
        
        botones.add(lblText, BorderLayout.NORTH);
        botones.add(btn1, BorderLayout.SOUTH);
        
        super.add(formulario, BorderLayout.CENTER);
        super.add(botones, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setVisible(true);}
}