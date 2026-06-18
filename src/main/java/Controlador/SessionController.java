package Controlador;

import Modelo.Usuario;

import java.util.HashMap;
import java.util.Map;

public class SessionController {

    private final Map<String, Usuario> usuarios = new HashMap<>();
    private Usuario usuarioActual;

    public void registrarUsuario(String username, String password, String nombre) {
        if (username == null || username.isBlank() ||
                password == null || password.isBlank() ||
                nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Datos requeridos");
        }

        String usernameNormalizado = username.trim();

        if (usuarios.containsKey(usernameNormalizado)) {
            throw new IllegalStateException("El usuario ya está registrado");
        }

        Usuario nuevoUsuario = new Usuario(usernameNormalizado, password, nombre.trim());
        usuarios.put(usernameNormalizado, nuevoUsuario);
        usuarioActual = nuevoUsuario;
    }

    public boolean iniciarSesion(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }

        Usuario usuario = usuarios.get(username.trim());

        if (usuario == null || !usuario.validarCredenciales(username.trim(), password)) {
            throw new IllegalStateException("Credenciales incorrectas");
        }

        usuarioActual = usuario;
        return true;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual() {
        if (!hayUsuario()) {
            throw new IllegalStateException("No hay una sesión activa");
        }
        return usuarioActual;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}
