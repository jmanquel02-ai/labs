package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;
import Modelo.Ruleta;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {

    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private JLabel lblBienvenida;
    private JLabel lblSaldo;
    private JButton btnJugar;
    private JButton btnPerfil;
    private JButton btnCerrarSesion;

    public VentanaMenu(SessionController sessionController) {
        this.sessionController = sessionController;
        this.ruletaController = new RuletaController(new Ruleta(1000));
        inicializar();
    }

    private void inicializar() {
        setTitle("Menú Principal");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        lblBienvenida = new JLabel("Bienvenido: " + sessionController.getNombreUsuario(), SwingConstants.CENTER);
        lblSaldo = new JLabel("Saldo: $" + ruletaController.getSaldo(), SwingConstants.CENTER);

        btnJugar = new JButton("Jugar");
        btnPerfil = new JButton("Perfil");
        btnCerrarSesion = new JButton("Cerrar sesión");

        add(lblBienvenida);
        add(lblSaldo);
        add(btnJugar);
        add(btnPerfil);
        add(btnCerrarSesion);

        btnJugar.addActionListener(e -> abrirJuego());
        btnPerfil.addActionListener(e -> mostrarPerfil());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    private void abrirJuego() {
        new VentanaJuego(sessionController, ruletaController).setVisible(true);
        dispose();
    }

    private void mostrarPerfil() {
        JOptionPane.showMessageDialog(this,
                "Nombre: " + sessionController.getUsuarioActual().getNombre() +
                        "\nUsuario: " + sessionController.getUsuarioActual().getUsername() +
                        "\nSaldo: $" + ruletaController.getSaldo());
    }

    private void cerrarSesion() {
        sessionController.cerrarSesion();
        new VentanaLogin(sessionController).setVisible(true);
        dispose();
    }
}