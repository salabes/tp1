public class Torre extends Pieza {
    public Torre (Integer color){
        super(color);
    }
    public boolean validarMovimiento(Tablero tablero, Integer oi, Integer oj, Integer di, Integer dj){
        if (di >= tablero.getDimensiones() || dj >= tablero.getDimensiones()) {
            return false;
        }// AGREGAR QUE NO SEA MENOR A 0

        Integer idiff = (oi - di);
        Integer jdiff = (oj - dj);

        //falar el caso q AMBOS SEAN 0 Y NO SE MUEVA , EN TODAS LAS PIEZAS
        if (enrroque()==true){
            return true;
        }
        if  (((idiff==0) || ( jdiff==0))&& (!tablero.estaOcupada(di, dj))){
            return true;
        }
        if  ((idiff==0) || ( jdiff==0)&& (tablero.estaOcupada(di, dj)) ){
            Pieza piezaDestino = tablero.obtenerPieza(di, dj);
            return (piezaDestino.getColor() != this.getColor() && (tablero.estaOcupada(di, dj)));
        }

        return false;
    };
    @Override
    public Integer getColor() {
        return this.color;
    }
}
