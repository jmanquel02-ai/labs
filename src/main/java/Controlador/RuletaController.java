package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

public class RuletaController {

    private final Ruleta ruleta;

    public RuletaController(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public Resultado jugar(TipoApuesta tipoApuesta, int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        }

        int numero = ruleta.generarNumero();
        boolean acierto = ruleta.evaluarResultado(numero, tipoApuesta);

        ruleta.registrarResultado(numero, monto, acierto);

        return new Resultado(numero, tipoApuesta, monto, acierto);
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public void depositar(int monto) {
        ruleta.depositar(monto);
    }

    public String obtenerEstadisticas() {
        return ruleta.obtenerEstadisticas();
    }

    public Ruleta getRuleta() {
        return ruleta;
    }
}