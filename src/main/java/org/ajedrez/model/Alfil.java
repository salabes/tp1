package org.ajedrez.model;

import java.util.stream.IntStream;

/**
 * Clase que representa un Alfil en el juego de ajedrez.
 */
public class Alfil extends Pieza {

    /**
     * Constructor de la clase Alfil.
     *
     * @param color   Color de la pieza (blanco o negro).
     * @param fila    Fila inicial de la pieza en el tablero.
     * @param columna Columna inicial de la pieza en el tablero.
     */
    public Alfil(Color color, int fila, int columna) {
        super(color, fila, columna); // Llama al constructor de la clase padre Pieza
    }

    /**
     * Valida si el movimiento de la pieza es legal según las reglas del ajedrez.
     *
     * @param tablero   Tablero en el que se realiza el movimiento.
     * @param oi       Fila original de la pieza.
     * @param oj       Columna original de la pieza.
     * @param di       Fila de destino de la pieza.
     * @param dj       Columna de destino de la pieza.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    @Override
    public boolean validarMovimiento(Tablero tablero, int oi, int oj, int di, int dj) {
        // Si la pieza tiene un efecto "freeze", no puede moverse
        if (getEfecto() != null && getEfecto().getTipo() == "freeze") {
            return false;
        }

        int idiff = Math.abs(oi - di); // Diferencia en filas
        int jdiff = Math.abs(oj - dj); // Diferencia en columnas

        // Verifica si el movimiento es diagonal
        if (idiff == jdiff) {
            int isign = Integer.signum(di - oi); // Signo de la diferencia en filas
            int jsign = Integer.signum(dj - oj); // Signo de la diferencia en columnas

            // Verifica si el camino está libre
            boolean caminoLibre = IntStream.range(1, Math.max(idiff, jdiff))
                    .allMatch(x -> !tablero.estaOcupada(oi + isign * x, oj + jsign * x));

            // Si la pieza tiene un efecto "volar", el camino es considerado libre
            if (getEfecto() != null && getEfecto().getTipo() == "volar") {
                caminoLibre = true;
            }

            // Si el camino no está libre, el movimiento no es válido
            if (!caminoLibre) {
                return false;
            }

            // Verifica si la casilla de destino está ocupada
            if (tablero.estaOcupada(di, dj)) {
                Pieza piezaDestino = tablero.getPieza(di, dj).get(); // Obtiene la pieza en la casilla de destino
                // El movimiento es válido si la pieza de destino es de color diferente
                return !piezaDestino.getColor().equals(this.getColor());
            }

            return true; // Movimiento válido
        }

        return false; // Movimiento no válido
    }

    /**
     * Obtiene el tipo de la pieza.
     *
     * @return Un string que representa el tipo de pieza ("alfil").
     */
    @Override
    public TipoPieza getTipo() {
        return TipoPieza.ALFIL; // Tipo de pieza
    }
}
