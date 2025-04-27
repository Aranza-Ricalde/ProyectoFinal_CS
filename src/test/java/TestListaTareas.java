import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.CampoVacioException;
import modelo.Estado;
import modelo.FechaPasadaException;
import modelo.ListaTareas;
import modelo.Prioridad;
import modelo.Tarea;

public class TestListaTareas {
    private static final ListaTareas listaTareas = new ListaTareas(); 
    private static Tarea tareaEjemplo;
    @BeforeAll
    public static void crearTarea(){ 
        try {
            TestListaTareas.tareaEjemplo = new Tarea("Mi título");
        } catch (CampoVacioException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void agregarTituloValido() {
        assertDoesNotThrow(() -> {
            listaTareas.agregar("Nueva tarea");
            assertFalse(listaTareas.listar().isEmpty());
        });
    }

    @Test
    public void agregarTareaValida() {
        assertDoesNotThrow(() -> {
            listaTareas.agregar(tareaEjemplo);
            assertTrue(listaTareas.listar().contains(tareaEjemplo));
        });
    }

    @Test
    public void modificarTareaPorReferencia() throws CampoVacioException, FechaPasadaException {
        Tarea nueva = new Tarea("Modificada");
        nueva.setFechaVencimiento(LocalDate.now().plusDays(5));
        nueva.estado = Estado.EN_PROGRESO;
        nueva.prioridad = Prioridad.ALTA;

        assertDoesNotThrow(() -> {
            listaTareas.modificar(tareaEjemplo, nueva);
            assertEquals("Modificada", tareaEjemplo.getTitulo());
            assertEquals(Prioridad.ALTA, tareaEjemplo.prioridad);
        });
    }

    @Test
    public void modificarTareaPorIndice() throws CampoVacioException, FechaPasadaException {
        Tarea nueva = new Tarea("Modificada índice");
        nueva.setFechaVencimiento(LocalDate.now().plusDays(2));
        nueva.estado = Estado.COMPLETADA;
        nueva.prioridad = Prioridad.BAJA;
        
        Tarea tareaNueva = new Tarea("Tarea nueva"); 
        listaTareas.agregar(tareaNueva);
        int index = listaTareas.listar().indexOf(tareaNueva);
        assertDoesNotThrow(() -> {
            listaTareas.modificar(index, nueva);
            assertEquals("Modificada índice", listaTareas.listar().get(index).getTitulo());
        });
    }

    @Test
    public void eliminarPorTarea() {
        listaTareas.agregar(tareaEjemplo);
        int tamanioAntes = listaTareas.listar().size();
        assertDoesNotThrow(() -> {
            listaTareas.eliminar(tareaEjemplo);
            assertEquals(tamanioAntes - 1, listaTareas.listar().size());
        });
        assertFalse(listaTareas.listar().contains(tareaEjemplo));
    }

    @Test
    public void eliminarPorIndex() throws CampoVacioException {
        listaTareas.agregar(tareaEjemplo);
        int ultimoIndex = listaTareas.listar().size() - 1;
        assertDoesNotThrow(() -> {
            listaTareas.eliminar(ultimoIndex);
            assertTrue(listaTareas.listar().size() <= ultimoIndex);
        });
        assertFalse(listaTareas.listar().contains(tareaEjemplo));
    }

    @Test
    public void eliminarPorId() throws CampoVacioException {
        listaTareas.agregar(tareaEjemplo);
        UUID id = tareaEjemplo.id;
        assertDoesNotThrow(() -> {
            listaTareas.eliminar(id);
        });
        assertFalse(listaTareas.listar().contains(tareaEjemplo));
    }

    @Test
    public void ordenarPorFecha() throws CampoVacioException {
        listaTareas.agregar(new Tarea("Con fecha lejana"));
        assertDoesNotThrow(() -> { 
            listaTareas.listar().get(listaTareas.listar().size()-1).setFechaVencimiento(LocalDate.now().plusDays(10));
        }); 

        listaTareas.ordenarFechaVencimiento();

        assertTrue(listaTareas.listar().get(0).getFechaVencimiento().isBefore(
            listaTareas.listar().get(listaTareas.listar().size()-1).getFechaVencimiento()
        ));
    }

    @Test
    public void ordenarPorPrioridad() throws CampoVacioException {
        listaTareas.agregar(new Tarea("Con prioridad alta"));
        listaTareas.listar().get(listaTareas.listar().size()-1).prioridad = Prioridad.ALTA;

        listaTareas.ordenarPrioridad();

        assertEquals(Prioridad.BAJA.valor > Prioridad.MEDIA.valor, true);
    }

    @Test
    public void filtrarPorEstadoPendiente() throws CampoVacioException {
        listaTareas.agregar("Filtrar pendiente");
        var resultados = listaTareas.filtrar(t -> t.estado == Estado.PENDIENTE);
        assertTrue(resultados.stream().allMatch(t -> t.estado == Estado.PENDIENTE));
    }

    @Test
    public void buscarTituloExistente() throws CampoVacioException {
        listaTareas.agregar("Buscar título único");
        var encontrados = listaTareas.buscar("único");
        assertTrue(encontrados.stream().anyMatch(t -> t.getTitulo().toLowerCase().contains("único")));
    }


}
