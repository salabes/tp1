
package org.ajedrez.model;

public class Peon extends Pieza {

    public Peon(String imagen, Color color) {
        super(imagen, color);
    }

    @Override
    public boolean validarMovimiento(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        // Obtener la dirección de avance dependiendo del color
        int direccion = (this.getColor() == Color.BLANCO) ? -1 : 1;

        // Verificar movimiento hacia adelante
        if (columnaOriginal == columnaDestino) {
            // Mover una casilla hacia adelante
            if (filaDestino == filaOriginal + direccion && !tablero.estaOcupada(filaDestino, columnaDestino)) {
                return true;
            }

            // Mover dos casillas hacia adelante desde la posición inicial
            if ((filaOriginal == 6 && this.getColor() == Color.BLANCO) || (filaOriginal == 1 && this.getColor() == Color.NEGRO)) {
                if (filaDestino == filaOriginal + 2 * direccion &&
                        !tablero.estaOcupada(filaOriginal + direccion, columnaOriginal) && // No hay piezas entre medio
                        !tablero.estaOcupada(filaDestino, columnaDestino)) {
                    return true;
                }
            }
        }

        // Verificar captura en diagonal
        if (Math.abs(columnaDestino - columnaOriginal) == 1 && filaDestino == filaOriginal + direccion) {
            if (tablero.estaOcupada(filaDestino, columnaDestino)) {
                Pieza piezaDestino = tablero.getPieza(filaDestino, columnaDestino).get();
                // Solo puede capturar si la pieza es del color contrario
                if (piezaDestino.getColor() != this.getColor()) {
                    return true;
                }
            }
        }

        // Movimiento no válido
        return false;
    }
}