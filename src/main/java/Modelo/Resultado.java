package Modelo;

public class Resultado {

    private int numero;
    private TipoApuesta tipoApuesta;
    private int monto;
    private boolean acierto;

    public Resultado(int numero, TipoApuesta tipoApuesta, int monto, boolean acierto) {
        this.numero = numero;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
        this.acierto = acierto;
    }

    public int getNumero() {
        return numero;
    }

    public TipoApuesta getTipoApuesta() {
        return tipoApuesta;
    }

    public int getMonto() {
        return monto;
    }

    public boolean isAcierto() {
        return acierto;
    }
}