package Modelo.Persistencia;

import Modelo.Resultado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HistorialArchivo {

    private static final Path RUTA = Path.of("historial_ruleta.csv");

    public void guardar(List<Resultado> historial) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA.toFile()))) {
            writer.write("numero,color,tipoApuesta,monto,acierto");
            writer.newLine();

            for (Resultado resultado : historial) {
                writer.write(resultado.getNumero() + ","
                        + resultado.getColor() + ","
                        + resultado.getTipoApuesta() + ","
                        + resultado.getMonto() + ","
                        + resultado.isAcierto());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo guardar el historial: " + e.getMessage());
        }
    }

    public String leerComoTexto() {
        try {
            if (!Files.exists(RUTA)) {
                return "No existe archivo de historial todavía.";
            }
            return Files.readString(RUTA);
        } catch (IOException e) {
            return "No se pudo leer el historial: " + e.getMessage();
        }
    }
}
