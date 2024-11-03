
package org.ajedrez.view;

import org.ajedrez.model.Pieza;

public class TorreView extends PiezaView {

    public TorreView(Pieza pieza) {
        super(pieza);
    }

    @Override
    protected String construirRutaImagen(Pieza pieza) {
        return "/org/ajedrez/imagenes/Torre_" + pieza.getColor().toString().toLowerCase() + ".png";
    }
}
