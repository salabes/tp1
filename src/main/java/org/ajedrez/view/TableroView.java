package org.ajedrez.view;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import org.ajedrez.controller.MovimientosEspecialesController;
import org.ajedrez.controller.TableroController;
import org.ajedrez.model.Pieza;
import org.ajedrez.model.Tablero;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;

public class TableroView {

    private final GridPane tableroGrid;
    private final StackPane[][] casillas;  // Contenedor bidimensional para las casillas
    private final int filas = 8;
    private final int columnas = 8;

    private Tablero tablero;

    private Map<Pieza, ImageView> mapaPiezas;

    public TableroView(GridPane tableroGrid) {
        this.tableroGrid = tableroGrid;
        this.casillas = new StackPane[filas][columnas];
        this.mapaPiezas = new HashMap<>();
        // Inicializar el contenedor
        crearTablero();
    }

    public GridPane getTableroGrid() {
        return tableroGrid;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public GridPane getGridPane() {
        return tableroGrid;
    }

    // Método para crear el tablero visualmente
    public void crearTablero() {
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                StackPane casilla = crearCasilla(fila, columna);
                casillas[fila][columna] = casilla;  // Guardar la casilla en el arreglo
                tableroGrid.add(casilla, columna, fila);  // Añadir la casilla al GridPane
            }
        }
    }

    // Crear una casilla con su color y posible interacción
    private StackPane crearCasilla(int fila, int columna) {
        StackPane casilla = new StackPane();
        Rectangle fondo = new Rectangle(90, 90);

        if ((fila + columna) % 2 == 0) {
            fondo.setFill(Color.WHITESMOKE);
        } else {
            fondo.setFill(Color.SADDLEBROWN);
        }
        fondo.setStroke(Color.BLACK);  // Bordes negros

        casilla.getChildren().add(fondo);
        return casilla;
    }

    public void limpiarCasilla(int fila, int columna) {
        casillas[fila][columna].getChildren().clear();
    }

    public ImageView obtenerVistaDePieza(Pieza pieza) {
        return mapaPiezas.get(pieza);  // Retorna la vista de la pieza
    }

    // Método para agregar una pieza visual al tablero y al mapa
    public void agregarPiezaView(PiezaView piezaView, Pieza pieza, int fila, int columna) {
        ImageView vistaPieza = piezaView.getVistaPieza();
        tableroGrid.add(vistaPieza, columna, fila);
        mapaPiezas.put(pieza, vistaPieza);  // Asociamos la pieza con su ImageView en el mapa
    }

    // Método para eliminar la vista de una pieza cuando es comida
    public void eliminarPiezaView(Pieza pieza) {
        ImageView vistaPieza = mapaPiezas.get(pieza);
        if (vistaPieza != null) {
            tableroGrid.getChildren().remove(vistaPieza);  // Elimina la vista de la interfaz
            mapaPiezas.remove(pieza);  // Elimina la referencia del mapa
        }
    }

    public PiezaView reemplazarPiezaView(Pieza pieza, Pieza nuevaPieza, int fila, int columna) {
        eliminarPiezaView(pieza);
        PiezaView piezaView = new PiezaView(nuevaPieza.getImagen());
        agregarPiezaView(piezaView, nuevaPieza, fila, columna);

        return piezaView;
    }

    // Metodo para cargar las piezas el tablero visual
    public void inicializarPiezas(TableroController controller) {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                final int filaFinal = fila;
                final int columnaFinal = columna;
                Boolean present = this.tablero.getPieza(filaFinal, columnaFinal).isPresent();
                if (present) {
                    Pieza pieza = this.tablero.getPieza(filaFinal, columnaFinal).get();
                    PiezaView piezaView = new PiezaView(pieza.getImagen());
                    agregarPiezaView(piezaView, pieza, filaFinal, columnaFinal);
                    controller.setearEventos(piezaView, filaFinal, columnaFinal);
                }
            }
        }
    }

    // Método para resaltar las casillas válidas
    // Método para resaltar las casillas válidas
    public void resaltarCasilla(int fila, int columna, boolean resaltar) {
        boolean present = this.tablero.getPieza(fila, columna).isPresent();
        StackPane casilla = casillas[fila][columna];

        // Verificamos si ya hay un borde resaltado
        Rectangle bordeResaltado = null;
        for (Node child : casilla.getChildren()) {
            if (child instanceof Rectangle && ((Rectangle) child).getStroke() == Color.BLUE) {
                bordeResaltado = (Rectangle) child;
                break;
            }
        }

        if (resaltar) {
            // Solo agrega un borde si no existe uno
            if (bordeResaltado == null) {
                Rectangle nuevoBorde = new Rectangle(85, 85);
                nuevoBorde.setFill(Color.TRANSPARENT);
                nuevoBorde.setStroke(Color.BLUE);
                if (present) {
                    Pieza pieza = this.tablero.getPieza(fila, columna).get();
                    if (pieza.getTipo() == "rey") {
                        nuevoBorde.setStroke(Color.RED);
                        // Usamos un temporizador para quitar el resaltado después de 2 segundos
                        PauseTransition pause = new PauseTransition(Duration.seconds(2));
                        pause.setOnFinished(event -> casilla.getChildren().remove(nuevoBorde));
                        pause.play(); // Iniciamos la pausa
                    }
                }
                nuevoBorde.setStrokeWidth(5);
                casilla.getChildren().add(nuevoBorde);
            }
        } else {
            // Remueve el borde solo si existe
            if (bordeResaltado != null) {
                casilla.getChildren().remove(bordeResaltado);
            }
        }
    }

    // Método para mover una pieza de una casilla a otra
    public void moverPieza(ImageView vistaImagen, int filaOrigen, int columnaOrigen, int filaDestino,
                           int columnaDestino) throws Exception {

        // Realizar el movimiento en el modelo
        tablero.moverPieza(filaOrigen, columnaOrigen, filaDestino, columnaDestino);

        // Actualizar la columna y fila en el GridPane
        GridPane.setColumnIndex(vistaImagen, columnaDestino);
        GridPane.setRowIndex(vistaImagen, filaDestino);
    }
}