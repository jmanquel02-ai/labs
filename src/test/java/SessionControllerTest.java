import Controlador.SessionController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionControllerTest {

    @Test
    void inicioSesionConUsuarioNoRegistradoLanzaExcepcionDeDominio() {
        SessionController sessionController = new SessionController();

        sessionController.registrarUsuario("jonathan", "1234", "Jonathan");

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> sessionController.iniciarSesion("usuario_no_registrado", "1234")
        );

        assertEquals("Credenciales incorrectas", exception.getMessage());
    }

    @Test
    void inicioSesionSinUsuariosRegistradosLanzaExcepcionDeDominio() {
        SessionController sessionController = new SessionController();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> sessionController.iniciarSesion("jonathan", "1234")
        );

        assertEquals("Credenciales incorrectas", exception.getMessage());
    }

    @Test
    void inicioSesionConUsernameNuloEsRechazadoSinExcepcion() {
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

    @Test
    void registroDuplicadoLanzaIllegalStateException() {
        SessionController sessionController = new SessionController();

        sessionController.registrarUsuario("jonathan", "1234", "Jonathan");

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> sessionController.registrarUsuario("jonathan", "abcd", "Otro Jonathan")
        );

        assertEquals("El usuario ya está registrado", exception.getMessage());
    }

    @Test
    void getUsuarioActualSinSesionLanzaIllegalStateException() {
        SessionController sessionController = new SessionController();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                sessionController::getUsuarioActual
        );

        assertEquals("No hay una sesión activa", exception.getMessage());
    }
}
