package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class VistaFiltrar extends JFrame {
    private JTable tablaTareas;
    private DefaultTableModel modeloTabla;

    private JCheckBox chkEstado;
    private JComboBox<String> comboEstado;

    private JCheckBox chkPrioridad;
    private JComboBox<String> comboPrioridad;

    private JCheckBox chkFecha;
    private JSpinner spinnerFecha;

    private JButton btnFiltrar;

    public VistaFiltrar() {
        setTitle("Filtrar Tareas");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"Id", "Título", "Descripción", "Fecha Vencimiento", "Prioridad", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaTareas = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaTareas);
        add(scroll, BorderLayout.CENTER);

        //Panel inferior con filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        chkEstado = new JCheckBox("Estado:");
        comboEstado = new JComboBox<>(new String[]{"PENDIENTE", "EN_PROGRESO", "COMPLETADA"});
        panelFiltros.add(chkEstado);
        panelFiltros.add(comboEstado);

        chkPrioridad = new JCheckBox("Prioridad:");
        comboPrioridad = new JComboBox<>(new String[]{"ALTA", "MEDIA", "BAJA"});
        panelFiltros.add(chkPrioridad);
        panelFiltros.add(comboPrioridad);

        chkFecha = new JCheckBox("Fecha:");
        spinnerFecha = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd");
        spinnerFecha.setEditor(editor);
        panelFiltros.add(chkFecha);
        panelFiltros.add(spinnerFecha);

        btnFiltrar = new JButton("Filtrar");
        panelFiltros.add(btnFiltrar);

        add(panelFiltros, BorderLayout.SOUTH);
    }

    public JTable getTablaTareas() {
        return tablaTareas;
    }

    public boolean getChkEstado() {
        return chkEstado.isSelected();
    }

    public String getComboEstado() {
        return (String)comboEstado.getSelectedItem();
    }

    public boolean getChkPrioridad() {
        return chkPrioridad.isSelected();
    }

    public String getComboPrioridad() {
        return (String) comboPrioridad.getSelectedItem();
    }

    public boolean getChkFecha() {
        return chkFecha.isSelected();
    }

    public LocalDate getSpinnerFecha() {
        Date date = (Date) spinnerFecha.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaFiltrar().setVisible(true));
    }
}
