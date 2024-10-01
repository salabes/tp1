package org.ajedrez.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * La clase PiezaView es responsable de crear visualizaciones de las piezas de ajedrez y las casillas del tablero.
 */
public class PiezaView {

    /**
     * Crea una casilla del tablero de ajedrez.
     *
     * @param fila La fila en la que se ubicará la casilla.
     * @param columna La columna en la que se ubicará la casilla.
     * @return Un objeto Rectangle que representa la casilla,
     *         con un color que alterna entre blanco y marrón según su posición.
     */
    public static Rectangle crearCasilla(int fila, int columna) {
        Rectangle casilla = new Rectangle(90, 90);
        // Alterna el color de la casilla entre blanco y marrón
        casilla.setFill((fila + columna) % 2 == 0 ? Color.WHITESMOKE : Color.SADDLEBROWN);
        return casilla;
    }

    /**
     * Crea una vista para una pieza de ajedrez a partir de una imagen.
     *
     * @param rutaImagen La ruta de la imagen de la pieza dentro del paquete de recursos.
     * @return Un objeto ImageView que representa la pieza de ajedrez.
     */
    public static ImageView crearPiezaView(String rutaImagen) {
        // Carga la imagen de la pieza desde el paquete de recursos
        Image imagen = new Image(PiezaView.class.getResourceAsStream("/org/ajedrez/imagenes/" + rutaImagen));
        ImageView vistaImagen = new ImageView(imagen);
        // Ajusta el tamaño de la imagen a 90x90 píxeles
        vistaImagen.setFitWidth(90);
        vistaImagen.setFitHeight(90);
        return vistaImagen;
    }
}
