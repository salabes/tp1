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
        piezas[0] = new Pieza[]{
                new Torre("torre_negra.png", Color.NEGRO), new Caballo("caballo_negro.png", Color.NEGRO),
                new Alfil("alfil_negro.png", Color.NEGRO), new Reina("reina_negra.png", Color.NEGRO),
                new Rey("rey_negro.png", Color.NEGRO), new Alfil("alfil_negro.png", Color.NEGRO),
                new Caballo("caballo_negro.png", Color.NEGRO), new Torre("torre_negra.png", Color.NEGRO)
        };
        piezas[1] = new Pieza[8]; // Peones negros
        for (int i = 0; i < 8; i++) {
            piezas[1][i] = new Peon("peon_negro.png", Color.NEGRO);
        }

        piezas[6] = new Pieza[8]; // Peones blancos
        for (int i = 0; i < 8; i++) {
            piezas[6][i] = new Peon("peon_blanco.png", Color.BLANCO);
        }

        piezas[7] = new Pieza[]{
                new Torre("torre_blanca.png", Color.BLANCO), new Caballo("caballo_blanco.png", Color.BLANCO),
                new Alfil("alfil_blanco.png", Color.BLANCO), new Reina("reina_blanca.png", Color.BLANCO),
                new Rey("rey_blanco.png", Color.BLANCO), new Alfil("alfil_blanco.png", Color.BLANCO),
                new Caballo("caballo_blanco.png", Color.BLANCO), new Torre("torre_blanca.png", Color.BLANCO)
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

    public boolean estaOcupada(int filaDestino, int columnaDestino) {
        return getPieza(filaDestino,columnaDestino).isPresent();
    }
}
