package org.ajedrez.model;

public abstract class Pieza {

    public Integer color;
    public Pieza(Integer color){
        this.color = color;

    }
    public abstract boolean validarMovimiento(Tablero tablero, Integer oi, Integer oj, Integer di, Integer dj);

    public abstract Integer getColor();
}