package org.ajedrez.model;

/**
 * Clase que representa un peón en una partida de ajedrez.
 */
public class Peon extends Pieza {

    /**
     * Constructor de la clase Peon.
     * Inicializa el peón con su color, fila y columna.
     *
     * @param color Color del peón (blanco o negro).
     * @param fila Fila inicial del peón.
     * @param columna Columna inicial del peón.
     */
    public Peon(Color color, int fila, int columna) {
        super(color, fila, columna); // Llama al constructor de la clase base (Pieza)
    }

    /**
     * Valida el movimiento del peón en el tablero.
     * Un peón se mueve hacia adelante y puede capturar en diagonal.
     *
     * @param tablero Tablero en el que se realiza el movimiento.
     * @param filaOriginal Fila de la posición original del peón.
     * @param columnaOriginal Columna de la posición original del peón.
     * @param filaDestino Fila de la posición de destino del peón.
     * @param columnaDestino Columna de la posición de destino del peón.
     * @return true si el movimiento es válido, false en caso contrario.
     */
    @Override
    public boolean validarMovimiento(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
        // Verificar efecto de congelación
        if (getEfecto() != null && getEfecto().getTipo() == "freeze") {
            return false; // El peón no puede moverse si está congelado
        }

        // Obtener la dirección de avance dependiendo del color
        int direccion = (this.getColor() == Color.BLANCO) ? -1 : 1; // -1 para blanco, 1 para negro

        // Verificar movimiento hacia adelante
        if (columnaOriginal == columnaDestino) {
            // Mover una casilla hacia adelante
            if (filaDestino == filaOriginal + direccion && !tablero.estaOcupada(filaDestino, columnaDestino)) {
                return true; // Movimiento válido
            }

            // Mover dos casillas hacia adelante desde la posición inicial
            if ((filaOriginal == 6 && this.getColor() == Color.BLANCO) || (filaOriginal == 1 && this.getColor() == Color.NEGRO)) {
                if (filaDestino == filaOriginal + 2 * direccion &&
                        !tablero.estaOcupada(filaOriginal + direccion, columnaOriginal) && // No hay piezas en medio
                        !tablero.estaOcupada(filaDestino, columnaDestino)) {
                    return true; // Movimiento válido
                }
                // Verificar efecto de volar
                if (filaDestino == filaOriginal + 2 * direccion && getEfecto() != null && getEfecto().getTipo() == "volar") {
                    return true; // Movimiento válido
                }
            }
        }

        // Verificar captura en diagonal
        if (Math.abs(columnaDestino - columnaOriginal) == 1 && filaDestino == filaOriginal + direccion) {
            if (tablero.estaOcupada(filaDestino, columnaDestino)) {
                Pieza piezaDestino = tablero.getPieza(filaDestino, columnaDestino).get();
                // Solo puede capturar si la pieza es del color contrario
                if (piezaDestino.getColor() != this.getColor()) {
                    return true; // Movimiento válido
                }
            }
        }

        // Movimiento no válido
        return false;
    }

    /**
     * Verifica si el peón ha llegado a la fila final del tablero.
     *
     * @param fila Fila a verificar.
     * @return true si el peón ha llegado a la fila final, false en caso contrario.
     */
    public boolean haLlegadoAFilaFinal(int fila) {
        return (this.getColor() == Color.BLANCO && fila == 0) || (this.getColor() == Color.NEGRO && fila == 7);
    }

    /**
     * Obtiene el tipo de pieza.
     *
     * @return El tipo de pieza, en este caso "peon".
     */
    @Override
    public String getTipo() {
        return "peon"; // Tipo de pieza
    }
}
