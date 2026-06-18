package Vista;

import javax.swing.*;

public class ManejadorErroresSwing {

    private ManejadorErroresSwing() {
    }

    public static void configurar() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            throwable.printStackTrace();
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                    null,
                    "Ocurrió un error inesperado: " + obtenerMensaje(throwable),
                    "Error inesperado",
                    JOptionPane.ERROR_MESSAGE
            ));
        });
    }

    private static String obtenerMensaje(Throwable throwable) {
        if (throwable == null || throwable.getMessage() == null || throwable.getMessage().isBlank()) {
            return "revise la consola para más detalles";
        }
        return throwable.getMessage();
    }
}
