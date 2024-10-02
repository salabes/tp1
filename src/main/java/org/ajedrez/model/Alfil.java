package org.ajedrez.model;

import java.util.stream.IntStream;

public class Alfil extends Pieza{

    public Alfil(String imagen, Color color) {
        super(imagen,color);
    }

    @Override
    public boolean validarMovimiento(Tablero tablero, int oi, int oj, int di, int dj) {

        int idiff = Math.abs(oi - di);
        int jdiff = Math.abs(oj - dj);

        if (idiff == jdiff ) {
            int isign = Integer.signum(di - oi); 
            int jsign = Integer.signum(dj - oj); 

            boolean caminoLibre = IntStream.range(1, Math.max(idiff, jdiff))
                    .allMatch(x -> !tablero.estaOcupada(oi + isign * x, oj + jsign * x));

            if (!caminoLibre) {
                return false;
            }

            if (tablero.estaOcupada(di, dj)) {
                Pieza piezaDestino = tablero.getPieza(di, dj).get();
                return !piezaDestino.getColor().equals(this.getColor());//aca tendria q agg la logica de q si es de color dif se lo coma y lo borre
            }

            return true;
        }

        return false;
    }
}