package org.example;
import java.util.Random;
import java.util.Scanner;

public class lab01 {

    public static final int MAX_HISTORIAL = 100;

    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;

    public static Random rng = new Random();

    public static final int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18, 19,
            21, 23, 25, 27, 30, 32, 34, 36
    };

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);

        in.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n===== RULETA =====");
        System.out.println("1. Iniciar una ronda");
        System.out.println("2. Ver estadísticas");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static int leerOpcion(Scanner in) {
        while (!in.hasNextInt()) {
            System.out.print("Ingrese una opción válida (1, 2 o 3): ");
            in.next();
        }
        return in.nextInt();
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                iniciarRonda(in);
                break;
            case 2:
                mostrarEstadisticas();
                break;
            case 3:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    public static void iniciarRonda(Scanner in) {
        if (historialSize >= MAX_HISTORIAL) {
            System.out.println("Historial lleno.");
            return;
        }

        char tipo = leerTipoApuesta(in);
        int monto = leerMonto(in);
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);

        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }

    public static char leerTipoApuesta(Scanner in) {
        char tipo;

        do {
            System.out.print("Tipo (R/N/P/I): ");
            tipo = Character.toUpperCase(in.next().charAt(0));
        } while (tipo != 'R' && tipo != 'N' && tipo != 'P' && tipo != 'I');

        return tipo;
    }

    public static int leerMonto(Scanner in) {
        int monto;

        do {
            System.out.print("Ingrese el monto: ");
            while (!in.hasNextInt()) {
                System.out.print("Monto inválido. Ingrese un número entero: ");
                in.next();
            }
            monto = in.nextInt();

            if (monto <= 0) {
                System.out.println("El monto debe ser mayor que 0.");
            }
        } while (monto <= 0);

        return monto;
    }

    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) {
            return false;
        }

        switch (tipo) {
            case 'R':
                return esRojo(numero);
            case 'N':
                return !esRojo(numero);
            case 'P':
                return numero % 2 == 0;
            case 'I':
                return numero % 2 != 0;
            default:
                return false;
        }
    }

    public static boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) {
                return true;
            }
        }
        return false;
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        historialNumeros[historialSize] = numero;
        historialApuestas[historialSize] = apuesta;
        historialAciertos[historialSize] = acierto;
        historialSize++;
    }

    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        System.out.println("\nNúmero: " + numero);
        System.out.println("Apuesta: " + tipo);
        System.out.println("Monto: $" + monto);

        if (acierto) {
            System.out.println("Ganaste");
        } else {
            System.out.println("Perdiste");
        }
    }

    public static void mostrarEstadisticas() {
        if (historialSize == 0) {
            System.out.println("No hay datos.");
            return;
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

        System.out.println("\nRondas: " + historialSize);
        System.out.println("Total apostado: $" + totalApostado);
        System.out.println("Aciertos: " + totalAciertos);
        System.out.println("% acierto: " + porcentaje + "%");
        System.out.println("Ganancia neta: $" + gananciaNeta);
    }
}