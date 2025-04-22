package modelo;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;


//Esta clase funciona para que el json pueda parsear el LocalData
public class LocalDataAdaptador extends TypeAdapter<LocalDate> {

    @Override
    //Escibir el LocalDate al json
    public void write(JsonWriter out, LocalDate fecha) throws IOException {
        if (fecha == null) {
            out.nullValue();
        } else {
            out.value(fecha.toString());
        }
    }

    //Leer el LocalDate del json
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        String fecha = in.nextString();
        return LocalDate.parse(fecha);
    }
}
