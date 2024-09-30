package org.ajedrez.model;

public abstract class Pieza {
    private String imagen;
    private Color color;

    public Pieza(String imagen,Color color) {
        this.imagen = imagen;
        this.color = color;
    }

    public String getImagen() {
        return imagen;
    }
    public Color getColor(){
        return color;
    }

    public abstract boolean validarMovimiento(Tablero tablero, int filaOriginal , int columnaOiriginal, int filaDestino, int columnaDestino);
    //validarMovimiento devuelve true si el movimiento es valido o false si no lo es.
}
