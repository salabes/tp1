
import java.util.stream.IntStream;

public class Alfil extends Pieza{

    private Integer color;
    public Alfil(Integer color) {
        this.color = color;
    }

    @Override
    public Boolean validarMovimiento(Tablero tablero, Integer oi, Integer oj, Integer di, Integer dj) {
        if (di >= tablero.getDimensiones() || dj >= tablero.getDimensiones()) {
            return false;
        }

        Integer idiff = Math.abs(oi - di);
        Integer jdiff = Math.abs(oj - dj);
        Integer isign = (di - oi) / idiff;
        Integer jsign = (dj - oj) / jdiff;

        if (idiff != jdiff) { //cambiar a bool 
            return false;
        }

        boolean caminoLibre = IntStream.range(1, idiff)
                .allMatch(x -> !tablero.estaOcupada(oi + isign * x, oj + jsign * x));

        if (!caminoLibre) {
            return false;
        }

        if (tablero.estaOcupada(di, dj)) {
            Pieza piezaDestino = tablero.obtenerPieza(di, dj);
            return piezaDestino.getColor() != this.getColor();
        }

        return true;
    }

    @Override
    public Integer getColor() {
        return this.color;
    }
}

