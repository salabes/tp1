package org.ajedrez.model;

import java.util.Optional;

public class Tablero {
    private Casillero[][] casilleros;

    public Tablero() {
        casilleros = new Casillero[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casilleros[i][j] = new Casillero();
            }
        }
        inicializarTablero();
    }

    private void inicializarTablero() {
        casilleros[0][0].setPieza(new Torre("torre_blanca.png", Color.BLANCO));
        casilleros[0][1].setPieza(new Caballo("caballo_blanca.png", Color.BLANCO));
        casilleros[0][2].setPieza(new Alfil("alfil_blanca.png", Color.BLANCO));
        casilleros[0][3].setPieza(new Reina("reina_blanca.png", Color.BLANCO));
        casilleros[0][4].setPieza(new Rey("rey_blanca.png", Color.BLANCO));
        casilleros[0][5].setPieza(new Alfil("alfil_blanca.png", Color.BLANCO));
        casilleros[0][6].setPieza(new Reina("reina_negra.png", Color.NEGRO));
        casilleros[0][7].setPieza(new Torre("torre_blanca.png", Color.BLANCO));
        for (int i = 0; i < 8; i++) {
            casilleros[1][i].setPieza(new Peon("peon_blanco.png", Color.BLANCO));
        }
        casilleros[6][0].setPieza(new Torre("torre_negra.png", Color.NEGRO));
        casilleros[6][1].setPieza(new Caballo("caballo_negra.png", Color.NEGRO));
        casilleros[6][2].setPieza(new Alfil("alfil_negra.png", Color.NEGRO));
        casilleros[6][3].setPieza(new Reina("reina_negra.png", Color.NEGRO));
        casilleros[6][4].setPieza(new Rey("rey_negra.png", Color.NEGRO));
        casilleros[6][5].setPieza(new Alfil("alfil_negra.png", Color.NEGRO));
        casilleros[6][6].setPieza(new Reina("reina_negra.png", Color.NEGRO));
        casilleros[6][7].setPieza(new Torre("torre_negra.png", Color.NEGRO));
        for (int i = 0; i < 8; i++) {
            casilleros[7][i].setPieza(new Peon("peon_negro.png", Color.NEGRO));
        }
    }

    public Optional<Pieza> getPieza(int fila, int columna) {
        return Optional.ofNullable(casilleros[fila][columna].getPieza());
    }

    public void moverPieza(int filaOriginal, int columnaOriginal, int filaNueva, int columnaNueva) {
        casilleros[filaNueva][columnaNueva].setPieza(casilleros[filaOriginal][columnaOriginal].getPieza());
        casilleros[filaOriginal][columnaOriginal].setPieza(null);
    }

    public boolean estaOcupada(int filaDestino, int columnaDestino) {
        return getPieza(filaDestino, columnaDestino).isPresent();
    }

}