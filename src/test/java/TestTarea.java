import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.CampoVacioException;
import modelo.FechaPasadaException;
import modelo.Tarea;

public class TestTarea {
    private static Tarea tarea; 

    @BeforeAll
    public static void crearTarea(){ 
        try {
            TestTarea.tarea = new Tarea("Mi título");
        } catch (CampoVacioException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tituloValido(){ 
        assertDoesNotThrow(()-> {
            TestTarea.tarea = new Tarea("Mi título"); 
            assertEquals("Mi título", tarea.getTitulo());
        }); 
    }

    @Test
    public void tareaActual(){ 
        assertNotNull(TestTarea.tarea);
    }

    @Test
    public void lanzaCampoVacioException(){ 
        Exception exception = assertThrows(CampoVacioException.class , ()-> {
                new Tarea(" ");
            }
        );
        assertEquals("El Título está vacio", exception.getMessage());  
    }

     @Test
    public void setTituloLanzaCampoVacioException() throws CampoVacioException {
        Exception exception = assertThrows(CampoVacioException.class, () -> {
            TestTarea.tarea.setTitulo(" ");
        });

        assertEquals("El Título está vacio", exception.getMessage());
    }

    @Test
    public void setFechaVencimientoLanzaFechaPasadaException() throws CampoVacioException {
        Exception exception = assertThrows(FechaPasadaException.class, () -> {
            TestTarea.tarea.setFechaVencimiento(LocalDate.now().minusDays(1));
        });

        assertEquals("La fecha es anterior al dia de hoy (" + LocalDate.now() + ")", exception.getMessage());
    }

    @Test
    public void setFechaVencimientoNoLanzaExcepcion() throws CampoVacioException {
        assertDoesNotThrow(() -> {
            TestTarea.tarea.setFechaVencimiento(LocalDate.now()); 
        });

        assertEquals(LocalDate.now(), tarea.getFechaVencimiento());
    }


}
