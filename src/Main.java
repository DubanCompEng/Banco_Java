
import Controlador.Banco;
import javax.swing.*;

public static void main(String[] args) {
    Banco banco = new Banco();
    SwingUtilities.invokeLater(() -> {
        new MenuPrincipal(banco).mostrar();
    });
}
