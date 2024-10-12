package org.ajedrez.controller;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.ajedrez.model.Color;

/**
 * La clase PiezasEliminadasController es responsable de gestionar las piezas eliminadas
 * en el tablero de ajedrez. Estas piezas se añaden a las vistas correspondientes
 * (para las piezas blancas y negras) en un GridPane.
 */
public class PiezasEliminadasController {

    // GridPane que almacena las piezas blancas eliminadas
    private GridPane gridpaneBlancas;

    // GridPane que almacena las piezas negras eliminadas
    private GridPane gridpaneNegras;

    // Variables que controlan la posición de la siguiente pieza blanca eliminada en el GridPane
    int filaBlancas = 0;
    int columnaBlancas = 0;

    // Variables que controlan la posición de la siguiente pieza negra eliminada en el GridPane
    int filaNegras = 0;
    int columnaNegras = 0;

    /**
     * Constructor que inicializa los GridPane donde se colocarán las piezas eliminadas.
     * @param gridpaneBlancas El GridPane para las piezas blancas eliminadas.
     * @param gridPaneNegras El GridPane para las piezas negras eliminadas.
     */
    public PiezasEliminadasController(GridPane gridpaneBlancas, GridPane gridPaneNegras){
        this.gridpaneBlancas = gridpaneBlancas;
        this.gridpaneNegras = gridPaneNegras;
    }

    /**
     * Agrega la imagen de una pieza eliminada al GridPane correspondiente
     * según el color de la pieza (blanca o negra).
     * @param imagen La vista de la imagen de la pieza eliminada.
     * @param color El color de la pieza eliminada (blanca o negra).
     */
    public void agregarPiezaEliminadaAGridPane(ImageView imagen, Color color) {
        // Ajustar el tamaño de la imagen de la pieza eliminada
        imagen.setFitWidth(50);
        imagen.setFitHeight(50);

        // Si la pieza es blanca, agregarla al GridPane de las piezas blancas eliminadas
        if(color == color.BLANCO){
            gridpaneBlancas.add(imagen, this.columnaBlancas, this.filaBlancas);
            columnaBlancas++;

            // Mover a la siguiente fila si la columna actual está llena
            if (columnaBlancas == 4) {
                filaBlancas++;
                columnaBlancas = 0;
            }
        } else {
            // Si la pieza es negra, agregarla al GridPane de las piezas negras eliminadas
            gridpaneNegras.add(imagen, this.columnaNegras, this.filaNegras);
            columnaNegras++;

            // Mover a la siguiente fila si la columna actual está llena
            if (columnaNegras == 4) {
                filaNegras++;
                columnaNegras = 0;
            }
        }
    }
}
