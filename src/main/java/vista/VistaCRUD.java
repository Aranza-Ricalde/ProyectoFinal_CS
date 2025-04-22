package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaCRUD extends javax.swing.JFrame {
    private JTable tablaTareas;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnModificar, btnEliminar, btnBuscar, btnFiltrar,btnOrdenarFecha, btnOrdenarPrioridad;

    public VistaCRUD() {
        setTitle("Gestor de Tareas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabla
        String[] columnas = {"Id","Título", "Descripción", "Fecha Vencimiento", "Prioridad", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTareas = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaTareas);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnFiltrar = new JButton("Filtrar");
        btnOrdenarFecha = new JButton("Ordenar por Fecha");
        btnOrdenarPrioridad = new JButton("Ordenar por Prioridad");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnFiltrar);
        panelBotones.add(btnOrdenarFecha);
        panelBotones.add(btnOrdenarPrioridad);
        tablaTareas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JTable getTableTareas(){ 
        return tablaTareas;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }
    
    public JButton getBtnModificar() {
        return btnModificar;
    }
    
    public JButton getBtnEliminar() {
        return btnEliminar;
    }
    
    public JButton getBtnBuscar() {
        return btnBuscar;
    }
    
    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }
    
    public JButton getBtnOrdenarFecha() {
        return btnOrdenarFecha;
    }
    
    public JButton getBtnOrdenarPrioridad() {
        return btnOrdenarPrioridad;
    }

}
