package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import modelo.Estado;
import modelo.ListaTareas;
import modelo.Prioridad;
import modelo.Tarea;
import vista.VistaFiltrar;

public class ControladorFiltrar implements ActionListener{
    
    private VistaFiltrar vistaFiltrar; 
    private ListaTareas tareas;

    public ControladorFiltrar(VistaFiltrar vistaFiltrar, ListaTareas listaTareas){ 
        this.vistaFiltrar = vistaFiltrar;
        this.tareas = listaTareas;

        this.vistaFiltrar.getBtnFiltrar().addActionListener(this);
        this.vistaFiltrar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Utils.actualizarTabla((DefaultTableModel)vistaFiltrar.getTablaTareas().getModel(), (ArrayList<Tarea>)tareas.listar());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vistaFiltrar.getBtnFiltrar() == e.getSource()){ 
            filtrar();
        }
        
    }

    private void filtrar(){ 
        Predicate<Tarea> filtro = tarea-> true; //Siempre selecciona todo por defecto

        if(vistaFiltrar.getChkEstado()){ 
            Estado estadoSeleccionado = Estado.valueOf(vistaFiltrar.getComboEstado());
            filtro = filtro.and(tarea -> tarea.estado == estadoSeleccionado);
        }
        if(vistaFiltrar.getChkPrioridad()){ 
            Prioridad prioridadSeleccionada = Prioridad.valueOf(vistaFiltrar.getComboPrioridad());
            filtro = filtro.and(tarea -> tarea.prioridad == prioridadSeleccionada);
        }
        if(vistaFiltrar.getChkFecha()){ 
            LocalDate fechaSeleccionada = vistaFiltrar.getSpinnerFecha();
            filtro = filtro.and(tarea -> tarea.getFechaVenciomiento() == fechaSeleccionada);
        }

        ArrayList<Tarea> tareasSeleccionadas = (ArrayList<Tarea> )tareas.filtrar(filtro);
        Utils.actualizarTabla((DefaultTableModel)vistaFiltrar.getTablaTareas().getModel(), tareasSeleccionadas);
    }

}
