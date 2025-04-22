package controlador;

import javax.swing.JOptionPane;

import modelo.ListaTareas;
import modelo.Tarea;
import vista.VistaModificarTarea;

public class ControladorModificarTarea extends ControladorFormularioTarea {
    public ControladorModificarTarea(VistaModificarTarea vistaFormularioTarea, ControladorCRUD controladorCRUD,ListaTareas listaTareas) {
        super(vistaFormularioTarea, controladorCRUD, listaTareas);
    }

    @Override
    public void alAceptar(Tarea tarea) throws Exception{
        int filaTabla = controladorCRUD.getFilaSeleccionada();
        tareas.modificar(filaTabla, tarea);
        JOptionPane.showMessageDialog(vistaFormularioTarea, "Tarea modificada");
        controladorCRUD.actualizarTabla();
        vistaFormularioTarea.setVisible(false);
    }


}
