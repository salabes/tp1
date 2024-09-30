package org.ajedrez.model;

import java.util.stream.IntStream;

public class Alfil extends Pieza{

    public Alfil(String imagen, Color color) {
        super(imagen,color);
    }

    @Override
    public boolean validarMovimiento(Tablero tablero,int oi, int oj, int di, int dj) {

        Integer idiff = Math.abs(oi - di);
        Integer jdiff = Math.abs(oj - dj);
        Integer isign = (di - oi) / idiff;
        Integer jsign = (dj - oj) / jdiff;

        if (idiff != jdiff) { //cambiar a bool 
            return false;
        }

        boolean caminoLibre = IntStream.range(1, idiff)
                .allMatch(x -> !tablero.estaOcupada(oi + isign * x, oj + jsign * x));

        if (!caminoLibre) {
            return false;
        }

        if (tablero.estaOcupada(di, dj)) {
            Pieza piezaDestino = tablero.getPieza(di,dj).get();
            return !piezaDestino.getColor().equals(this.getColor());
        }

        return true;
    }
}

