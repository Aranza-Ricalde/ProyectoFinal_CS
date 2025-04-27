package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListaTareas {  
 
    private ArrayList<Tarea> tareas;
    
    public ListaTareas(){
        this.tareas = new ArrayList<Tarea>();
    }

    public void agregar(String titulo) throws CampoVacioException{ 
        this.tareas.add(new Tarea(titulo));
    }

    public void agregar(Tarea tarea){
        tareas.add(tarea);
    }

    public void modificar(Tarea tarea, Tarea tareaModificada) throws CampoVacioException, FechaPasadaException{ 
        Tarea referencia = tareas.get(tareas.indexOf(tarea));
        referencia.setTitulo(tareaModificada.getTitulo());
        referencia.descripcion = tareaModificada.descripcion; 
        referencia.setFechaVencimiento(tareaModificada.getFechaVencimiento());
        referencia.estado = tareaModificada.estado; 
        referencia.prioridad = tareaModificada.prioridad;
    }

    public void modificar(int index, Tarea tarea) throws CampoVacioException, FechaPasadaException{ 
        Tarea referencia = tareas.get(index);
        referencia.setTitulo(tarea.getTitulo());
        referencia.descripcion = tarea.descripcion; 
        referencia.setFechaVencimiento(tarea.getFechaVencimiento());
        referencia.estado = tarea.estado; 
        referencia.prioridad = tarea.prioridad;
    }

    public void eliminar(Tarea tarea){
        tareas.remove(tarea); 
    }

    public void eliminar(UUID id){ 
        tareas.removeIf(t -> t.id == id);
    }

    public void eliminar(int index){ 
        tareas.remove(index);
    }
 
    public void ordenarFechaVencimiento(){
        this.tareas.sort(Comparator.comparing(t-> t.getFechaVencimiento()));
    }

    public void ordenarPrioridad(){
        this.tareas.sort(Comparator.comparing(t-> t.prioridad.valor));
    }

    public List<Tarea> listar(){ 
        return tareas;
    }

    public List<Tarea> filtrar(Predicate<Tarea> filtro){ 
        List<Tarea> seleccion = this.tareas.stream().filter(filtro).collect(Collectors.toList());
        return seleccion;
    }

    public List<Tarea> buscar(String consulta){ 
        List<Tarea> busqueda = this.tareas.stream().filter(t -> 
                t.getTitulo().toLowerCase().contains(consulta.toLowerCase()) || 
                t.descripcion.toLowerCase().contains(consulta.toLowerCase()))
        .collect(Collectors.toList());
        return busqueda;
    }



}
