package org.ajedrez.model;

public class Caballo extends Pieza {

    public Caballo(String imagen, Color color) {
        super(imagen, color);
    }

    @Override
    public boolean validarMovimiento(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino) {
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
}
