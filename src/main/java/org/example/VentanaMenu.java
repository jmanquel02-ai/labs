package org.example;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final Usuario usuario;
    private final Ruleta ruleta = new Ruleta();
    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JButton btnJugar     = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir     = new JButton("Salir");

    public VentanaMenu(Usuario usuario) {
        this.usuario = usuario;
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(400, 250);
        frame.setLayout(new BorderLayout(10, 10));
        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuario.getNombre(), SwingConstants.CENTER);
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 5, 5));
        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);
        frame.add(lblBienvenida, BorderLayout.NORTH);
        frame.add(panelBotones, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> new VentanaJuego(ruleta).mostrarVentana());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnSalir.addActionListener(e -> cerrarSesion());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void mostrarHistorial() {
        JOptionPane.showMessageDialog(frame, ruleta.obtenerEstadisticas());
    }

    private void cerrarSesion() {
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }
}