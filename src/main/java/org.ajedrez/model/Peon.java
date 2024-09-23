import java.io.ObjectInputFilter.Config;
package org.ajedrez.model;

public class Peon extends Pieza{
    public Peon(Integer color) {
        super(color);
    }
    @Override
    public Boolean validarMovimiento(Tablero tablero, Integer oi, Integer oj, Integer di, Integer dj) {
        if (di >= tablero.getDimensiones() || dj >= tablero.getDimensiones()) {
            return false;
        }

        Integer idiff = (oi - di);
        Integer jdiff = (oj - dj);


        if  ((idiff ==1  && jdiff == 0) || ( oi==1 && idiff==2) && !tablero.estaOcupada(di, dj)){
            return true;
        }

        if  ((idiff==1 && jdiff==1) && (idiff>=0) && (tablero.estaOcupada(di, dj)) ){
            Pieza piezaDestino = tablero.obtenerPieza(di, dj);
            return (piezaDestino.getColor() != this.getColor() );
        } // completa
        return false;
    };

    @Override
    public Integer getColor() {
        return this.color;
    }

}
