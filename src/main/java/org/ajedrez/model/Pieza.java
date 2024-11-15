package org.ajedrez.model;

import org.ajedrez.model.Efectos.Efecto;

/**
 * Clase abstracta que representa una pieza en una partida de ajedrez.
 */
public abstract class Pieza {
    private Color color; // Color de la pieza (blanco o negro)
    private int fila;    // Fila actual de la pieza
    private int columna; // Columna actual de la pieza
    private Efecto efecto; // Efecto que puede tener la pieza

    /**
     * Constructor de la clase Pieza.
     *
     * @param color Color de la pieza.
     * @param fila Fila inicial de la pieza.
     * @param columna Columna inicial de la pieza.
     */
    public Pieza(Color color, int fila, int columna) {
        this.color = color;
        this.fila = fila;
        this.columna = columna;
    }

    // Métodos de acceso (setters y getters)

    public void setFila(int fila) {
        this.fila = fila; // Establece la fila de la pieza
    }

    public void setColumna(int columna) {
        this.columna = columna; // Establece la columna de la pieza
    }

    public void setEfecto(Efecto efecto) {
        this.efecto = efecto; // Establece el efecto de la pieza
    }


    public Color getColor() {
        return color; // Retorna el color de la pieza
    }

    public int getFila() {
        return fila; // Retorna la fila actual de la pieza
    }

    public int getColumna() {
        return columna; // Retorna la columna actual de la pieza
    }

    public Efecto getEfecto() {
        return efecto; // Retorna el efecto de la pieza
    }

    /**
     * Obtiene el tipo de pieza. Este método debe ser implementado en las clases derivadas.
     *
     * @return Tipo de la pieza.
     */
    public abstract TipoPieza getTipo();



    /**
     * Valida si el movimiento de la pieza es válido.
     *
     * @param tablero Tablero en el que se realiza el movimiento.
     * @param filaOriginal Fila de la posición original de la pieza.
     * @param columnaOriginal Columna de la posición original de la pieza.
     * @param filaDestino Fila de la posición de destino de la pieza.
     * @param columnaDestino Columna de la posición de destino de la pieza.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    public abstract boolean validarMovimiento(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino);

    /**
     * Verifica si la pieza tiene un efecto activo.
     *
     * @return true si hay un efecto activo, false en caso contrario.
     */
    public boolean isefectoactivo() {
        if (this.efecto != null) {
            return this.efecto.isEfectoActivo(); // Verifica si el efecto está activo
        }
        return false; // No hay efecto activo
    }
}
