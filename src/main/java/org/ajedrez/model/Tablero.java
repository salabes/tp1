package org.ajedrez.model;

import java.util.Optional;

public class Tablero {
    private Pieza[][] piezas;

    public Tablero() {
        piezas = new Pieza[8][8];
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Inicializar piezas blancas y negras
        piezas[0] = new Pieza[] {
                new Pieza("torre_negra.png"), new Pieza("caballo_negro.png"), new Pieza("alfil_negro.png"),
                new Pieza("reina_negra.png"), new Pieza("rey_negro.png"), new Pieza("alfil_negro.png"),
                new Pieza("caballo_negro.png"), new Pieza("torre_negra.png")
        };
        piezas[1] = new Pieza[8]; // Peones negros
        for (int i = 0; i < 8; i++) {
            piezas[1][i] = new Pieza("peon_negro.png");
        }

        piezas[6] = new Pieza[8]; // Peones blancos
        for (int i = 0; i < 8; i++) {
            piezas[6][i] = new Pieza("peon_blanco.png");
        }

        piezas[7] = new Pieza[] {
                new Pieza("torre_blanca.png"), new Pieza("caballo_blanco.png"), new Pieza("alfil_blanco.png"),
                new Pieza("reina_blanca.png"), new Pieza("rey_blanco.png"), new Pieza("alfil_blanco.png"),
                new Pieza("caballo_blanco.png"), new Pieza("torre_blanca.png")
        };
    }

    public Optional<Pieza> getPieza(int fila, int columna) {
        if (fila >= 0 && fila < 8 && columna >= 0 && columna < 8) {
            return Optional.ofNullable(piezas[fila][columna]);
        }
        return Optional.empty();
    }

    public void moverPieza(int filaOriginal, int columnaOriginal, int filaNueva, int columnaNueva) {
        if (filaNueva >= 0 && filaNueva < 8 && columnaNueva >= 0 && columnaNueva < 8) {
            piezas[filaNueva][columnaNueva] = piezas[filaOriginal][columnaOriginal];
            piezas[filaOriginal][columnaOriginal] = null;
        }
    }
}
