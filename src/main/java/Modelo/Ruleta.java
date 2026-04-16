package Modelo;

import java.util.Random;

public class Ruleta {

    public static final int MAX_HISTORIAL = 100;
    public static final int MAX_NUMERO = 36;

    private final int[] historialNumeros = new int[MAX_HISTORIAL];
    private final int[] historialApuestas = new int[MAX_HISTORIAL];
    private final boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private int historialSize = 0;

    private final Random rng = new Random();
    private final int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    private int saldo;

    public Ruleta() {
        this.saldo = 0;
    }

    public Ruleta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public int generarNumero() {
        return rng.nextInt(MAX_NUMERO + 1);
    }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (numero == 0) {
            return false;
        }

        return switch (tipo) {
            case ROJO -> esRojo(numero);
            case NEGRO -> !esRojo(numero);
            case PAR -> numero % 2 == 0;
            case IMPAR -> numero % 2 != 0;
        };
    }

    public boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) {
                return true;
            }
        }
        return false;
    }

    public void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize >= MAX_HISTORIAL) {
            return;
        }

        historialNumeros[historialSize] = numero;
        historialApuestas[historialSize] = apuesta;
        historialAciertos[historialSize] = acierto;
        historialSize++;

        if (acierto) {
            saldo += apuesta;
        } else {
            saldo -= apuesta;
        }
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
        if (historialSize == 0) {
            return "No hay rondas jugadas.";
        }

        int totalApostado = 0;
        int totalAciertos = 0;
        int gananciaNeta = 0;

        for (int i = 0; i < historialSize; i++) {
            totalApostado += historialApuestas[i];
            if (historialAciertos[i]) {
                totalAciertos++;
                gananciaNeta += historialApuestas[i];
            } else {
                gananciaNeta -= historialApuestas[i];
            }
        }

        double porcentaje = (double) totalAciertos * 100 / historialSize;

        return "Rondas: " + historialSize
                + "\nTotal apostado: $" + totalApostado
                + "\nAciertos: " + totalAciertos
                + "\n% acierto: " + String.format("%.1f", porcentaje) + "%"
                + "\nGanancia neta: $" + gananciaNeta
                + "\nSaldo actual: $" + saldo;
    }
}