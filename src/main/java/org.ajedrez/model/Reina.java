public class Reina extends Pieza {
    public Reina(Integer color){
        super(color);
    }
    public abstract validarMovimiento(Tablero tablero, Integer oi, Integer oj, Integer di, Integer dj){
        if (di >= tablero.getDimensiones() || dj >= tablero.getDimensiones()) {
            return false;
        }

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
