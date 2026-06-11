package Modelo;

import Modelo.Persistencia.IRepositorioResultados;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {

    private final IRepositorioResultados repositorioResultados;

    public Estadisticas(IRepositorioResultados repositorioResultados) {
        if (repositorioResultados == null) {
            throw new IllegalArgumentException("Repositorio requerido");
        }
        this.repositorioResultados = repositorioResultados;
    }

    private List<Resultado> obtenerHistorial() {
        return repositorioResultados.obtenerTodos();
    }

    public int calcularTotalJugadas() {
        return obtenerHistorial().size();
    }

    public int calcularVictorias() {
        int victorias = 0;

        for (Resultado resultado : obtenerHistorial()) {
            if (resultado.isAcierto()) {
                victorias++;
            }
        }

        return victorias;
    }

    public double calcularPorcentajeVictorias() {
        List<Resultado> historial = obtenerHistorial();

        if (historial.isEmpty()) {
            return 0;
        }

        return (calcularVictorias() * 100.0) / historial.size();
    }

    public int calcularRachaMaxima() {
        int rachaActual = 0;
        int rachaMaxima = 0;

        for (Resultado resultado : obtenerHistorial()) {
            if (resultado.isAcierto()) {
                rachaActual++;
                rachaMaxima = Math.max(rachaMaxima, rachaActual);
            } else {
                rachaActual = 0;
            }
        }

        return rachaMaxima;
    }

    public String calcularTipoMasJugado() {
        List<Resultado> historial = obtenerHistorial();

        if (historial.isEmpty()) {
            return null;
        }

        Map<String, Integer> contador = new HashMap<>();

        for (Resultado resultado : historial) {
            String tipo = resultado.getTipoApuesta();

            if (tipo == null || tipo.isBlank()) {
                continue;
            }

            contador.put(tipo, contador.getOrDefault(tipo, 0) + 1);
        }

        String tipoMasJugado = null;
        int mayorCantidad = 0;

        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > mayorCantidad) {
                mayorCantidad = entry.getValue();
                tipoMasJugado = entry.getKey();
            }
        }

        return tipoMasJugado;
    }
}
