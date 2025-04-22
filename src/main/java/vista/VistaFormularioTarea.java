package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class VistaFormularioTarea extends JDialog {

    protected JTextField txtTitulo, txtId;
    protected JTextArea txtDescripcion;
    protected JSpinner spinnerFecha;
    protected JComboBox<String> comboPrioridad;
    protected JComboBox<String> comboEstado;
    protected JButton btnAceptar, btnCancelar; 

    public VistaFormularioTarea(JFrame parent) {
        super(parent, "Formulario Tarea", true);
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());


        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtTitulo = new JTextField();
        
        txtDescripcion = new JTextArea(12, 20);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);


        spinnerFecha = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd");
        spinnerFecha.setEditor(dateEditor);


        comboPrioridad = new JComboBox<>(new String[]{"ALTA", "MEDIA", "BAJA"});
        comboEstado = new JComboBox<>(new String[]{"PENDIENTE", "EN_PROGRESO", "COMPLETADA"});

        panel.add(new JLabel("Id:"));
        panel.add(txtId);
        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("Descripción:"));
        panel.add(scrollDesc);
        panel.add(new JLabel("Fecha de vencimiento:"));
        panel.add(spinnerFecha);
        panel.add(new JLabel("Prioridad:"));
        panel.add(comboPrioridad);
        panel.add(new JLabel("Estado:"));
        panel.add(comboEstado);

        JPanel panelBotones = new JPanel();
        this.btnAceptar = new JButton("Agregar");
        this.btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
    public String getId() {
        return txtId.getText().trim();
    }


    public String getTitulo() {
        return txtTitulo.getText().trim();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public LocalDate getFecha() {
        Date date = (Date) spinnerFecha.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getPrioridad() {
        return (String) comboPrioridad.getSelectedItem();
    }

    public String getEstado() {
        return (String) comboEstado.getSelectedItem();
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }
    
    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}

