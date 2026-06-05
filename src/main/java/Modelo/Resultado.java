package Modelo;

public class Resultado {

    private final int numero;
    private final String tipoApuesta;
    private final int monto;
    private final boolean acierto;
    private final String color;

    public Resultado(int numero, String tipoApuesta, int monto, boolean acierto, String color) {
        this.numero = numero;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
        this.acierto = acierto;
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipoApuesta() {
        return tipoApuesta;
    }

    public int getMonto() {
        return monto;
    }

    public boolean isAcierto() {
        return acierto;
    }

    public String getColor() {
        return color;
    }
}
