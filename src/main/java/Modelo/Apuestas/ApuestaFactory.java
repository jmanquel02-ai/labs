package Modelo.Apuestas;

public class ApuestaFactory {

    public static ApuestaBase crear(String etiqueta, int monto) {
        return switch (etiqueta) {
            case "ROJO" -> new ApuestaRojo(monto);
            case "NEGRO" -> new ApuestaNegro(monto);
            case "PAR" -> new ApuestaPar(monto);
            case "IMPAR" -> new ApuestaImpar(monto);
            default -> throw new IllegalArgumentException("Tipo de apuesta no válido");
        };
    }
}
