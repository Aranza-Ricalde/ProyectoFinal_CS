package controlador;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Tarea;

public class Utils {

     public static void actualizarTabla(DefaultTableModel modelo, ArrayList<Tarea> tareas){
        modelo.setRowCount(0);
        for (Tarea tarea : tareas) {
            modelo.addRow(new Object[]{
                tarea.id,
                tarea.getTitulo(),
                tarea.descripcion,
                tarea.getFechaVencimiento(),
                tarea.prioridad.toString(),
                tarea.estado.toString()
            });
        }
    }

}
