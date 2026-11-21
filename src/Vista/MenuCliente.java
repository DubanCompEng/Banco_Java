package Vista;


import Controlador.Banco;
import Modelo.Cliente;
import javax.swing.*;

public class MenuCliente extends BaseFrame {

    public MenuCliente(Banco banco, Cliente cliente) {
        super("Banco — Login", banco);
        setSize(400, 300);
        setResizable(false);
    }

    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
