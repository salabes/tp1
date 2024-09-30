package org.ajedrez.model;

public class Peon extends Pieza{
    public Peon(String imagen, Color color) {
        super(imagen,color);
    }
    @Override
    public boolean validarMovimiento(Tablero tablero,int oi, int oj, int di, int dj) {

        Integer idiff = (oi - di);
        Integer jdiff = (oj - dj);


        if  ((idiff ==1  && jdiff == 0) || ( oi==1 && idiff==2) && !tablero.estaOcupada(di, dj)){
            return true;
        }

        if  ((idiff==1 && jdiff==1) && (idiff>=0) && (tablero.estaOcupada(di, dj)) ){
            Pieza piezaDestino = tablero.getPieza(di,dj).get();
            return !piezaDestino.getColor().equals(this.getColor());
        } // completa
        return false;
    };

}
