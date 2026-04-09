package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


// Clase Usuario (NO pública)
class Usuario {
    private String username;
    private String password;
    private String nombre;

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }
}

// Clase VentanaLogin (NO pública)
class VentanaLogin {

    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private JFrame frame;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;

    public VentanaLogin() {
        inicializarUsuarios();
        configurarVentana();
        configurarEventos();
    }

    private void inicializarUsuarios() {
        USUARIOS.add(new Usuario("jona", "1234", "Administrador"));
        USUARIOS.add(new Usuario("basti", "abcd", "Juan Perez"));
    }

    private void configurarVentana() {
        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));

        txtUsuario = new JTextField();
        txtClave = new JPasswordField();
        btnIngresar = new JButton("Ingresar");

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);
        frame.add(new JLabel(""));
        frame.add(btnIngresar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());

        String nombre = validarCredenciales(usuario, clave);

        if (!nombre.equals("")) {
            JOptionPane.showMessageDialog(frame, "Bienvenido " + nombre);
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas");
        }
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.validarCredenciales(u, p)) {
                return usuario.getNombre();
            }
        }
        return "";
    }
}

// ÚNICA clase pública
public class lab02 {
    public static void main(String[] args) {
        VentanaLogin v = new VentanaLogin();
        v.mostrarVentana();
    }
}