package org.ajedrez.model;

import java.io.ObjectInputFilter.Config;
import java.util.stream.IntStream;

public class Caballo extends Pieza{
    public Caballo(String imagen, Color color) {
        super(imagen,color);
    }
    @Override
    public boolean validarMovimiento(Tablero tablero,int oi, int oj, int di, int dj) {

        Integer idiff = Math.abs(oi - di);
        Integer jdiff = Math.abs(oj - dj);

        if (tablero.estaOcupada(di, dj) && ((idiff == 2 && jdiff == 1) || (idiff == 1 && jdiff == 2))) {
            Pieza piezaDestino = tablero.getPieza(di,dj).get();
            return !piezaDestino.getColor().equals(this.getColor());
        }
        return ((idiff == 2 && jdiff == 1) || (idiff == 1 && jdiff == 2));
    };
}
