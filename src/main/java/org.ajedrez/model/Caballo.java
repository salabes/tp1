
import java.io.ObjectInputFilter.Config;
import java.util.stream.IntStream;

public class Caballo {
    public Caballo(Integer color) {
        super(color);
    }
    @Override
    public Boolean validarMovimiento(Tablero tablero, Integer oi, Integer oj, Integer di, Integer dj) {
        if (di >= tablero.getDimensiones() || dj >= tablero.getDimensiones()) {
            return false;
        }

        Integer idiff = Math.abs(oi - di);
        Integer jdiff = Math.abs(oj - dj);

        if (tablero.estaOcupada(di, dj) && ((idiff == 2 && jdiff == 1) || (idiff == 1 && jdiff == 2))) {
            Pieza piezaDestino = tablero.obtenerPieza(di, dj);
            return piezaDestino.getColor() != this.getColor();
        }
        return ((idiff == 2 && jdiff == 1) || (idiff == 1 && jdiff == 2));
    };

    @Override
    public Integer getColor() {
        return this.color;
    }

}
