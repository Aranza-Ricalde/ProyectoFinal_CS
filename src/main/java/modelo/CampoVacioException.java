package modelo;

public class CampoVacioException extends Exception{
    public CampoVacioException(String campo){ 
        super("El " + campo + " está vacio");
    }
}
