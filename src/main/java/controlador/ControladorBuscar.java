package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import modelo.ListaTareas;
import modelo.Tarea;
import vista.VistaBuscar;

public class ControladorBuscar implements ActionListener{

    private VistaBuscar vistaBuscar;
    private ListaTareas tareas; 

    public ControladorBuscar(VistaBuscar vistaBuscar, ListaTareas listaTareas){ 
        this.vistaBuscar = vistaBuscar;
        this.tareas = listaTareas;

        vistaBuscar.getBtnBuscar().addActionListener(this);
        vistaBuscar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Utils.actualizarTabla((DefaultTableModel) vistaBuscar.getTablaTareas().getModel(), (ArrayList<Tarea>)listaTareas.listar());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(vistaBuscar.getBtnBuscar() == e.getSource()){ 
            buscar();
        }
    }

    private void buscar(){
        String busqueda = vistaBuscar.getCampoBusqueda();
        
        ArrayList<Tarea> resultado = (ArrayList<Tarea>) tareas.buscar(busqueda);
        Utils.actualizarTabla((DefaultTableModel) vistaBuscar.getTablaTareas().getModel(), resultado);
    }

}
