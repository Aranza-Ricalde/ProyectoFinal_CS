package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.CampoVacioException;
import modelo.Estado;
import modelo.FechaPasadaException;
import modelo.ListaTareas;
import modelo.Prioridad;
import modelo.Tarea;
import vista.VistaFormularioTarea;

public abstract class ControladorFormularioTarea implements ActionListener{
    protected VistaFormularioTarea vistaFormularioTarea;
    protected ControladorCRUD controladorCRUD;  
    protected ListaTareas tareas;

    public ControladorFormularioTarea(VistaFormularioTarea vistaFormularioTarea, ControladorCRUD controladorCRUD, ListaTareas listaTareas){
        this.vistaFormularioTarea = vistaFormularioTarea;
        this.controladorCRUD = controladorCRUD;
        this.tareas = listaTareas;
        vistaFormularioTarea.getBtnAceptar().addActionListener(this);
        vistaFormularioTarea.getBtnCancelar().addActionListener(this); 
        vistaFormularioTarea.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public abstract void alAceptar(Tarea tarea) throws Exception;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vistaFormularioTarea.getBtnAceptar()== e.getSource()){
            try {
                alAceptar(leerTarea());
            } catch (CampoVacioException e1) {
                JOptionPane.showMessageDialog(vistaFormularioTarea,e1.getMessage(),"Campo vacío", JOptionPane.WARNING_MESSAGE);
            } catch (FechaPasadaException e1) {
                JOptionPane.showMessageDialog(vistaFormularioTarea,e1.getMessage(),"Fecha inválida",JOptionPane.WARNING_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(vistaFormularioTarea,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            controladorCRUD.actualizarTabla();
        }
        if(vistaFormularioTarea.getBtnCancelar() == e.getSource()){ 
            vistaFormularioTarea.setVisible(false);
        }
    }

    private Tarea leerTarea() throws CampoVacioException, FechaPasadaException{ 
        String titulo = vistaFormularioTarea.getTitulo();
        String descripcion = vistaFormularioTarea.getDescripcion();
        LocalDate fechaVencimiento = vistaFormularioTarea.getFecha(); 
        Prioridad prioridad =  Prioridad.valueOf(vistaFormularioTarea.getPrioridad()); 
        Estado estado = Estado.valueOf(vistaFormularioTarea.getEstado());

        Tarea tareaActual = new Tarea(titulo);
        tareaActual.setFechaVenciminto(fechaVencimiento);
        tareaActual.descripcion = descripcion;
        tareaActual.prioridad = prioridad;
        tareaActual.estado = estado;
        return tareaActual;
    }

}


