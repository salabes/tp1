package org.ajedrez.model;

public class Torre extends Pieza {
    public Torre (String imagen,Color color){
        super(imagen,color);
    }
    public boolean validarMovimiento(Tablero tablero,int oi, int oj, int di, int dj){

        Integer idiff = (oi - di);
        Integer jdiff = (oj - dj);

        //falar el caso q AMBOS SEAN 0 Y NO SE MUEVA , EN TODAS LAS PIEZAS
        if  (((idiff==0) || ( jdiff==0))&& (!tablero.estaOcupada(di, dj))){
            return true;
        }
        if  ((idiff==0) || ( jdiff==0)&& (tablero.estaOcupada(di, dj)) ){
            Pieza piezaDestino = tablero.getPieza(di,dj).get();
            return !piezaDestino.getColor().equals(this.getColor());
        }

        return false;
    };
}
