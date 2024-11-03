package org.ajedrez.view;

import org.ajedrez.model.Pieza;

public class PeonView extends PiezaView {

    public PeonView(Pieza pieza) {
        super(pieza);
    }

    @Override
    protected String construirRutaImagen(Pieza pieza) {
        return "/org/ajedrez/imagenes/Peon_" + pieza.getColor().toString().toLowerCase() + ".png";
    }
}

