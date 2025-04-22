package modelo;

public enum Prioridad {
    ALTA("Alta", 1),
    MEDIA("Media",2), 
    BAJA("Baja",3);
    
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
