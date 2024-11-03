package org.ajedrez.view;

import org.ajedrez.model.Pieza;

public class ReinaView extends PiezaView {

    public ReinaView(Pieza pieza) {
        super(pieza);
    }

    @Override
    protected String construirRutaImagen(Pieza pieza) {
        return "/org/ajedrez/imagenes/Reina_" + pieza.getColor().toString().toLowerCase() + ".png";
    }
}
