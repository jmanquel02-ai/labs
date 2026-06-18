import Modelo.Persistencia.RepositorioArchivo;
import Modelo.Resultado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioArchivoTest {

    @TempDir
    Path carpetaTemporal;

    @Test
    void lecturaIgnoraLineasCorruptasYContinuaConElResto() throws IOException {
        Path archivo = carpetaTemporal.resolve("historial.csv");

        Files.writeString(archivo,
                "numero,color,tipoApuesta,monto,acierto\n" +
                        "2,NEGRO,PAR,100,true\n" +
                        "linea-corrupta\n" +
                        "abc,ROJO,ROJO,100,false\n" +
                        "37,ROJO,ROJO,100,false\n" +
                        "4,NEGRO,PAR,200,true\n");

        RepositorioArchivo repositorio = new RepositorioArchivo(archivo.toString());

        List<Resultado> resultados = repositorio.obtenerTodos();

        assertEquals(2, resultados.size());
        assertEquals(2, resultados.get(0).getNumero());
        assertEquals(4, resultados.get(1).getNumero());
    }
}
