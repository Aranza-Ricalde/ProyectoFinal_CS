package modelo;

public enum Prioridad {
    ALTA("Alta", 0),
    MEDIA("Media",1), 
    BAJA("Baja",2);
    
    private final String cadena;
    public  final int    valor;  

    private Prioridad(String cadena, int valor){
        this.cadena = cadena;
        this.valor = valor; 
    }

    @Override
    public String toString(){ 
        return this.cadena;
    }
}
