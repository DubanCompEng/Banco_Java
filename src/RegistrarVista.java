
import Controlador.Banco;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegistrarVista extends BaseFrame {

    public RegistrarVista(Banco banco)  {
        super("Banco — Registrar", banco);
        setSize(400, 300);
        setResizable(false);
    }

    @Override
    public void mostrar() {
        JPanel formulario = new JPanel(new GridLayout(3, 2));

        JLabel lblRut = new JLabel("Rut:");
        lblRut.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField txtRut = new JTextField();
        formulario.add(lblRut);
        formulario.add(txtRut);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField txtNombre = new JTextField();
        formulario.add(lblNombre);
        formulario.add(txtNombre);
        
        JLabel lblClave = new JLabel("Clave:");
        lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField txtClave = new JTextField();
        formulario.add(lblClave);
        formulario.add(txtClave);
        
        super.add(formulario);
        
        JLabel lblMensaje = new JLabel("");
        super.add(lblMensaje);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
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
                    lblMensaje.setText("Jugador registrado: " + nombre + " " + apellido);
                }
            }
        });panelBotones.add(btnRegistrar);
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener((ActionEvent e) -> {
            txtRut.setText("");
            txtNombre.setText("");
            txtClave.setText("");
        });panelBotones.add(btnLimpiar);
        
        super.add(panelBotones);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
