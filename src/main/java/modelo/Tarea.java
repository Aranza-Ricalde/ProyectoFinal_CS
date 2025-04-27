package modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Tarea {
    public  final UUID id;
    private       String titulo;

    public String descripcion;
    private LocalDate fechaVencimiento;
    public Prioridad prioridad;
    public Estado estado; 

    public Tarea(String titulo) throws CampoVacioException{
        checarTitulo(titulo);
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descripcion = "";
        this.fechaVencimiento = LocalDate.now().plusDays(1);
        this.prioridad = Prioridad.MEDIA; 
        this.estado = Estado.PENDIENTE; 
    }

    public void setTitulo(String titulo) throws CampoVacioException{
        checarTitulo(titulo);
        this.titulo = titulo;
    }

    public void setFechaVencimiento(LocalDate fecha) throws FechaPasadaException{ 
        if(fecha.isBefore(LocalDate.now())){ 
            throw new FechaPasadaException(); 
        }
        this.fechaVencimiento = fecha;
    }

    public LocalDate getFechaVencimiento(){ 
        return this.fechaVencimiento;
    }

    public String getTitulo(){ 
        return this.titulo; 
    }

    private void checarTitulo(String titulo) throws CampoVacioException{ 
        if(titulo.isBlank() || titulo == null){ 
            throw new CampoVacioException("Título");
        } 
    }
}
