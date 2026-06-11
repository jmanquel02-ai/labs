package Modelo.Persistencia;

import Modelo.Resultado;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {

    private final Path rutaArchivo;

    public RepositorioArchivo() {
        this("historial_ruleta.csv");
    }

    public RepositorioArchivo(String nombreArchivo) {
        this.rutaArchivo = Path.of(nombreArchivo);
    }

    @Override
    public void guardar(Resultado resultado) {
        try {
            boolean archivoNuevo = !Files.exists(rutaArchivo) || Files.size(rutaArchivo) == 0;

            try (BufferedWriter writer = Files.newBufferedWriter(
                    rutaArchivo,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            )) {
                if (archivoNuevo) {
                    writer.write("numero,color,tipoApuesta,monto,acierto");
                    writer.newLine();
                }

                writer.write(resultado.getNumero() + ","
                        + resultado.getColor() + ","
                        + resultado.getTipoApuesta() + ","
                        + resultado.getMonto() + ","
                        + resultado.isAcierto());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo guardar el resultado en archivo: " + e.getMessage());
        }
    }

    @Override
    public List<Resultado> obtenerTodos() {
        List<Resultado> resultados = new ArrayList<>();

        if (!Files.exists(rutaArchivo)) {
            return resultados;
        }

        try {
            List<String> lineas = Files.readAllLines(rutaArchivo);

            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i).trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(",");

                if (partes.length != 5) {
                    continue;
                }

                int numero = Integer.parseInt(partes[0]);
                String color = partes[1];
                String tipoApuesta = partes[2];
                int monto = Integer.parseInt(partes[3]);
                boolean acierto = Boolean.parseBoolean(partes[4]);

                resultados.add(new Resultado(numero, tipoApuesta, monto, acierto, color));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("No se pudo leer el historial desde archivo: " + e.getMessage());
        }

        return resultados;
    }

    @Override
    public void limpiar() {
        try (BufferedWriter writer = Files.newBufferedWriter(
                rutaArchivo,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        )) {
            writer.write("numero,color,tipoApuesta,monto,acierto");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo limpiar el historial: " + e.getMessage());
        }
    }
}
