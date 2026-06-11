import Controlador.SessionController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionControllerTest {

    @Test
    void inicioSesionConUsuarioNoRegistradoEsRechazado() {
        SessionController sessionController = new SessionController();

        sessionController.registrarUsuario("jonathan", "1234", "Jonathan");

        boolean resultado = sessionController.iniciarSesion("usuario_no_registrado", "1234");

        assertFalse(resultado);
    }

    @Test
    void inicioSesionSinUsuariosRegistradosEsRechazado() {
        SessionController sessionController = new SessionController();

        boolean resultado = sessionController.iniciarSesion("jonathan", "1234");

        assertFalse(resultado);
    }

    @Test
    void inicioSesionConUsernameNuloEsRechazado() {
        SessionController sessionController = new SessionController();

        sessionController.registrarUsuario("jonathan", "1234", "Jonathan");

        boolean resultado = sessionController.iniciarSesion(null, "1234");

        assertFalse(resultado);
    }

    @Test
    void registroConUsernameNuloEsRechazado() {
        SessionController sessionController = new SessionController();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> sessionController.registrarUsuario(null, "1234", "Jonathan")
        );

        assertEquals("Datos requeridos", exception.getMessage());
    }
}
