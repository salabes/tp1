package org.ajedrez.view;

import org.ajedrez.model.Pieza;

public class ReyView extends PiezaView {

    public ReyView(Pieza pieza) {
        super(pieza);
    }

    @Override
    protected String construirRutaImagen(Pieza pieza) {
        return "/org/ajedrez/imagenes/Rey_" + pieza.getColor().toString().toLowerCase() + ".png";
    }
}
