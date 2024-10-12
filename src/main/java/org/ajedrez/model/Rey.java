package org.ajedrez.model;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Clase que representa la pieza Rey en una partida de ajedrez.
 */
public class Rey extends Pieza {
    private boolean haMovido; // Estado para verificar si el rey se ha movido

    /**
     * Constructor de la clase Rey.
     *
     * @param color Color de la pieza (blanco o negro).
     * @param fila Fila inicial de la pieza.
     * @param columna Columna inicial de la pieza.
     */
    public Rey(Color color, int fila, int columna) {
        super(color, fila, columna);
        this.haMovido = false; // Inicialmente, el rey no se ha movido
    }

    /**
     * Valida si el movimiento del Rey es válido.
     *
     * @param tablero Tablero en el que se realiza el movimiento.
     * @param oi Fila de la posición original del Rey.
     * @param oj Columna de la posición original del Rey.
     * @param di Fila de la posición de destino del Rey.
     * @param dj Columna de la posición de destino del Rey.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    @Override
    public boolean validarMovimiento(Tablero tablero, int oi, int oj, int di, int dj) {
        // Verificar si hay un efecto de congelamiento activo
        if (getEfecto() != null && getEfecto().getTipo() == "freeze") {
            return false; // No se puede mover si está congelado
        }

        // Calcular las diferencias en filas y columnas
        int idiff = Math.abs(oi - di);
        int jdiff = Math.abs(oj - dj);

        // Movimiento normal del rey o enroque
        if (((idiff <= 1 && jdiff <= 1) && !(idiff == 0 && jdiff == 0)) || (oj == 4 && (idiff == 0) && (oi == 0 || oi == 7) && (dj == 2 || dj == 6))) {
            // Validación de enroque
            if (oj == 4 && (oi == 0 || oi == 7) && (dj == 2 || dj == 6)) {

                Optional<Pieza> piezaOpt = tablero.getPieza(oi, (dj == 2) ? 0 : 7);

                if (piezaOpt.isPresent() && piezaOpt.get() instanceof Torre) {
                    Torre torre = (Torre) piezaOpt.get();
                    if (torre.haMovido() || haMovido()) {
                        return false; // La torre ya se ha movido o el rey no puede moverse
                    }
                } else {
                    return false; // No hay torre para enroque
                }

                // Verificar que el camino está libre
                int iniciocol = Math.min(oj, dj) + 1;
                int fincol = Math.max(oj, dj);
                boolean caminoLibre = IntStream.range(iniciocol, fincol)
                        .allMatch(col -> !tablero.estaOcupada(oi, col));

                if (!caminoLibre) {
                    return false; // Hay piezas en el camino
                }

                // Si el rey tiene un efecto de vuelo, el camino se considera libre
                if (getEfecto() != null && getEfecto().getTipo() == "volar") {
                    caminoLibre = true;
                }
            }

            // Si el destino está ocupado, verificar el color
            if (tablero.estaOcupada(di, dj)) {
                Pieza piezaDestino = tablero.getPieza(di, dj).get();
                return !piezaDestino.getColor().equals(this.getColor()); // Puede capturar si es de color diferente
            }

            return true; // Movimiento válido
        }

        return false; // Movimiento inválido
    }

    /**
     * Marca el rey como movido.
     */
    public void marcarComoMovido() {
        this.haMovido = true; // Cambia el estado de haMovido a true
    }

    /**
     * Verifica si el rey se ha movido.
     *
     * @return true si el rey se ha movido, false en caso contrario.
     */
    public boolean haMovido() {
        return haMovido; // Devuelve el estado de haMovido
    }

    /**
     * Obtiene el tipo de la pieza.
     *
     * @return Tipo de la pieza (en este caso, "rey").
     */
    @Override
    public String getTipo() {
        return "rey"; // Tipo de pieza
    }
}
