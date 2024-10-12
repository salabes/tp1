package org.ajedrez.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PiezaView {

    private final ImageView vistaPieza;  // Imagen de la pieza

    public PiezaView(String rutaImagen) {
        Image imagen = new Image(PiezaView.class.getResourceAsStream(rutaImagen));
        this.vistaPieza = new ImageView(imagen);
        vistaPieza.setFitWidth(90);  // Ajustar el tamaño de la pieza
        vistaPieza.setFitHeight(90);
    }

    // Método para obtener la vista de la pieza (ImageView)
    public ImageView getVistaPieza() {
        return vistaPieza;
    }

    // Método para resaltar la pieza
    public void resaltar() {
        vistaPieza.setStyle("-fx-border-color: yellow; -fx-border-width: 3;");
    }

    // Método para quitar el resaltado
    public void quitarResaltado() {
        vistaPieza.setStyle(null);
    }
}