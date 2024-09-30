package org.ajedrez.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.ajedrez.model.AdministradorDeMovimientos;
import org.ajedrez.model.Pieza;
import org.ajedrez.model.Tablero;
import org.ajedrez.view.PiezaView;

import java.util.Optional;

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
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                gridPane.add(PiezaView.crearCasilla(fila, columna), columna, fila);
            }
        }
    }

    private void inicializarPiezas() {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                final int Filafinal = fila; // Crear variable final
                final int Colfinal = col; // Crear variable final
                tablero.getPieza(fila, col).ifPresent(pieza -> agregarPieza(pieza, Filafinal, Colfinal));
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
        int columnaDestino = (int) Math.round((imageView.getLayoutX() + imageView.getTranslateX()) / 90);
        int filaDestino = (int) Math.round((imageView.getLayoutY() + imageView.getTranslateY()) / 90);


        // Validar que la posición esté dentro de los límites del tablero
        if (AdministradorDeMovimientos.movimientoInValido(this.tablero,filaOriginal,columnaOriginal,filaDestino,columnaDestino)) {
            // Retornar a la posición original si la posición es inválida
            System.out.println("Posición inválida. Revertiendo movimiento.");
            imageView.setTranslateX(0);  // Restaura la posición original
            imageView.setTranslateY(0);  // Restaura la posición original
            GridPane.setColumnIndex(imageView, columnaOriginal);
            GridPane.setRowIndex(imageView, filaOriginal);
        } else {
            // Si la posición es válida, realiza el movimiento en el tablero lógico
            tablero.moverPieza(filaOriginal, columnaOriginal, filaDestino, columnaDestino);
            GridPane.setColumnIndex(imageView, columnaDestino);  // Actualiza la columna en el GridPane
            GridPane.setRowIndex(imageView, filaDestino);        // Actualiza la fila en el GridPane

            imageView.setTranslateX(0);  // Restaura las translaciones a 0 para mantener el orden correcto en el GridPane
            imageView.setTranslateY(0);
        }

        piezaSeleccionada = null;
    }

}
