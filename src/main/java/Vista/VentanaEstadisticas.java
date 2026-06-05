package Vista;

import Controlador.EstadisticasController;
import Controlador.RuletaController;

import javax.swing.*;
import java.awt.*;

public class VentanaEstadisticas extends JFrame {

    private final EstadisticasController estadisticasController;

    public VentanaEstadisticas(RuletaController ruletaController) {
        this.estadisticasController = new EstadisticasController(ruletaController);

        setTitle("Estadísticas de la Ruleta");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        cargarEstadisticas();
    }

    private void cargarEstadisticas() {
        String tipoMasJugado = estadisticasController.obtenerTipoMasJugado();

        add(new JLabel("Total de jugadas: " + estadisticasController.obtenerTotalJugadas()));
        add(new JLabel("Victorias: " + estadisticasController.obtenerVictorias()));
        add(new JLabel("Porcentaje de victorias: " + String.format("%.2f", estadisticasController.obtenerPorcentajeVictorias()) + "%"));
        add(new JLabel("Racha máxima: " + estadisticasController.obtenerRachaMaxima()));
        add(new JLabel("Tipo más jugado: " + (tipoMasJugado != null ? tipoMasJugado : "Sin jugadas")));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}
