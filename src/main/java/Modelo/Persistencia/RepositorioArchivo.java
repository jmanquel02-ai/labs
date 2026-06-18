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
        if (nombreArchivo == null || nombreArchivo.isBlank()) {
            throw new IllegalArgumentException("Ruta de historial requerida");
        }

        this.rutaArchivo = Path.of(nombreArchivo);
        validarRutaInicial();
    }

    private void validarRutaInicial() {
        Path rutaPadre = rutaArchivo.toAbsolutePath().getParent();

        if (rutaPadre != null && Files.exists(rutaPadre) && !Files.isWritable(rutaPadre)) {
            throw new IllegalStateException("No hay permisos de escritura en la carpeta del historial");
        }

        if (Files.exists(rutaArchivo)) {
            if (!Files.isRegularFile(rutaArchivo)) {
                throw new IllegalStateException("La ruta del historial no corresponde a un archivo");
            }

            if (!Files.isReadable(rutaArchivo)) {
                throw new IllegalStateException("No hay permisos de lectura sobre el historial");
            }

            if (!Files.isWritable(rutaArchivo)) {
                throw new IllegalStateException("No hay permisos de escritura sobre el historial");
            }
        }
    }

    @Override
    public void guardar(Resultado resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("Resultado requerido");
        }

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
            throw new IllegalStateException("No se pudo guardar el resultado en archivo", e);
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

                String textoNumero = partes[0].trim();
                String color = partes[1].trim();
                String tipoApuesta = partes[2].trim();
                String textoMonto = partes[3].trim();
                String textoAcierto = partes[4].trim();

                if (textoNumero.isBlank() || color.isBlank() || tipoApuesta.isBlank()
                        || textoMonto.isBlank() || textoAcierto.isBlank()) {
                    continue;
                }

                try {
                    int numero = Integer.parseInt(textoNumero);
                    int monto = Integer.parseInt(textoMonto);
                    boolean acierto = Boolean.parseBoolean(textoAcierto);

                    if (numero < 0 || numero > 36 || monto <= 0) {
                        continue;
                    }

                    resultados.add(new Resultado(numero, tipoApuesta, monto, acierto, color));
                } catch (NumberFormatException e) {
                    // Línea corrupta: se descarta y el resto del historial se sigue leyendo.
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer el historial desde archivo", e);
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
            throw new IllegalStateException("No se pudo limpiar el historial", e);
        }
    }
}
