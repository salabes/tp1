package org.ajedrez.model;

import java.util.stream.IntStream;
import org.ajedrez.model.Efectos.TipoEfecto;

/**
 * Clase que representa la pieza Reina en una partida de ajedrez.
 */
public class Reina extends Pieza {

    /**
     * Constructor de la clase Reina.
     *
     * @param color Color de la pieza (blanco o negro).
     * @param fila Fila inicial de la pieza.
     * @param columna Columna inicial de la pieza.
     */
    public Reina(Color color, int fila, int columna) {
        super(color, fila, columna);
    }

    /**
     * Valida si el movimiento de la Reina es válido.
     *
     * @param tablero Tablero en el que se realiza el movimiento.
     * @param oi Fila de la posición original de la Reina.
     * @param oj Columna de la posición original de la Reina.
     * @param di Fila de la posición de destino de la Reina.
     * @param dj Columna de la posición de destino de la Reina.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    @Override
    public boolean validarMovimiento(Tablero tablero, int oi, int oj, int di, int dj) {
        // Verificar si hay un efecto de congelamiento activo
        if (getEfecto() != null && getEfecto().getTipo() == TipoEfecto.FREEZE) {
            return false; // No se puede mover si está congelada
        }

        // Calcular las diferencias en filas y columnas
        int idiff = Math.abs(oi - di);
        int jdiff = Math.abs(oj - dj);

        // Comprobar si el movimiento es diagonal, vertical u horizontal
        if (idiff == jdiff || idiff == 0 || jdiff == 0) {
            int isign = Integer.signum(di - oi); // Signo de la fila
            int jsign = Integer.signum(dj - oj); // Signo de la columna

            // Verificar si el camino está libre
            boolean caminoLibre = IntStream.range(1, Math.max(idiff, jdiff))
                    .allMatch(x -> !tablero.estaOcupada(oi + isign * x, oj + jsign * x));

            // Si la Reina tiene un efecto de vuelo, el camino se considera libre
            if (getEfecto() != null && getEfecto().getTipo() == TipoEfecto.VOLAR) {
                caminoLibre = true;
            }

            // Si el camino no está libre, el movimiento no es válido
            if (!caminoLibre) {
                return false;
            }

            // Si hay una pieza en la posición de destino
            if (tablero.estaOcupada(di, dj)) {
                Pieza piezaDestino = tablero.getPieza(di, dj).get();
                // La Reina puede capturar la pieza si es de color diferente
                return !piezaDestino.getColor().equals(this.getColor());
            }

            return true; // Movimiento válido si no hay pieza en la posición de destino
        }

        return false; // Movimiento no válido
    }

    /**
     * Obtiene el tipo de la pieza.
     *
     * @return Tipo de la pieza (en este caso, "reina").
     */
    @Override
    public TipoPieza getTipo() {
        return TipoPieza.REINA; // Tipo de pieza
    }
}
