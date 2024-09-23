package org.ajedrez.model;

public class Jugador {
    String nombre;
    Integer color;
    list<Pieza> piezas;

    public Jugador(String nombre, Integer color) {
        this.nombre = nombre;
        this.color = color;
        piezas = new list<Pieza>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getColor() {
        return this.color;
    }

}
