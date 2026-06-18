package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.Apuestas.ApuestaBase;
import Modelo.Apuestas.ApuestaFactory;
import Modelo.Resultado;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private JComboBox<String> cboTipo;
    private JTextField txtMonto;
    private JLabel lblSaldo;
    private JButton btnJugar;
    private JButton btnVolver;

    public VentanaJuego(SessionController sessionController, RuletaController ruletaController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;
        inicializar();
    }

    private void inicializar() {
        setTitle("Ruleta");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Tipo de apuesta:"));
        cboTipo = new JComboBox<>(new String[]{"ROJO", "NEGRO", "PAR", "IMPAR"});
        add(cboTipo);

        add(new JLabel("Monto:"));
        txtMonto = new JTextField();
        add(txtMonto);

        lblSaldo = new JLabel("Saldo: $" + ruletaController.getSaldo());
        add(lblSaldo);

        btnJugar = new JButton("Jugar");
        btnVolver = new JButton("Volver");

        add(btnJugar);
        add(btnVolver);

        btnJugar.addActionListener(e -> jugar());
        btnVolver.addActionListener(e -> volverMenu());
    }

    private void jugar() {
        if (!sessionController.hayUsuario()) {
            JOptionPane.showMessageDialog(this, "No hay una sesión activa. Debe iniciar sesión nuevamente.");
            new VentanaLogin(sessionController).setVisible(true);
            dispose();
            return;
        }

        String tipo = (String) cboTipo.getSelectedItem();
        String textoMonto = txtMonto.getText().trim();

        if (tipo == null || tipo.isBlank()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de apuesta");
            return;
        }

        if (textoMonto.isBlank()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto");
            return;
        }

        int monto;

        try {
            monto = Integer.parseInt(textoMonto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un número entero");
            return;
        }

        if (monto <= 0) {
            JOptionPane.showMessageDialog(this, "El monto debe ser mayor que 0");
            return;
        }

        if (monto > ruletaController.getSaldo()) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente");
            return;
        }

        try {
            ApuestaBase apuesta = ApuestaFactory.crear(tipo, monto);
            Resultado resultado = ruletaController.jugar(apuesta);

            String mensaje = resultado.isAcierto() ? "Ganaste" : "Perdiste";

            JOptionPane.showMessageDialog(this,
                    mensaje
                            + "\nNúmero salido: " + resultado.getNumero()
                            + "\nColor: " + resultado.getColor()
                            + "\nApuesta: " + resultado.getTipoApuesta());

            refrescarSaldo();

        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void refrescarSaldo() {
        lblSaldo.setText("Saldo: $" + ruletaController.getSaldo());
    }

    private void volverMenu() {
        new VentanaMenu(sessionController, ruletaController).setVisible(true);
        dispose();
    }
}
