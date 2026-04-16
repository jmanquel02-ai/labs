package Modelo;

public enum TipoApuesta {
    ROJO("Rojo"),
    NEGRO("Negro"),
    PAR("Par"),
    IMPAR("Impar");

    private final String nombre;

    TipoApuesta(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}