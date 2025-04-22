package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaBuscar extends JFrame {
    private JTable tablaTareas;
    private DefaultTableModel modeloTabla;
    private JTextField campoBusqueda;
    private JButton btnBuscar;

    public VistaBuscar() {
        setTitle("Buscar Tareas");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"Id", "Título", "Descripción", "Fecha Vencimiento", "Prioridad", "Estado"};
        //Hacer la tabla no editable
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTareas = new JTable(modeloTabla);
        tablaTareas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(tablaTareas);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        campoBusqueda = new JTextField(30);
        btnBuscar = new JButton("Buscar");

        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.SOUTH);
    }

    public JTable getTablaTareas() {
        return tablaTareas;
    }
    public String getCampoBusqueda() {
        return campoBusqueda.getText();
    }
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaBuscar().setVisible(true));
    }
}