package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrar");

    public VentanaLogin() {
        inicializarUsuarios();
        configurarVentana();
        configurarEventos();
    }

    private void inicializarUsuarios() {
        if (!USUARIOS.isEmpty()) return;
        USUARIOS.add(new Usuario("jona", "1234", "Administrador"));
        USUARIOS.add(new Usuario("basti", "abcd", "Juan Perez"));
    }

    private void configurarVentana() {
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 2, 5, 5));
        frame.add(new JLabel("Usuario:")); frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));  frame.add(txtClave);
        frame.add(btnIngresar);           frame.add(btnRegistrar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        Usuario encontrado = buscarUsuario(u, p);
        if (encontrado != null) {
            frame.dispose();
            new VentanaMenu(encontrado).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas");
        }
    }

    private Usuario buscarUsuario(String u, String p) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.validarCredenciales(u, p)) return usuario;
        }
        return null;
    }

    private void abrirRegistro() {
        frame.dispose();
        new VentanaRegistro().mostrarVentana();
    }
}