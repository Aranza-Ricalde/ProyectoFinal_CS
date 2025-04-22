package vista;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JFrame;


public class VistaModificarTarea extends VistaFormularioTarea {
    

    public VistaModificarTarea(JFrame parent) {
        super(parent);
        btnAceptar.setText("Modificar");
    }
    public void setId(String id) {
        txtId.setText(id);
    }

    public void setTitulo(String titulo) {
        txtTitulo.setText(titulo);
    }
    
    public void setDescripcion(String descripcion) {
        txtDescripcion.setText(descripcion);
    }
    
    public void setFecha(LocalDate fecha) {
        Date date = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        spinnerFecha.setValue(date);
    }
    
    public void setPrioridad(int index) {
        comboPrioridad.setSelectedIndex(index);
    }
    
    public void setEstado(int index) {
        comboEstado.setSelectedIndex(index);
    }



}
