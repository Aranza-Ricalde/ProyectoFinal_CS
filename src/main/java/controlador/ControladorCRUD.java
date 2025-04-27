package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.ListaTareas;
import modelo.PersistenciaTareas;
import modelo.Tarea;
import vista.VistaAgregarTarea;
import vista.VistaBuscar;
import vista.VistaCRUD;
import vista.VistaFiltrar;
import vista.VistaModificarTarea;

public class ControladorCRUD implements ActionListener, WindowListener{

    private VistaCRUD vistaCRUD;
    private VistaAgregarTarea vistaAgregarTarea; 
    private VistaModificarTarea vistaModificarTarea;
    private VistaBuscar vistaBuscar;
    private VistaFiltrar vistaFiltrar;
    private ListaTareas tareas; 

    private int ultimaSeleccion = 0;
    public ControladorCRUD(VistaCRUD vistaCRUD, VistaAgregarTarea vistaAgregarTarea, VistaModificarTarea vistaModificarTarea, VistaBuscar vistaBuscar, VistaFiltrar vistaFiltrar,ListaTareas listaTareas){ 
        this.tareas = listaTareas;

        this.vistaCRUD = vistaCRUD;
        this.vistaAgregarTarea = vistaAgregarTarea;
        this.vistaModificarTarea = vistaModificarTarea;
        this.vistaBuscar = vistaBuscar;
        this.vistaFiltrar = vistaFiltrar;

        vistaCRUD.getBtnAgregar().addActionListener(this);
        vistaCRUD.getBtnModificar().addActionListener(this);
        vistaCRUD.getBtnEliminar().addActionListener(this);
        vistaCRUD.getBtnBuscar().addActionListener(this);
        vistaCRUD.getBtnFiltrar().addActionListener(this);
        vistaCRUD.getBtnOrdenarFecha().addActionListener(this);
        vistaCRUD.getBtnOrdenarPrioridad().addActionListener(this);

        vistaCRUD.addWindowListener(this);
        actualizarTabla();
    }


    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaCRUD.getBtnAgregar() == evento.getSource()){ 
            vistaAgregarTarea.setVisible(true);
        }
        if(vistaCRUD.getBtnModificar() == evento.getSource()){ 
            modificarTarea();
        }
        if(vistaCRUD.getBtnEliminar()== evento.getSource()){
            eliminarTarea();
        }
        if(vistaCRUD.getBtnBuscar() == evento.getSource()){
            vistaBuscar.setVisible(true);
        }
        if(vistaCRUD.getBtnFiltrar() == evento.getSource()){ 
            vistaFiltrar.setVisible(true);
        }
        if(vistaCRUD.getBtnOrdenarFecha()== evento.getSource()){
            tareas.ordenarFechaVencimiento();
            actualizarTabla();
        }
        if(vistaCRUD.getBtnOrdenarPrioridad()== evento.getSource()){
            tareas.ordenarPrioridad();
            actualizarTabla();
        }


        


    }

    public int getFilaSeleccionada(){
        int seleccion = vistaCRUD.getTableTareas().getSelectedRow();
        if(seleccion != -1){ 
            this.ultimaSeleccion = seleccion;
            return seleccion;
        }else{ 
            return ultimaSeleccion;
        }
    }

    private void modificarTarea(){ 
        int filaTabla = getFilaSeleccionada();
        if(filaTabla == -1){ 
            JOptionPane.showMessageDialog(null, "Seleccione una tarea para modificar", "Aviso!", JOptionPane.WARNING_MESSAGE);
            vistaModificarTarea.setVisible(false);
            return;
        }else{ }
        Tarea actual = tareas.listar().get(filaTabla);
        vistaModificarTarea.setId(actual.id.toString());
        vistaModificarTarea.setTitulo(actual.getTitulo());
        vistaModificarTarea.setDescripcion(actual.descripcion);
        vistaModificarTarea.setFecha(actual.getFechaVencimiento());
        vistaModificarTarea.setPrioridad(actual.prioridad.valor);
        vistaModificarTarea.setEstado(2);
        vistaModificarTarea.setVisible(true);
    }


    private void eliminarTarea(){
        int filaTabla = getFilaSeleccionada(); 
        if(filaTabla == -1){ 
            JOptionPane.showMessageDialog(null, "Seleccione una tarea para eliminar", "Aviso!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
            vistaCRUD,
            "¿Deseas eliminar esta tarea?\n" + tareas.listar().get(filaTabla).getTitulo(),
            "Eliminar Tarea",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if(opcion == JOptionPane.YES_OPTION){ 
            tareas.eliminar(filaTabla);
            Utils.actualizarTabla((DefaultTableModel) vistaCRUD.getTableTareas().getModel(),(ArrayList<Tarea>)tareas.listar());
        }

    }



    public void actualizarTabla(){
        Utils.actualizarTabla((DefaultTableModel) vistaCRUD.getTableTareas().getModel(),(ArrayList<Tarea>)tareas.listar());
    }
    @Override
    public void windowClosing(WindowEvent e) {
        try {
            PersistenciaTareas.guardarListaTareas(this.tareas);
            JOptionPane.showMessageDialog(null, "Tareas guardadas", "Gurdando", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error al guardar tareas", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void windowOpened(WindowEvent e){}
    @Override
    public void windowClosed(WindowEvent e){}
    @Override
    public void windowIconified(WindowEvent e){}
    @Override
    public void windowDeiconified(WindowEvent e){}
    @Override
    public void windowActivated(WindowEvent e){}
    @Override
    public void windowDeactivated(WindowEvent e){}




}
