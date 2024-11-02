package org.ajedrez.model;

import java.util.stream.IntStream;

/**
 * Clase que representa una torre en el juego de ajedrez, heredando de la clase Pieza.
 */
public class Torre extends Pieza {
    private boolean haMovido; // Estado para verificar si la torre se ha movido

    /**
     * Constructor que inicializa la torre con su color y posición.
     *
     * @param color Color de la torre.
     * @param fila Fila inicial de la torre.
     * @param columna Columna inicial de la torre.
     */
    public Torre(Color color, int fila, int columna) {
        super(color, fila, columna);
        this.haMovido = false; // La torre no ha sido movida al inicializarse
    }

    /**
     * Valida si el movimiento de la torre es válido.
     *
     * @param tablero Tablero en el que se encuentra la torre.
     * @param oi Fila de origen de la torre.
     * @param oj Columna de origen de la torre.
     * @param di Fila de destino para el movimiento.
     * @param dj Columna de destino para el movimiento.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    public boolean validarMovimiento(Tablero tablero, int oi, int oj, int di, int dj) {
        //System.out.println("Entro a validar movimiento");
        // Verificar si la torre está congelada
        if (getEfecto() != null && "freeze".equals(getEfecto().getTipo())) {
            return false; // No puede moverse si está congelada
        }

        int idiff = Math.abs(oi - di); // Diferencia en filas
        int jdiff = Math.abs(oj - dj); // Diferencia en columnas

        // Verificar si el movimiento es en línea recta
        if ((idiff != 0 && jdiff != 0) || (idiff == 0 && jdiff == 0)) {
            return false; // Movimiento no válido
        }

        int isign = Integer.signum(di - oi); // Signo de la dirección en filas
        int jsign = Integer.signum(dj - oj); // Signo de la dirección en columnas

        if (tablero.estaOcupada(di, dj)) {
            Pieza piezaDestino = tablero.getPieza(di, dj).get();
            if (piezaDestino.getColor().equals(this.getColor())) {
                return false; // No se puede capturar una pieza del mismo color
            }
        }
        boolean caminosLibre = IntStream.range(1, Math.max(idiff, jdiff))
                .allMatch(x -> !tablero.estaOcupada(oi + isign * x, oj + jsign * x));
        if (!caminosLibre) {
            return false; // Camino obstruido
        }

        // Si no hay efecto especial
        if (this.getEfecto() == null) {
            //System.out.println("ERRORRRRR");
            // Verificar si el camino está libre
            boolean caminoLibre = IntStream.range(1, Math.max(idiff, jdiff))
                    .allMatch(x -> !tablero.estaOcupada(oi + isign * x, oj + jsign * x));
            if (!caminoLibre) {
                return false; // Camino obstruido
            }

            // Verificación del enroque
            if (idiff == 0 && jdiff == 2 && (oi ==1)) { // Movimiento enroque
                int columnaRey = 4;
                int columnaTorre = (dj - oj) > 0 ? 7 : 0; // Enroque corto o largo

                Pieza rey = tablero.getPieza(oi, columnaRey).orElse(null);
                Pieza torre = tablero.getPieza(oi, columnaTorre).orElse(null);

                // Validar que el rey y la torre sean válidos y que la torre no haya sido movida
                if (rey instanceof Rey && torre instanceof Torre && !((Torre) torre).haMovido()) {
                    //System.out.println("Entro torre");
                    return true; // Movimiento válido de enroque
                } else {
                    return false; // Movimiento de enroque no válido
                }
            }

            // Verificar si hay una pieza en la posición destino
            if (tablero.estaOcupada(di, dj)) {
                Pieza piezaDestino = tablero.getPieza(di, dj).get();
                if (piezaDestino.getColor().equals(this.getColor())) {
                    return false; // No se puede capturar una pieza del mismo color
                }
            }
        } else if (getEfecto() != null && "volar".equals(getEfecto().getTipo())) {
            // Si el efecto es "volar", se permite capturar
            if (tablero.estaOcupada(di, dj)) {
                Pieza piezaDestino = tablero.getPieza(di, dj).get();
                if (piezaDestino.getColor().equals(this.getColor())) {
                    return false; // No se puede capturar una pieza del mismo color
                }
            }
        }

        return true; // Movimiento válido
    }

    /**
     * Marca la torre como movida.
     */
    public void marcarComoMovido() {
        this.haMovido = true; // Cambia el estado de haMovido a true
    }

    /**
     * Verifica si la torre ha sido movida.
     *
     * @return true si la torre ha sido movida, false en caso contrario.
     */
    public boolean haMovido() {
        return haMovido; // Retorna el estado de haMovido
    }

    /**
     * Devuelve el tipo de pieza.
     *
     * @return Tipo de la pieza.
     */
    @Override
    public String getTipo() {
        return "torre"; // Tipo de pieza
    }
}
