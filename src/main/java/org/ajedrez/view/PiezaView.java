package org.ajedrez.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.ajedrez.model.Pieza;

public abstract class PiezaView {

    protected final ImageView vistaPieza;

    public PiezaView(Pieza pieza) {
        String rutaImagen = construirRutaImagen(pieza);
        Image imagen = new Image(PiezaView.class.getResourceAsStream(rutaImagen));
        this.vistaPieza = new ImageView(imagen);
        vistaPieza.setFitWidth(90);
        vistaPieza.setFitHeight(90);
    }

    protected abstract String construirRutaImagen(Pieza pieza);

    public ImageView getVistaPieza() {
        return vistaPieza;
    }

    public void resaltar() {
        vistaPieza.setStyle("-fx-border-color: yellow; -fx-border-width: 3;");
    }

    public void quitarResaltado() {
        vistaPieza.setStyle(null);
    }
}
