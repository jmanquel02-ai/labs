package org.example;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego {
    private final Ruleta ruleta;
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JComboBox<String> cbTipo = new JComboBox<>(new String[]{"R - Rojo", "N - Negro", "P - Par", "I - Impar"});
    private final JTextField txtMonto = new JTextField("100");
    private final JButton btnGirar = new JButton("Girar");
    private final JTextArea areaResultados = new JTextArea();

    public VentanaJuego(Ruleta ruleta) {
        this.ruleta = ruleta;
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout(10, 10));
        JPanel panelSuperior = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSuperior.add(new JLabel("Tipo de apuesta:")); panelSuperior.add(cbTipo);
        panelSuperior.add(new JLabel("Monto:"));           panelSuperior.add(txtMonto);
        panelSuperior.add(new JLabel(""));                 panelSuperior.add(btnGirar);
        areaResultados.setEditable(false);
        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(new JScrollPane(areaResultados), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void configurarEventos() {
        btnGirar.addActionListener(e -> jugar());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void jugar() {
        int monto = leerMonto();
        if (monto <= 0) {
            JOptionPane.showMessageDialog(frame, "Ingrese un monto válido");
            return;
        }
        char tipo = cbTipo.getSelectedItem().toString().charAt(0);
        int numero = ruleta.girarRuleta();
        boolean acierto = ruleta.evaluarResultado(numero, tipo);
        ruleta.registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }

    private int leerMonto() {
        try {
            return Integer.parseInt(txtMonto.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        String estado = acierto ? "GANASTE" : "PERDISTE";
        areaResultados.append("Número " + numero + " | Apuesta=" + tipo +
                " | Monto=$" + monto + " | " + estado + "\n");
    }
}