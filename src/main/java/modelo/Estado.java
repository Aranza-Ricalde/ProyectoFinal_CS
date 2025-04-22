package modelo;

public enum Estado {
    PENDIENTE("Pendiente"), 
    EN_PROGRESO("En progreso"), 
    COMPLETADA("Completada");

    private final String valor; 

    private Estado(String valor){
        this.valor = valor; 
    }

    @Override
    public String toString(){ 
        return this.valor;
    }
} 
