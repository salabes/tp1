package org.ajedrez.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.ajedrez.model.Pieza;
import org.ajedrez.model.Tablero;
import org.ajedrez.view.PiezaView;

public class TableroController {
    @FXML
    private GridPane gridPane;

    private Tablero tablero;
    private ImageView piezaSeleccionada;
    private double offsetX;
    private double offsetY;

    @FXML
    public void initialize() {
        tablero = new Tablero();
        inicializarTablero();
        inicializarPiezas();
    }

    private void inicializarTablero() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                gridPane.add(PiezaView.crearCasilla(row, col), col, row);
            }
        }
    }

    private void inicializarPiezas() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                final int finalRow = row; // Crear variable final
                final int finalCol = col; // Crear variable final
                tablero.getPieza(row, col).ifPresent(pieza -> agregarPieza(pieza, finalRow, finalCol));
            }
        }
    }

    private void agregarPieza(Pieza pieza, int fila, int columna) {
        ImageView piezaView = PiezaView.crearPiezaView(pieza.getImagen());
        piezaView.setOnMousePressed(event -> onMousePressed(event, piezaView));
        piezaView.setOnMouseDragged(event -> onMouseDragged(event, piezaView));
        piezaView.setOnMouseReleased(event -> onMouseReleased(event, piezaView, fila, columna));
        gridPane.add(piezaView, columna, fila);
    }

    private void onMousePressed(MouseEvent event, ImageView imageView) {
        piezaSeleccionada = imageView;
        offsetX = event.getSceneX() - imageView.getTranslateX();
        offsetY = event.getSceneY() - imageView.getTranslateY();
        imageView.setMouseTransparent(true);
        imageView.toFront();
    }

    private void onMouseDragged(MouseEvent event, ImageView imageView) {
        if (piezaSeleccionada != null) {
            imageView.setTranslateX(event.getSceneX() - offsetX);
            imageView.setTranslateY(event.getSceneY() - offsetY);
        }
    }

    private void onMouseReleased(MouseEvent event, ImageView imageView, int filaOriginal, int columnaOriginal) {
        imageView.setMouseTransparent(false);

        // Calcula la nueva fila y columna según las coordenadas del mouse
        int columna = (int) Math.round((imageView.getLayoutX() + imageView.getTranslateX()) / 90);
        int fila = (int) Math.round((imageView.getLayoutY() + imageView.getTranslateY()) / 90);

        // Validar que la posición esté dentro de los límites del tablero
        if (columna < 0 || columna >= 8 || fila < 0 || fila >= 8) {
            // Retornar a la posición original si la posición es inválida
            System.out.println("Posición inválida. Revertiendo movimiento.");
            imageView.setTranslateX(0);  // Restaura la posición original
            imageView.setTranslateY(0);  // Restaura la posición original
            GridPane.setColumnIndex(imageView, columnaOriginal);
            GridPane.setRowIndex(imageView, filaOriginal);
        } else {
            // Si la posición es válida, realiza el movimiento en el tablero lógico
            tablero.moverPieza(filaOriginal, columnaOriginal, fila, columna);
            GridPane.setColumnIndex(imageView, columna);  // Actualiza la columna en el GridPane
            GridPane.setRowIndex(imageView, fila);        // Actualiza la fila en el GridPane

            imageView.setTranslateX(0);  // Restaura las translaciones a 0 para mantener el orden correcto en el GridPane
            imageView.setTranslateY(0);
        }

        piezaSeleccionada = null;
    }

}
