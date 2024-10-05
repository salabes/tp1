package org.ajedrez.model;

import java.util.List;

public class Jugador {
    private String nombre;
    private Color color;
    private List<Pieza> piezas;

    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void removerPieza(Pieza pieza) {
        piezas.remove(pieza);
    }
}
