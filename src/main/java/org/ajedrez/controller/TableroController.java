package org.ajedrez.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import org.ajedrez.model.Pieza;
import org.ajedrez.model.Tablero;
import org.ajedrez.view.PiezaView;
import org.ajedrez.model.AdministradorDeMovimientos;

/**
 * Clase controladora que gestiona las interacciones del tablero de ajedrez.
 * Se encarga de inicializar el tablero y las piezas, y maneja los eventos del mouse
 * para el movimiento de las piezas en la interfaz gráfica.
 */
public class TableroController {

    @FXML
    private GridPane panelCuadriculado;  // Panel que contiene el tablero de ajedrez

    private Tablero tablero;  // Representación lógica del tablero
    private ImageView imagenPiezaSeleccionada;  // Imagen de la pieza actualmente seleccionada
    private double desplazamientoX;  // Desplazamiento del mouse en el eje X al presionar la pieza
    private double desplazamientoY;  // Desplazamiento del mouse en el eje Y al presionar la pieza

    /**
     * Inicializa el controlador. Se llama automáticamente al cargar el FXML.
     * Crea una nueva instancia de Tablero y llama a los métodos para inicializar el tablero y las piezas.
     */
    @FXML
    public void initialize() {
        tablero = new Tablero();
        inicializarTablero();
        inicializarPiezas();
    }

    /**
     * Inicializa el tablero agregando casillas al panel cuadrado.
     */
    private void inicializarTablero() {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                panelCuadriculado.add(PiezaView.crearCasilla(fila, columna), columna, fila);
            }
        }
    }

    /**
     * Inicializa las piezas en el tablero. Recorre el tablero y agrega las piezas
     * correspondientes utilizando el método agregarPieza.
     */
    private void inicializarPiezas() {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                final int filaFinal = fila;
                final int columnaFinal = columna;
                tablero.getPieza(fila, columna).ifPresent(pieza -> agregarPieza(pieza, filaFinal, columnaFinal));
            }
        }
    }

    /**
     * Agrega una pieza al tablero en la posición especificada.
     * @param pieza La pieza a agregar.
     * @param fila La fila en la que se ubicará la pieza.
     * @param columna La columna en la que se ubicará la pieza.
     */
    private void agregarPieza(Pieza pieza, int fila, int columna) {

        ImageView imagenPieza = PiezaView.crearPiezaView(pieza.getImagen());
        panelCuadriculado.add(imagenPieza, columna, fila);
        // Configura los eventos del mouse para la imagen de la pieza
        imagenPieza.setOnMousePressed(evento -> alPresionarConMouse(evento, imagenPieza));
        imagenPieza.setOnMouseDragged(evento -> alArrastrarConMouse(evento, imagenPieza));
        imagenPieza.setOnMouseReleased(evento -> alSoltarMouse(imagenPieza, fila, columna));
    }

    /**
     * Maneja el evento de presionar el mouse sobre una pieza.
     * @param eventoMouse El evento de mouse que contiene información sobre la acción.
     * @param vistaImagen La imagen de la pieza que fue presionada.
     */
    private void alPresionarConMouse(MouseEvent eventoMouse, ImageView vistaImagen) {
        imagenPiezaSeleccionada = vistaImagen;
        desplazamientoX = eventoMouse.getSceneX() - vistaImagen.getTranslateX();
        desplazamientoY = eventoMouse.getSceneY() - vistaImagen.getTranslateY();
        vistaImagen.setMouseTransparent(true);  // Evita que se reciban eventos de mouse mientras se arrastra
        vistaImagen.toFront();  // Lleva la pieza al frente del panel
    }

    /**
     * Maneja el evento de arrastrar una pieza con el mouse.
     * @param eventoMouse El evento de mouse que contiene información sobre la acción.
     * @param vistaImagen La imagen de la pieza que se está arrastrando.
     */
    private void alArrastrarConMouse(MouseEvent eventoMouse, ImageView vistaImagen) {
        if (imagenPiezaSeleccionada != null) {
            vistaImagen.setTranslateX(eventoMouse.getSceneX() - desplazamientoX);
            vistaImagen.setTranslateY(eventoMouse.getSceneY() - desplazamientoY);
        }
    }

    /**
     * Maneja el evento de soltar una pieza con el mouse.
     * @param vistaImagen La imagen de la pieza que se está soltando.
     * @param filaOriginal La fila original de la pieza.
     * @param columnaOriginal La columna original de la pieza.
     */
    private void alSoltarMouse(ImageView vistaImagen, int filaOriginal, int columnaOriginal) {
        vistaImagen.setMouseTransparent(false);
        System.out.println(filaOriginal);

        // Calcula la nueva fila y columna según las coordenadas del mouse
        int nuevaColumna = (int) Math.round((vistaImagen.getLayoutX() + vistaImagen.getTranslateX()) / 90);
        int nuevaFila = (int) Math.round((vistaImagen.getLayoutY() + vistaImagen.getTranslateY()) / 90);
        System.out.println(nuevaFila);
        // Validar que la posición esté dentro de los límites del tablero
        if (AdministradorDeMovimientos.movimientoInValido(tablero, filaOriginal, columnaOriginal, nuevaFila, nuevaColumna)) {
            // Retornar a la posición original si la posición es inválida
            System.out.println("Posición inválida. Revertiendo movimiento.");

            // También es útil reiniciar las traducciones a 0
            vistaImagen.setTranslateX(0);
            vistaImagen.setTranslateY(0);
        } else {
            // Si la posición es válida, realiza el movimiento en el tablero lógico
            tablero.moverPieza(filaOriginal, columnaOriginal, nuevaFila, nuevaColumna);

            // Actualiza la columna y fila en el GridPane
            GridPane.setColumnIndex(vistaImagen, nuevaColumna);
            GridPane.setRowIndex(vistaImagen, nuevaFila);
            vistaImagen.setOnMouseReleased(evento -> alSoltarMouse(vistaImagen, nuevaFila, nuevaColumna));

            // Reinicia las traducciones para mantener el orden correcto en el GridPane
            vistaImagen.setTranslateX(0);
            vistaImagen.setTranslateY(0);
        }

        imagenPiezaSeleccionada = null;  // Resetea la imagen seleccionada
    }

}
