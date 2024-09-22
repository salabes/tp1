package org.ajedrez.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TableroController {
    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        // Crear el tablero 8x8 con colores alternos
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(50, 50);
                square.setFill((row + col) % 2 == 0 ? Color.WHITESMOKE : Color.SADDLEBROWN);
                gridPane.add(square, col, row); // Añadir a la GridPane
            }
        }

        // Colocar las piezas en sus posiciones iniciales
        inicializarPiezas();
    }

    private void inicializarPiezas() {
        // Agregar piezas negras al tablero
        agregarPieza("torre_negra.png", 0, 0);
        agregarPieza("caballo_negro.png", 0, 1);
        agregarPieza("alfil_negro.png", 0, 2);
        agregarPieza("reina_negra.png", 0, 3);
        agregarPieza("rey_negro.png", 0, 4);
        agregarPieza("alfil_negro.png", 0, 5);
        agregarPieza("caballo_negro.png", 0, 6);
        agregarPieza("torre_negra.png", 0, 7);
        for (int col = 0; col < 8; col++) {
            agregarPieza("peon_negro.png", 1, col);
        }

        // Agregar piezas blancas al tablero
        agregarPieza("torre_blanca.png", 7, 0);
        agregarPieza("caballo_blanco.png", 7, 1);
        agregarPieza("alfil_blanco.png", 7, 2);
        agregarPieza("reina_blanca.png", 7, 3);
        agregarPieza("rey_blanco.png", 7, 4);
        agregarPieza("alfil_blanco.png", 7, 5);
        agregarPieza("caballo_blanco.png", 7, 6);
        agregarPieza("torre_blanca.png", 7, 7);

        //Agregar peones al tablero
        for (int col = 0; col < 8; col++) {
            agregarPieza("peon_blanco.png", 6, col);
        }
    }

    private void agregarPieza(String nombreImagen, int fila, int columna) {
        // Ruta de la imagen de la pieza
        Image imagen = new Image(getClass().getResourceAsStream("/org/ajedrez/imagenes/" + nombreImagen));
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // Colocar la imagen en el GridPane
        gridPane.add(imageView, columna, fila);
    }
}
