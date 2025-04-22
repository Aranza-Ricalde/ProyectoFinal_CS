package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PersistenciaTareas {

    private static final String ARCHIVO_TAREAS = "tareas.json";
    private static final Gson GSON = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDataAdaptador())
                        .setPrettyPrinting()
                        .create();


    public static ListaTareas cargarListaTareas(){
        File archivo = new File(ARCHIVO_TAREAS);

        if(!Files.exists(Path.of(ARCHIVO_TAREAS))){ 
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                //Nada porque checamos antes que de verdad un archivo no existe
            }
            return new ListaTareas(); 
        }
        
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get(ARCHIVO_TAREAS)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            json = "{\"tareas\": []}";
        }
        return GSON.fromJson(json, ListaTareas.class);
    }
    public static void guardarListaTareas(ListaTareas tareas) throws IOException{
        File archivo = new File(ARCHIVO_TAREAS);

        if(!Files.exists(Path.of(ARCHIVO_TAREAS))){ 
            archivo.createNewFile(); 
        }
        

        FileWriter escritor = new FileWriter(archivo);
        String json = GSON.toJson(tareas); 
        escritor.append(json);
        escritor.close();
    }

    

}
