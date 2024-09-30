package org.ajedrez.model;

import java.util.Optional;

public class AdministradorDeMovimientos {
    public static boolean movimientoInValido(Tablero tablero, int filaOriginal, int columnaOriginal, int filaDestino, int columnaDestino){
        Pieza pieza = tablero.getPieza(filaOriginal,columnaOriginal).get();
        if(
                columnaDestino < 0 ||
                columnaDestino >= 8 ||
                filaDestino < 0 ||
                filaDestino >= 8 ||
                !pieza.validarMovimiento(tablero,filaOriginal,columnaOriginal,filaDestino,columnaDestino)
        ) {
            return true;
        }

        return false;
    }
}
