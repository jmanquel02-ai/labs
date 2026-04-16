package Launcher;

import Controlador.SessionController;
import Vista.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        SessionController session = new SessionController();
        VentanaLogin login = new VentanaLogin(session);
        login.setVisible(true);
    }
}