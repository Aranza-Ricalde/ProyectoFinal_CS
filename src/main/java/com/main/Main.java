package com.main;

import controlador.ControladorAgregarTarea;
import controlador.ControladorBuscar;
import controlador.ControladorCRUD;
import controlador.ControladorFiltrar;
import controlador.ControladorModificarTarea;
import modelo.ListaTareas;
import modelo.PersistenciaTareas;
import vista.VistaAgregarTarea;
import vista.VistaBuscar;
import vista.VistaCRUD;
import vista.VistaFiltrar;
import vista.VistaModificarTarea;


public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        ListaTareas tareas = PersistenciaTareas.cargarListaTareas();


        VistaCRUD vistaCRUD = new VistaCRUD(); 
        VistaAgregarTarea vistaAgregarTarea = new VistaAgregarTarea(vistaCRUD);
        VistaModificarTarea vistaModificarTarea = new VistaModificarTarea(vistaCRUD); 
        VistaBuscar vistaBuscar = new VistaBuscar();
        VistaFiltrar vistaFiltrar = new VistaFiltrar();

        ControladorCRUD controladorCRUD = new ControladorCRUD(vistaCRUD, vistaAgregarTarea, vistaModificarTarea, vistaBuscar, vistaFiltrar, tareas);
        ControladorAgregarTarea controladorAgregarTarea = new ControladorAgregarTarea(vistaAgregarTarea, controladorCRUD, tareas);
        ControladorModificarTarea controladorModificarTarea = new ControladorModificarTarea(vistaModificarTarea, controladorCRUD, tareas);
        ControladorBuscar controladorBuscar = new ControladorBuscar(vistaBuscar, tareas);
        ControladorFiltrar controladorFiltrar = new ControladorFiltrar(vistaFiltrar, tareas);

        vistaCRUD.setVisible(true);
    }
}