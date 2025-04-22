package vista;

import java.util.Scanner;

// Esto es una interfaz para poder aceptar funciones
@FunctionalInterface
public interface ValidadorEntrada<T> {
    T leer(Scanner scanner) throws Exception;
}
