package org.ajedrez.model;

public class Rey extends Pieza{
    public Rey(Integer color){
        super(color);
    }
    public boolean validarMovimiento(Tablero tablero, Integer oi, Integer oj, Integer di, Integer dj){
        if (di >= tablero.getDimensiones() || dj >= tablero.getDimensiones()) {
            return false;
        }

        Integer idiff = Math.abs(oi - di);
        Integer jdiff = Math.abs(oj - dj);

        if((idiff == 1 && jdiff == 1) || (idiff == 1 && jdiff == 0) || (idiff == 0 && jdiff == 1)) && !tablero.estaOcupada(di, dj){
            return true
        }
        if (tablero.estaOcupada(di, dj)) {
            Pieza piezaDestino = tablero.obtenerPieza(di, dj);
            return piezaDestino.getColor() != this.getColor();
        }

        return false;
    }
    @Override
    public Integer getColor() {
        return this.color;
    }
}
