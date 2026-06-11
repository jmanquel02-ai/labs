package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String username;
    private String password;
    private String nombre;
    private List<Resultado> historial;

    public Usuario() {
        this.username = "invitado";
        this.password = "";
        this.nombre = "Invitado";
        this.historial = new ArrayList<>();
    }

    public Usuario(String username, String password, String nombre) {
        if (username == null || username.isBlank() ||
                password == null || password.isBlank() ||
                nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Datos requeridos");
        }

        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.historial = new ArrayList<>();
    }

    public boolean validarCredenciales(String u, String p) {
        if (u == null || p == null) {
            return false;
        }
        return this.username.equals(u) && this.password.equals(p);
    }

    public void agregarResultado(Resultado resultado) {
        historial.add(resultado);
    }

    public List<Resultado> getHistorial() {
        return historial;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }
}
