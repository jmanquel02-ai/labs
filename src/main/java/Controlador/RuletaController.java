package Controlador;

import Modelo.Apuestas.ApuestaBase;
import Modelo.Persistencia.IRepositorioResultados;
import Modelo.Resultado;
import Modelo.Ruleta;

import java.util.List;

public class RuletaController {

    private final Ruleta ruleta;
    private final SessionController sessionController;
    private final IRepositorioResultados repositorioResultados;

    public RuletaController(Ruleta ruleta, SessionController sessionController) {
        this.ruleta = ruleta;
        this.sessionController = sessionController;
        this.repositorioResultados = ruleta.getRepositorioResultados();
    }

    public Resultado jugar(ApuestaBase apuesta) {
        Resultado resultado = ruleta.jugar(apuesta);

        sessionController.getUsuarioActual().agregarResultado(resultado);

        return resultado;
    }

    public List<Resultado> obtenerHistorial() {
        return repositorioResultados.obtenerTodos();
    }

    public IRepositorioResultados getRepositorioResultados() {
        return repositorioResultados;
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public void depositar(int monto) {
        ruleta.depositar(monto);
    }

    public String obtenerEstadisticas() {
        return ruleta.obtenerEstadisticas();
    }

    public Ruleta getRuleta() {
        return ruleta;
    }
}
