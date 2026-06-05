package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;
import Modelo.Resultado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorial extends JFrame {

    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private JTextArea txtHistorial;
    private JButton btnVolver;

    public VentanaHistorial(SessionController sessionController, RuletaController ruletaController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;
        inicializar();
    }

    private void inicializar() {
        setTitle("Historial");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        txtHistorial = new JTextArea();
        txtHistorial.setEditable(false);
        cargarHistorial();

        btnVolver = new JButton("Volver");

        add(new JScrollPane(txtHistorial), BorderLayout.CENTER);
        add(btnVolver, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> volverMenu());
    }

    private void cargarHistorial() {
        List<Resultado> historial = ruletaController.obtenerHistorial();

        if (historial.isEmpty()) {
            txtHistorial.setText("No hay jugadas registradas todavía.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < historial.size(); i++) {
            Resultado r = historial.get(i);

            sb.append("Jugada ").append(i + 1).append("\n");
            sb.append("Número: ").append(r.getNumero()).append("\n");
            sb.append("Color: ").append(r.getColor()).append("\n");
            sb.append("Tipo de apuesta: ").append(r.getTipoApuesta()).append("\n");
            sb.append("Monto: $").append(r.getMonto()).append("\n");
            sb.append("Resultado: ").append(r.isAcierto() ? "Ganó" : "Perdió").append("\n");
            sb.append("-------------------------\n");
        }

        txtHistorial.setText(sb.toString());
    }

    private void volverMenu() {
        new VentanaMenu(sessionController, ruletaController).setVisible(true);
        dispose();
    }
}
