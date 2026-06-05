package Modelo;

import Modelo.Apuestas.ApuestaBase;
import Modelo.Persistencia.IRepositorioResultados;
import Modelo.Persistencia.RepositorioEnMemoria;

import java.util.List;
import java.util.Random;

public class Ruleta {

    public static final int MAX_NUMERO = 36;

    private final Random rng = new Random();
    private final int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    private int saldo;
    private final IRepositorioResultados repositorioResultados;

    public Ruleta() {
        this(0, new RepositorioEnMemoria());
    }

    public Ruleta(int saldoInicial) {
        this(saldoInicial, new RepositorioEnMemoria());
    }

    public Ruleta(int saldoInicial, IRepositorioResultados repositorioResultados) {
        this.saldo = saldoInicial;
        this.repositorioResultados = repositorioResultados;
    }

    public Resultado jugar(ApuestaBase apuesta) {
        int numero = generarNumero();
        String color = colorDe(numero);
        boolean acierto = apuesta.acierta(numero, color);

        actualizarSaldo(apuesta.getMonto(), acierto);

        Resultado resultado = new Resultado(numero, apuesta.getEtiqueta(), apuesta.getMonto(), acierto, color);
        repositorioResultados.guardar(resultado);

        return resultado;
    }

    public int generarNumero() {
        return rng.nextInt(MAX_NUMERO + 1);
    }

    public String colorDe(int numero) {
        if (numero == 0) {
            return "VERDE";
        }
        return esRojo(numero) ? "ROJO" : "NEGRO";
    }

    public boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) {
                return true;
            }
        }
        return false;
    }

    private void actualizarSaldo(int monto, boolean acierto) {
        if (acierto) {
            saldo += monto;
        } else {
            saldo -= monto;
        }
    }

    public List<Resultado> obtenerHistorial() {
        return repositorioResultados.obtenerTodos();
    }

    public IRepositorioResultados getRepositorioResultados() {
        return repositorioResultados;
    }

    public int getSaldo() {
        return saldo;
    }

    public void depositar(int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        }
        saldo += monto;
    }

    public String obtenerEstadisticas() {
        List<Resultado> historial = repositorioResultados.obtenerTodos();

        if (historial.isEmpty()) {
            return "No hay rondas jugadas.";
        }

        int totalApostado = 0;
        int totalAciertos = 0;
        int gananciaNeta = 0;

        for (Resultado resultado : historial) {
            totalApostado += resultado.getMonto();

            if (resultado.isAcierto()) {
                totalAciertos++;
                gananciaNeta += resultado.getMonto();
            } else {
                gananciaNeta -= resultado.getMonto();
            }
        }

        double porcentaje = (double) totalAciertos * 100 / historial.size();

        return "Rondas: " + historial.size()
                + "\nTotal apostado: $" + totalApostado
                + "\nAciertos: " + totalAciertos
                + "\n% acierto: " + String.format("%.1f", porcentaje) + "%"
                + "\nGanancia neta: $" + gananciaNeta
                + "\nSaldo actual: $" + saldo;
    }
}
