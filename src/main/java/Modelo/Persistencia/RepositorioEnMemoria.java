package Modelo.Persistencia;

import Modelo.Resultado;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria implements IRepositorioResultados {

    private final List<Resultado> resultados;

    public RepositorioEnMemoria() {
        this.resultados = new ArrayList<>();
    }

    @Override
    public void guardar(Resultado resultado) {
        resultados.add(resultado);
    }

    @Override
    public List<Resultado> obtenerTodos() {
        return new ArrayList<>(resultados);
    }

    @Override
    public void limpiar() {
        resultados.clear();
    }
}
