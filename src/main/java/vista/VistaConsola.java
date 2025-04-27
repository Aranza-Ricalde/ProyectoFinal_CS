package vista;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import modelo.Tarea;

public class VistaConsola {
    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu() {
        System.out.println("\n=== MENÚ ===");
        System.out.println("1. Listar tareas");
        System.out.println("2. Agregar tareas");
        System.out.println("3. Eliminar tarea");
        System.out.println("4. Modificar tarea");
        System.out.println("5. Ordenar por fecha de vencimiento");
        System.out.println("6. Ordenar por prioridad");
        System.out.println("0. Salir");
    }

    
    public static void imprimirTablaTareas(List<Tarea> tareas){
         Iterator<Tarea> tareasIterator = tareas.iterator();

        // Encabezado
        System.err.println("=============================================================================================================");
        System.out.printf("| %-8s | %-32s | %-20s | %-10s | %-9s | %-11s |\n", 
                "ID",
                "Título", 
                "Descripción",
                "Fecha",
                "Prioridad",
                "Estado"
        ); 
        System.err.println("=============================================================================================================");
        //Tabla
        while (tareasIterator.hasNext()) {
            Tarea actualTarea = tareasIterator.next(); 
            System.out.printf("| %-8s | %-32s | %-20s | %-10s | %-9s | %-11s |\n", 
                actualTarea.id.toString().substring(0, 8),
                actualTarea.getTitulo().substring(0,Math.min(actualTarea.getTitulo().length(), 32)), 
                actualTarea.descripcion.substring(0, Math.min(actualTarea.descripcion.length(), 19)),
                actualTarea.getFechaVencimiento(),
                actualTarea.prioridad,
                actualTarea.estado
            );
        }
        System.err.println("=============================================================================================================");
    }

    public static void imprimirTareasTitulos(List<Tarea> tareas){ 
        Iterator<Tarea> tareasIterator = tareas.iterator();
        System.err.println("===============================================");
        System.out.printf("| %-8s | %-32s |\n", 
                "Index",
                        "Título");
        System.err.println("===============================================");
        for(int i = 0; i < tareas.size(); i++){ 
            Tarea actualTarea = tareasIterator.next();
            System.out.printf("| %-8s | %-32s |\n", 
                i,
                actualTarea.getTitulo()); 

        }
        System.err.println("===============================================");
    }

    //Estea es la base, para no estar repitiendo
    public static <T> T leerValor(String mensaje, String mensajeError, ValidadorEntrada<T> validador) {
        while (true) {
            System.out.print(mensaje);
            try {
                return validador.leer(scanner);
            } catch (Exception e) {
                System.out.println(mensajeError);
                scanner.nextLine(); //Limpiar buffer si hay error
            }
        }
    }

    public static String leerCadena(String mensaje){ 
        return leerValor(mensaje, "Cadena invalida", validador -> { 
            String cadena = validador.nextLine().trim();
            if (cadena.isBlank()) {throw new Exception();}
            return cadena;
        } 
        );
    }

    public static int leerEntero(String mensaje){ 
        return leerValor(mensaje, "Numero invalido", validador -> { 
            if(!validador.hasNextInt()){throw new Exception();}
            int entero = validador.nextInt();
            validador.nextLine(); //Limpiar buffer por si acaso
            return entero;
        } 
        );
    }

    public static LocalDate leerFecha(String mensaje){
        return leerValor(mensaje, "Fecha invalida, (formato: AAAA-MM-DD)", validador -> { 
            LocalDate fecha = LocalDate.parse(validador.nextLine());   
            return fecha;
        } 
        );

    }

    public static <E extends Enum<E>> E leerEnum(String mensaje, Class<E> enumTipo) {

        return leerValor(mensaje, "Valor inválido. Intenta de nuevo.", validador -> {
            String entrada = validador.nextLine().trim().toUpperCase();
            return Enum.valueOf(enumTipo, entrada);
        });
    }


}