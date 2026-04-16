package Modelo;

public class Usuario {

    private String username;
    private String password;
    private String nombre;

    public Usuario() {
        this.username = "invitado";
        this.password = "";
        this.nombre = "Invitado";
    }

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
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