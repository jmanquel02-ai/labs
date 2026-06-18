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
        if (ruleta == null) {
            throw new IllegalArgumentException("Ruleta requerida");
        }

        if (sessionController == null) {
            throw new IllegalArgumentException("Sesión requerida");
        }

        this.ruleta = ruleta;
        this.sessionController = sessionController;
        this.repositorioResultados = ruleta.getRepositorioResultados();
    }

    public Resultado jugar(ApuestaBase apuesta) {
        if (!sessionController.hayUsuario()) {
            throw new IllegalStateException("No hay una sesión activa");
        }

        Resultado resultado = ruleta.jugar(apuesta);
        sessionController.getUsuarioActual().agregarResultado(resultado);

        return resultado;
    }

    public List<Resultado> obtenerHistorial() {
        if (!sessionController.hayUsuario()) {
            throw new IllegalStateException("No hay una sesión activa");
        }

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
