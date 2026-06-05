package Controlador;

import Modelo.Estadisticas;

public class EstadisticasController {

    private final Estadisticas estadisticas;

    public EstadisticasController(RuletaController ruletaController) {
        this.estadisticas = new Estadisticas(ruletaController.getRepositorioResultados());
    }

    public int obtenerTotalJugadas() {
        return estadisticas.calcularTotalJugadas();
    }

    public int obtenerVictorias() {
        return estadisticas.calcularVictorias();
    }

    public double obtenerPorcentajeVictorias() {
        return estadisticas.calcularPorcentajeVictorias();
    }

    public int obtenerRachaMaxima() {
        return estadisticas.calcularRachaMaxima();
    }

    public String obtenerTipoMasJugado() {
        return estadisticas.calcularTipoMasJugado();
    }
}
