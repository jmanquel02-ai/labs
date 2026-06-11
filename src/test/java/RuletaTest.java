import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.Apuestas.ApuestaBase;
import Modelo.Persistencia.IRepositorioResultados;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RuletaTest {

    @Test
    void constructorRechazaSaldoInicialNegativo() {
        IRepositorioResultados repositorio = new RepositorioFake();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Ruleta(-100, repositorio)
        );

        assertEquals("Saldo inicial inválido", exception.getMessage());
    }

    @Test
    void depositoValidoIncrementaSaldo() {
        Ruleta ruleta = new Ruleta(100, new RepositorioFake());

        ruleta.depositar(50);

        assertEquals(150, ruleta.getSaldo());
    }

    @Test
    void apuestaNulaEsRechazada() {
        Ruleta ruleta = new Ruleta(100, new RepositorioFake());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.jugar(null)
        );

        assertEquals("Apuesta requerida", exception.getMessage());
    }

    @Test
    void apuestaConMontoMayorAlSaldoEsRechazada() {
        Ruleta ruleta = new Ruleta(100, new RepositorioFake());
        ApuestaBase apuesta = new ApuestaSiemprePierde(150);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.jugar(apuesta)
        );

        assertEquals("Saldo insuficiente", exception.getMessage());
    }

    static class ApuestaSiemprePierde extends ApuestaBase {

        public ApuestaSiemprePierde(int monto) {
            super(monto, "Prueba");
        }

        @Override
        public boolean acierta(int numero, String color) {
            return false;
        }
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