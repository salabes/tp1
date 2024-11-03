package org.ajedrez.view;

import org.ajedrez.model.Pieza;

public class CaballoView extends PiezaView {

    public CaballoView(Pieza pieza) {
        super(pieza);
    }

    @Override
    protected String construirRutaImagen(Pieza pieza) {
        return "/org/ajedrez/imagenes/Caballo_" + pieza.getColor().toString().toLowerCase() + ".png";
    }
}

