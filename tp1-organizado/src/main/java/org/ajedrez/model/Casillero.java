package org.ajedrez.model;

public class Casillero {
    private Pieza pieza;
    private boolean ocupado;

    public Casillero() {
        pieza = null;
        ocupado = false;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
        ocupado = true;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public boolean estaOcupada() {
        return ocupado;
    }

    public void limpiarCasillero() {
        pieza = null;
        ocupado = false;
    }
}