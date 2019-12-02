package com.example.pspretrofiteloquentliga.model.data;

public class Jugador {
    private int id;
    private int idequipo;
    private String nombre;
    private String apellidos;
    private String imagen_jugador;

    public Jugador() {
    }

    public Jugador(int id, int idequipo, String nombre, String apellidos, String imagenJugador) {
        this.id = id;
        this.idequipo = idequipo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.imagen_jugador = imagenJugador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(int idequipo) {
        this.idequipo = idequipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getImagenJugador() {
        return imagen_jugador;
    }

    public void setImagenJugador(String imagenJugador) {
        this.apellidos = imagenJugador;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", imagenJugador='" + imagen_jugador + '\'' +
                '}';
    }
}
