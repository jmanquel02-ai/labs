package Launcher;

import Controlador.SessionController;
import Vista.ManejadorErroresSwing;
import Vista.VentanaLogin;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        ManejadorErroresSwing.configurar();

        SwingUtilities.invokeLater(() -> {
            SessionController session = new SessionController();
            VentanaLogin login = new VentanaLogin(session);
            login.setVisible(true);
        });
    }
}
