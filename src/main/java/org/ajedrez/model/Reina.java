package org.ajedrez.model;

import java.util.stream.IntStream;

public class Reina extends Pieza {
    public Reina(String imagen, Color color){
        super(imagen,color);
    }
    public boolean validarMovimiento(Tablero tablero,int oi, int oj, int di, int dj){

        Integer idiff = Math.abs(oi - di);
        Integer jdiff = Math.abs(oj - dj);
        Integer isign = (di - oi) / idiff;
        Integer jsign = (dj - oj) / jdiff;

        boolean caminoLibre = IntStream.range(1, idiff)
                .allMatch(x -> !tablero.estaOcupada(oi + isign * x, oj + jsign * x));

        if (!caminoLibre) {
            return false;
        }
        if (((idiff != jdiff )|| ((idiff==0) || ( jdiff==0)))&& (!tablero.estaOcupada(di, dj))) { //cambiar a bool
            return true;
        }
        if (tablero.estaOcupada(di, dj)) {
            Pieza piezaDestino = tablero.getPieza(di,dj).get();
            return !piezaDestino.getColor().equals(this.getColor());
        }

        return false;

    }
}