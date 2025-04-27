import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import modelo.ListaTareas;
import modelo.PersistenciaTareas;

public class TestPersistenciaTareas {


    @Test
    public void cargarListaTareas(){ 
        assertDoesNotThrow(() -> {
            ListaTareas listaTareas = PersistenciaTareas.cargarListaTareas(); 
            assertTrue(listaTareas != null);

            assertEquals(ArrayList.class, listaTareas.listar().getClass());
            assertTrue(listaTareas.listar().size() >= 0);
        });
    }

    @AfterAll
    public static void guargarListaTareas(){ 
        assertDoesNotThrow(() -> {
            ListaTareas listaTareas = PersistenciaTareas.cargarListaTareas(); 
            PersistenciaTareas.guardarListaTareas(listaTareas);
        });
    }

}
