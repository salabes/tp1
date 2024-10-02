package org.ajedrez.model;

public class Rey extends Pieza{
    public Rey(String imagen, Color color) {
        super(imagen,color);
    }
    public boolean validarMovimiento(Tablero tablero,int oi, int oj, int di, int dj){

        Integer idiff = Math.abs(oi - di);
        Integer jdiff = Math.abs(oj - dj);

        if((idiff == 1 && jdiff == 1) || (idiff == 1 && jdiff == 0) || (idiff == 0 && jdiff == 1) && tablero.estaOcupada(di,dj))
            return true;

        if (tablero.estaOcupada(di, dj)) {
            Pieza piezaDestino = tablero.getPieza(di,dj).get();
            return !piezaDestino.getColor().equals(this.getColor());
        }

        return false;
    }
}