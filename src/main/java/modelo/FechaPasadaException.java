package modelo;

import java.time.LocalDate;

public class FechaPasadaException extends Exception{
    public FechaPasadaException(){ 
        super("La fecha es anterior al dia de hoy (" + LocalDate.now() + ")"); 
    }
}
