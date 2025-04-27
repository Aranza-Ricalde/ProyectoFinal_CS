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
    private static Tarea tareaEjemplo; 

    @BeforeAll
    public static void crearTarea(){ 
        try {
            tareaEjemplo = new Tarea("Mi título");
        } catch (CampoVacioException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tituloValido(){ 
        assertDoesNotThrow(()-> {
            tareaEjemplo = new Tarea("Mi título"); 
            assertEquals("Mi título", tareaEjemplo.getTitulo());
        }); 
    }

    @Test
    public void tareaActual(){ 
        assertNotNull(tareaEjemplo);
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
            tareaEjemplo.setTitulo(" ");
        });

        assertEquals("El Título está vacio", exception.getMessage());
    }

    @Test
    public void setFechaVencimientoLanzaFechaPasadaException() throws CampoVacioException {
        Exception exception = assertThrows(FechaPasadaException.class, () -> {
            tareaEjemplo.setFechaVencimiento(LocalDate.now().minusDays(1));
        });

        assertEquals("La fecha es anterior al dia de hoy (" + LocalDate.now() + ")", exception.getMessage());
    }

    @Test
    public void setFechaVencimientoNoLanzaExcepcion() throws CampoVacioException {
        assertDoesNotThrow(() -> {
            tareaEjemplo.setFechaVencimiento(LocalDate.now()); 
        });

        assertEquals(LocalDate.now(), tareaEjemplo.getFechaVencimiento());
    }


}
