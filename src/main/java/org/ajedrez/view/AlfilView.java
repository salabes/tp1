package org.ajedrez.view;

import org.ajedrez.model.Pieza;

public class AlfilView extends PiezaView {

    public AlfilView(Pieza pieza) {
        super(pieza);
    }

    @Override
    protected String construirRutaImagen(Pieza pieza) {
        return "/org/ajedrez/imagenes/Alfil_" + pieza.getColor().toString().toLowerCase() + ".png";
    }
}
