package com.example.pspretrofiteloquentliga.model.data;

public class Equipo {
    public int id;
    public String nombre;
    public String ciudad;
    public String estadio;
    public int aforo;
    public String imagen_equipo;

    public Equipo() {
    }

    public Equipo(int id, String nombre, String ciudad, String estadio, int aforo, String imagenEquipo) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.aforo = aforo;
        this.imagen_equipo = imagenEquipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getImagenEquipo(){
        return imagen_equipo;
    }

    public void setImagenEquipo(String imagenEquipo){
        this.imagen_equipo = imagenEquipo;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estadio='" + estadio + '\'' +
                ", aforo=" + aforo +
                ", imagenEquipo=" + imagen_equipo +
                '}';
    }
}
