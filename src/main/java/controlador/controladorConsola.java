package controlador;

import java.time.LocalDate;

import modelo.CampoVacioException;
import modelo.Estado;
import modelo.FechaPasadaException;
import modelo.ListaTareas;
import modelo.PersistenciaTareas;
import modelo.Prioridad;
import modelo.Tarea;
import vista.VistaConsola;

public class controladorConsola {
    private ListaTareas listaTareas;

    public controladorConsola(){ 
        
        try {
            this.listaTareas = PersistenciaTareas.cargarListaTareas();
        } catch (Exception e) {
            this.listaTareas = new ListaTareas();
            System.out.println("Error al cargar las tareas");

        }
    }

    public void iniciar(){
        int opcion;
        do {
            VistaConsola.mostrarMenu();
            opcion = VistaConsola.leerEntero("Elige una opción > ");
            switch (opcion) {
                case 1 -> listarTareas();
                case 2 -> agregarTarea();
                case 3 -> eliminarTarea();
                case 4 -> modificarTarea();
                case 5 -> ordenarPorFecha();
                case 6 -> ordenarPorPrioridad();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }
    
    private void listarTareas(){ 
        VistaConsola.imprimirTablaTareas(listaTareas.listar());
    }

    private void agregarTarea(){ 
        Tarea tareaActual = capturarTarea(); 
        listaTareas.agregar(tareaActual);
        System.out.println("-Tarea agregada-");
    }

    private void eliminarTarea(){ 
        System.out.println("Ingrese un numero de tarea a eliminar 0 - " + (listaTareas.listar().size()-1));
        VistaConsola.imprimirTareasTitulos(listaTareas.listar());
        int index = VistaConsola.leerEntero("> ");
        String confirmacion = VistaConsola.leerCadena("Estas seguro? (S/N) > ");
        if(confirmacion.toUpperCase() == "S"){ 
            listaTareas.eliminar(index);
            System.out.println("-Tarea eliminada-");
        }else{ 
            System.out.println("-Tarea no eliminada-");
        }
    }

    private void modificarTarea(){
        System.out.println("Ingrese un numero de tarea a modificar 0 - " + (listaTareas.listar().size()-1));
        VistaConsola.imprimirTareasTitulos(listaTareas.listar());
        int index = VistaConsola.leerEntero("> ");

        Tarea tareaActual = capturarTarea(); 

        try {
            listaTareas.modificar(index, tareaActual);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("-Tarea modificada-");
    }

    private void ordenarPorFecha(){
        listaTareas.ordenarFechaVencimiento();
        VistaConsola.imprimirTablaTareas(listaTareas.listar());
    }

    private void ordenarPorPrioridad(){ 
        listaTareas.ordenarPrioridad();
        VistaConsola.imprimirTablaTareas(listaTareas.listar());
    }
    
    private Tarea capturarTarea(){ 
        String titulo = VistaConsola.leerCadena("Ingrese un título > ");
        String descripcion = VistaConsola.leerCadena("Ingrese una descripción > ");
        LocalDate fechaVencimiento = VistaConsola.leerFecha("Ingrese una fecha mayor a hoy (formato: AAAA-MM-DD) \n> "); 
        Prioridad prioridad = VistaConsola.leerEnum("Ingrese una prioridad (ALTA, MEDIA, BAJA)\n> ", Prioridad.class); 
        Estado estado = VistaConsola.leerEnum("Ingrese un estado (PENDIENTE, EN_PROGRESO, COMPLETADA)\n> ", Estado.class); 

        Tarea tareaActual; 
        while(true){ 
            try{ 
                tareaActual = new Tarea(titulo);
                break;
            }catch(CampoVacioException e){ 
                titulo = VistaConsola.leerCadena("Ingrese un título valido > ");
            }
        }
        while(true){ 
            try{ 
                tareaActual.setFechaVencimiento(fechaVencimiento);
                break;
            }catch(FechaPasadaException e){ 
                fechaVencimiento = VistaConsola.leerFecha("Ingrese una fecha mayor a hoy (formato: AAAA-MM-DD) \\n> ");
            }
        }
        tareaActual.descripcion = descripcion;
        tareaActual.prioridad = prioridad;
        tareaActual.estado = estado;
        return tareaActual;

    }



}
