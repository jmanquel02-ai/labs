package Controlador;

import Modelo.Usuario;

public class SessionController {

    private Usuario usuarioActual;

    public void registrarUsuario(String username, String password, String nombre) {
        if (username == null || username.isBlank() ||
                password == null || password.isBlank() ||
                nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Datos requeridos");
        }

        this.usuarioActual = new Usuario(username, password, nombre);
    }

    public boolean iniciarSesion(String username, String password) {
        if (usuarioActual == null) {
            return false;
        }
        return usuarioActual.validarCredenciales(username, password);
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}