package Modelo.Persistencia;

import Modelo.Resultado;
import java.util.List;

public interface IRepositorioResultados {

    void guardar(Resultado resultado);

    List<Resultado> obtenerTodos();

    void limpiar();
}
