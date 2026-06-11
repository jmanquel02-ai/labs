import Modelo.Estadisticas;
import Modelo.Resultado;
import Modelo.Persistencia.IRepositorioResultados;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstadisticasTest {

    @Test
    void estadisticasCalculanRachaPorcentajeYTipoMasJugado() {
        RepositorioFake repositorio = new RepositorioFake();

        repositorio.guardar(new Resultado(2, "Par", 100, true, "NEGRO"));
        repositorio.guardar(new Resultado(4, "Par", 100, true, "NEGRO"));
        repositorio.guardar(new Resultado(5, "Rojo", 100, false, "ROJO"));
        repositorio.guardar(new Resultado(8, "Par", 100, true, "NEGRO"));
        repositorio.guardar(new Resultado(10, "Negro", 100, true, "NEGRO"));
        repositorio.guardar(new Resultado(11, null, 100, false, "NEGRO"));

        Estadisticas estadisticas = new Estadisticas(repositorio);

        assertEquals(6, estadisticas.calcularTotalJugadas());
        assertEquals(4, estadisticas.calcularVictorias());
        assertEquals(66.666, estadisticas.calcularPorcentajeVictorias(), 0.01);
        assertEquals(2, estadisticas.calcularRachaMaxima());
        assertEquals("Par", estadisticas.calcularTipoMasJugado());
    }

    static class RepositorioFake implements IRepositorioResultados {

        private final List<Resultado> resultados = new ArrayList<>();

        @Override
        public void guardar(Resultado resultado) {
            resultados.add(resultado);
        }

        @Override
        public List<Resultado> obtenerTodos() {
            return resultados;
        }

        @Override
        public void limpiar() {
            resultados.clear();
        }
    }
}
