package Vista;  //Listo


import Controlador.Banco;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuRegistrar extends BaseFrame {

    public MenuRegistrar(Banco banco)  {
        super("Banco — Registrar", banco);
        setSize(400, 300);
        setResizable(false);
    }

    @Override
    public void mostrar() {
        JLabel lblTitulo = new JLabel("Registrar nuevo cliente");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        super.add(lblTitulo, BorderLayout.NORTH);
        
        
        JPanel formulario = new JPanel(new GridLayout(3, 2));

        JLabel lblRut = new JLabel("Rut:");
        lblRut.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField txtRut = new JTextField();
        formulario.add(lblRut);
        formulario.add(txtRut);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField txtNombre = new JTextField();
        formulario.add(lblNombre);
        formulario.add(txtNombre);
        
        JLabel lblClave = new JLabel("Clave:");
        lblClave.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField txtClave = new JTextField();
        formulario.add(lblClave);
        formulario.add(txtClave);
        
        super.add(formulario, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel lblMensaje = new JLabel("");
        lblMensaje.setHorizontalAlignment(SwingConstants.RIGHT);
        panelBotones.add(lblMensaje);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtRut.getText().trim();
                String apellido = txtNombre.getText().trim();
                String correo = txtClave.getText().trim();
                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                    lblMensaje.setText("Completa todos los campos.");
                }else {
                    JOptionPane frame = new JOptionPane();
                    int r = JOptionPane.showConfirmDialog(frame, "¿Confirmar registro del cliente?","Confirmar",JOptionPane.YES_NO_OPTION);
                    if (r == JOptionPane.YES_OPTION) {
                        lblMensaje.setText("Usuario Registrado con exito.");
                    }
                }
            }
        });panelBotones.add(btnRegistrar);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setHorizontalAlignment(SwingConstants.RIGHT);
        btnSalir.addActionListener(e ->{
            dispose();
            new MenuPrincipal(banco).mostrar();
        });
        panelBotones.add(btnSalir);
        super.add(panelBotones, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
