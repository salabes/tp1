package org.ajedrez.model;

import org.ajedrez.model.Efectos.TipoEfecto;

import java.util.Optional;

/**
 * Clase que gestiona la validación de los movimientos de las piezas en el tablero de ajedrez.
 */
public class AdministradorDeMovimientos {

    /**
     * Verifica si un movimiento es inválido según las reglas del juego y las condiciones del tablero.
     *
     * @param tablero El objeto Tablero donde se encuentran las piezas.
     * @param filaOriginal Fila de la posición original de la pieza.
     * @param columnaOriginal Columna de la posición original de la pieza.
     * @param filaDestino Fila de la posición destino a la que se quiere mover la pieza.
     * @param columnaDestino Columna de la posición destino a la que se quiere mover la pieza.
     * @return true si el movimiento es inválido, false en caso contrario.
     */
    public boolean movimientoInValido(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        // Obtiene la pieza en la posición original
        Optional<Pieza> piezaOpt = tablero.getPieza(filaOriginal, columnaOriginal);

        // Verifica si hay una pieza en la posición destino
        if (tablero.getPieza(filaDestino, columnaDestino).isPresent()) {
            Pieza piezaDest = tablero.getPieza(filaDestino, columnaDestino).get();
            // Verifica si la pieza destino tiene un efecto de protección
            if (piezaDest.getEfecto() != null && piezaDest.getEfecto().getTipo() == TipoEfecto.PROTECT) {
                return true; // El movimiento es inválido debido a la protección
            }
        }

        // Verifica límites del tablero y si el movimiento de la pieza es válido
        if (columnaDestino < 0 || columnaDestino >= 8 || filaDestino < 0 || filaDestino >= 8 ||
                !piezaOpt.get().validarMovimiento(tablero, filaOriginal, columnaOriginal, filaDestino, columnaDestino)) {
            return true; // El movimiento es inválido
        }

        return false; // El movimiento es válido
    }
}
