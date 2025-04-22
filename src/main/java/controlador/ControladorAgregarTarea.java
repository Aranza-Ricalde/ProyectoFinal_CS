package controlador;


import javax.swing.JOptionPane;

import modelo.ListaTareas;
import modelo.Tarea;
import vista.VistaAgregarTarea;


public class ControladorAgregarTarea extends ControladorFormularioTarea{
 

    public ControladorAgregarTarea(VistaAgregarTarea vistaAgregarTarea, ControladorCRUD controladorCRUD,ListaTareas listaTareas){
        super(vistaAgregarTarea, controladorCRUD, listaTareas);
    }

    @Override
    public void alAceptar(Tarea tarea) {
        tareas.agregar(tarea);
        JOptionPane.showMessageDialog(vistaFormularioTarea, "Tarea agregada");
        vistaFormularioTarea.setVisible(false);
    }

}
