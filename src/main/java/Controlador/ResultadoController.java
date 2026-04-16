package Controlador;

import Modelo.Resultado;

public class ResultadoController {

    public String construirMensaje(Resultado resultado) {
        String estado = resultado.isAcierto() ? "GANASTE" : "PERDISTE";

        return "Número " + resultado.getNumero()
                + " | Apuesta=" + resultado.getTipoApuesta()
                + " | Monto=$" + resultado.getMonto()
                + " | " + estado;
    }
}