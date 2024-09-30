package org.ajedrez.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PiezaView {

    // Crea una casilla de tablero (cuadrado)
    public static Rectangle crearCasilla(int fila, int col) {
        Rectangle cuadrado = new Rectangle(90, 90);
        cuadrado.setFill((fila + col) % 2 == 0 ? Color.WHITESMOKE : Color.SADDLEBROWN);
        return cuadrado;
    }

    // Crea la vista de una pieza
    public static ImageView crearPiezaView(String imagenRuta) {
        Image imagen = new Image(PiezaView.class.getResourceAsStream("/org/ajedrez/imagenes/" + imagenRuta));
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(90);
        imageView.setFitHeight(90);
        return imageView;
    }
}
