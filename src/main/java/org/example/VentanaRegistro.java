package org.example;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtNombre   = new JTextField();
    private final JTextField txtUsuario  = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnRegistrar   = new JButton("Crear cuenta");
    private final JButton btnVolver      = new JButton("Volver");

    public VentanaRegistro() {
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(320, 220);
        frame.setLayout(new GridLayout(5, 2, 5, 5));
        frame.add(new JLabel("Nombre:"));  frame.add(txtNombre);
        frame.add(new JLabel("Usuario:")); frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));   frame.add(txtClave);
        frame.add(btnRegistrar);           frame.add(btnVolver);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnVolver.addActionListener(e -> volver());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrar() {
        String nombre  = txtNombre.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String clave   = new String(txtClave.getPassword()).trim();

        if (nombre.isEmpty() || usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos");
            return;
        }

        VentanaLogin.USUARIOS.add(new Usuario(usuario, clave, nombre));
        JOptionPane.showMessageDialog(frame, "Usuario registrado correctamente");
        volver();
    }

    private void volver() {
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }
}