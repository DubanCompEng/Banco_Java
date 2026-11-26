package Vista; //Listo

import java.awt.*;
import javax.swing.*;
import Controlador.*;
import Modelo.Cliente;
import Modelo.Transaccion;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class MenuAdmin extends BaseFrame{

    public MenuAdmin(Banco banco) {
        super("Banco — Menú Admin", banco);
        setSize(600, 400);
    }

    @Override
    public void mostrar() {
        JLabel lblTitulo = new JLabel("Menu Administrador");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        super.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel panelCentro = new JPanel(new GridLayout(5, 1));
        
        JButton btn1 = new JButton("Ranking de clientes");
        btn1.addActionListener(e->{
            
            String[] colummnas = {"Nombre", "Ranking"};
            DefaultTableModel modelo = new DefaultTableModel(colummnas,0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            for(Cliente c: banco.rankingClientes()){
                Object[] fila = {
                    c.getNombre(),c.getValorRanking()
                };
                modelo.addRow(fila);
            }
            crearTabla(modelo, banco.rankingClientes());
        });
        panelCentro.add(btn1);
        
        JButton btn2 = new JButton("Historial de Transacciones");
        btn2.addActionListener(e -> {
            //  Definir los encabezados de las columnas
            String[] columnas = {"Fecha", "Tipo", "Monto", "Cuenta Origen", "Cuenta Destino"};
            
            // Crear el modelo (bloqueando la edición de celdas por el usuario)
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            //  Llenar el modelo con los datos de tu objeto 'banco'            
            for (Transaccion t : banco.getHistorial()) {
                Object[] fila = {
                    t.getFecha(),
                    t.getTipo(),
                    "$" + t.getMonto(),
                    t.getCuentaOrigen(),
                    t.getCuentaDestino()
                };
                modelo.addRow(fila);
            }
            crearTabla(modelo, banco.getHistorial());
        });
        panelCentro.add(btn2);
        
        JButton btn3 = new JButton("Clientes Ordenados por nombre");
        btn3.addActionListener(e->{
            
            String[] columnas = {"Nombre", "Rut"};
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }};
            
            for (Cliente c : banco.clientesOrdenadosPorNombre()) {
                Object[] fila = {
                    c.getNombre(),
                    c.getRut()
                };
                modelo.addRow(fila);
            };
            
            crearTabla(modelo,banco.clientesOrdenadosPorNombre());
        });
        panelCentro.add(btn3);
        
        JButton btn5 = new JButton("Salir");
        btn5.addActionListener(e->{
            dispose();
            new MenuPrincipal(banco).mostrar();
        });
        panelCentro.add(btn5);
        
        super.add(panelCentro, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void crearTabla(DefaultTableModel modelo, ArrayList lista){
            //  Crear la tabla y meterla en un JScrollPane
            JTable tabla = new JTable(modelo);
            JScrollPane scrollPane = new JScrollPane(tabla);

            //  Mostrar en una ventana emergente 
            JDialog dialogTabla = new JDialog(this, "Historial de Transacciones", true);
            dialogTabla.setSize(650, 350);
            dialogTabla.setLocationRelativeTo(this);
            dialogTabla.add(scrollPane, BorderLayout.CENTER);
            
            JPanel botones = new JPanel(new GridLayout(3,0));
            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(ev -> dialogTabla.dispose());
            botones.add(btnCerrar);
            
            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                String nombre = (String) modelo.getValueAt(fila, 0);
                
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this,"Seleccione para eliminar");
                    return;
                }
                // Confirmar antes de eliminar
                int resp = JOptionPane.showConfirmDialog(this,"¿Confirmar eliminacion "+ JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    modelo.removeRow(fila); // modelo de tabla
                    lista.remove(1);
                     //Falta eliminar el dato del backend
                }
            
            dialogTabla.setVisible(true);
            });
            botones.add(btnEliminar);
            
            dialogTabla.add(botones, BorderLayout.SOUTH);
            
            dialogTabla.setVisible(true);
    }
}
    
