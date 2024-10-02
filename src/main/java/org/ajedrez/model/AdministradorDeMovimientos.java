package org.ajedrez.model;

import java.util.Optional;

public class AdministradorDeMovimientos {
    public static boolean movimientoInValido(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        Optional<Pieza> piezaOpt = tablero.getPieza(filaOriginal, columnaOriginal);
        if (!piezaOpt.isPresent() || columnaDestino < 0 || columnaDestino >= 8 || filaDestino < 0 || filaDestino >= 8 ||
                !piezaOpt.get().validarMovimiento(tablero, filaOriginal, columnaOriginal, filaDestino, columnaDestino)) {
            return true;
        }
        return false;
    }

}
/*
package org.ajedrez.model;

public class AdministradorDeMovimientos {

    // Método para validar el movimiento de una pieza en el tablero
    public boolean esMovimientoValido(Pieza pieza, Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        // Verificar si la pieza puede moverse al destino usando su método validarMovimiento
        if (pieza.validarMovimiento(tablero, filaOriginal, columnaOriginal, filaDestino, columnaDestino)) {
            // Si el movimiento es válido, realizamos verificaciones adicionales (opcional)
            // Por ejemplo, si hay alguna pieza en la posición de destino y si es del mismo color
            Pieza piezaDestino = tablero.getPieza(filaDestino, columnaDestino).orElse(null);
            if (piezaDestino == null || !piezaDestino.getColor().equals(pieza.getColor())) {
                return true; // Movimiento válido
            } else {
                System.out.println("No puedes capturar tu propia pieza.");
                return false; // Movimiento inválido
            }
        }
        return false; // Movimiento inválido
    }

    // Método para realizar el movimiento si es válido
    public boolean moverPieza(Pieza pieza, Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        if (esMovimientoValido(pieza, tablero, filaOriginal, columnaOriginal, filaDestino, columnaDestino)) {
            tablero.moverPieza(filaOriginal, columnaOriginal, filaDestino, columnaDestino);
            return true;
        } else {
            System.out.println("Movimiento no válido.");
            return false;
        }
    }
}

 */