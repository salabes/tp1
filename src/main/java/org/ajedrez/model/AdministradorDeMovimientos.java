package org.ajedrez.model;

import org.ajedrez.model.Efectos.TipoEfecto;

import java.util.ArrayList;
import java.util.List;
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
    public static boolean movimientoInValido(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        // Obtiene la pieza en la posición original
        Optional<Pieza> piezaOpt = tablero.getPieza(filaOriginal, columnaOriginal);
        Pieza piezaMovida = piezaOpt.get();

        // Verifica si hay una pieza en la posición destino y si está protegida
        if (tablero.getPieza(filaDestino, columnaDestino).isPresent()) {
            Pieza piezaDest = tablero.getPieza(filaDestino, columnaDestino).get();
            if (piezaDest.getEfecto() != null && piezaDest.getEfecto().getTipo() == TipoEfecto.PROTECT) {
                return true; // Movimiento inválido debido a la protección
            }
        }

        // Verifica los límites del tablero y si el movimiento de la pieza es válido
        if (columnaDestino < 0 || columnaDestino >= 8 || filaDestino < 0 || filaDestino >= 8 ||
                !piezaMovida.validarMovimiento(tablero, filaOriginal, columnaOriginal, filaDestino, columnaDestino)) {
            return true; // Movimiento inválido
        }

        // Simula el movimiento temporalmente en el tablero (incluyendo capturas)
        Optional<Pieza> piezaDestinoOpt = tablero.getPieza(filaDestino, columnaDestino);
        tablero.eliminarPieza(filaDestino, columnaDestino);
        tablero.moverPieza(filaOriginal, columnaOriginal, filaDestino, columnaDestino);

        // Verifica si aún hay jaque después del movimiento
        boolean hayJaqueDespuesDeMover = hayJaque(tablero, piezaMovida.getColor());

        // Revierte el movimiento para restaurar el estado original
        tablero.moverPieza(filaDestino, columnaDestino, filaOriginal, columnaOriginal);
        piezaDestinoOpt.ifPresent(p -> tablero.agregarPieza(p, filaDestino, columnaDestino));

        // Si el movimiento elimina el jaque (incluyendo capturas), es válido
        return hayJaqueDespuesDeMover;
    }

    public static boolean hayJaque(Tablero tablero, Color color) {
        List<Pieza> piezas = tablero.getPiezas();

        for (Pieza pieza : piezas) {
            if (pieza.getColor() != color) {
                List<int[]> posicionesFinales = obtenerPosicionesValidas(tablero, pieza, pieza.getFila(), pieza.getColumna());
                for (int[] posicionFinal : posicionesFinales) {
                    Optional<Pieza> piezaOptDestino = tablero.getPieza(posicionFinal[0], posicionFinal[1]);
                    if (piezaOptDestino.isPresent() && piezaOptDestino.get().getColor() == color
                            && piezaOptDestino.get().getTipo() == TipoPieza.REY) {
                        //piezaOptDestino
                        return true; // El rey del color está en jaque
                    }
                }
            }
        }
        return false; // No hay jaque
    }

    public static List<int[]> obtenerPosicionesValidas(Tablero tablero, Pieza pieza, int filaOriginal, int columnaOriginal) {
        List<int[]> posicionesValidas = new ArrayList<>();

        // Lógica para obtener las posiciones válidas, dependiendo de la pieza seleccionada
        // Supongamos que cada Pieza tiene un método validarMovimiento(filaDestino, columnaDestino)
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                if (pieza.validarMovimiento(tablero,filaOriginal, columnaOriginal, fila, columna)) {
                    posicionesValidas.add(new int[]{fila, columna});  // Añade la posición válida
                }
            }
        }

        return posicionesValidas;
    }


}
