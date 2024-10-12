package org.ajedrez.model;

/**
 * Clase que representa un Caballo en el juego de ajedrez.
 */
public class Caballo extends Pieza {

    /**
     * Constructor de la clase Caballo.
     *
     * @param color   Color de la pieza (blanco o negro).
     * @param fila    Fila inicial de la pieza en el tablero.
     * @param columna Columna inicial de la pieza en el tablero.
     */
    public Caballo(Color color, int fila, int columna) {
        super(color, fila, columna); // Llama al constructor de la clase padre Pieza
    }

    /**
     * Valida si el movimiento de la pieza es legal según las reglas del ajedrez.
     *
     * @param tablero        Tablero en el que se realiza el movimiento.
     * @param filaOriginal   Fila original de la pieza.
     * @param columnaOriginal Columna original de la pieza.
     * @param filaDestino    Fila de destino de la pieza.
     * @param columnaDestino  Columna de destino de la pieza.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    @Override
    public boolean validarMovimiento(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        // Si la pieza tiene un efecto "freeze", no puede moverse
        if (getEfecto() != null && getEfecto().getTipo() == "freeze") {
            return false;
        }

        // Calcular la diferencia entre las posiciones originales y de destino
        int difFila = Math.abs(filaDestino - filaOriginal);   // Diferencia en filas
        int difColumna = Math.abs(columnaDestino - columnaOriginal);  // Diferencia en columnas

        // El caballo se mueve en "L", lo que significa que la diferencia en filas y columnas debe ser:
        // 2 casillas en una dirección y 1 casilla en la otra, o viceversa.
        boolean movimientoEnL = (difFila == 2 && difColumna == 1) || (difFila == 1 && difColumna == 2);

        // Si no es un movimiento en "L", es inválido
        if (!movimientoEnL) {
            return false;
        }

        // Obtener la pieza que se encuentra en la casilla de destino
        Pieza piezaDestino = tablero.getPieza(filaDestino, columnaDestino).orElse(null);

        // Verificar si la casilla de destino está vacía o si contiene una pieza del equipo contrario
        if (piezaDestino == null || piezaDestino.getColor() != this.getColor()) {
            return true;  // Movimiento válido
        } else {
            return false;  // Movimiento inválido porque hay una pieza del mismo color en la casilla de destino
        }
    }

    /**
     * Obtiene el tipo de la pieza.
     *
     * @return Un string que representa el tipo de pieza ("caballo").
     */
    @Override
    public String getTipo() {
        return "caballo"; // Tipo de pieza
    }
}
