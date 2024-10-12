package org.ajedrez.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.ajedrez.model.*;

/**
 * El controlador para gestionar el proceso de coronación de un peón en el juego de ajedrez.
 * Permite al usuario seleccionar a qué pieza promocionar el peón (reina, torre, alfil o caballo).
 */
public class CoronarController {

    /** Controlador para manejar movimientos especiales, como la coronación. */
    private MovimientosEspecialesController movimientosEspecialesController;

    /** Fila de la posición destino del peón que se va a coronar. */
    private int filaDestino;

    /** Columna de la posición destino del peón que se va a coronar. */
    private int columnaDestino;

    /** Imagen de la reina blanca disponible para coronar. */
    @FXML
    private ImageView reinaBlanco;

    /** Imagen de la torre blanca disponible para coronar. */
    @FXML
    private ImageView torreBlanco;

    /** Imagen del alfil blanco disponible para coronar. */
    @FXML
    private ImageView alfilBlanco;

    /** Imagen del caballo blanco disponible para coronar. */
    @FXML
    private ImageView caballoBlanco;

    /** Imagen de la reina negra disponible para coronar. */
    @FXML
    private ImageView reinaNegro;

    /** Imagen del alfil negro disponible para coronar. */
    @FXML
    private ImageView alfilNegro;

    /** Imagen de la torre negra disponible para coronar. */
    @FXML
    private ImageView torreNegro;

    /** Imagen del caballo negro disponible para coronar. */
    @FXML
    private ImageView caballoNegro;

    /**
     * Establece la posición destino del peón que será coronado.
     *
     * @param filaDestino Fila de la posición del peón.
     * @param columnaDestino Columna de la posición del peón.
     */
    public void setPosicionPeon(int filaDestino, int columnaDestino) {
        this.filaDestino = filaDestino;
        this.columnaDestino = columnaDestino;
    }

    /**
     * Asigna el controlador de movimientos especiales, que maneja la lógica de la coronación.
     *
     * @param movimientosEspecialesController Controlador de movimientos especiales.
     */
    public void setMovimientosEspecialesController(MovimientosEspecialesController movimientosEspecialesController) {
        this.movimientosEspecialesController = movimientosEspecialesController;
    }

    /**
     * Selecciona una reina blanca para la coronación del peón.
     * Llama al método para coronar el peón y cierra la ventana de selección.
     */
    @FXML
    public void seleccionarReinaBlanca() {
        Pieza reina = new Reina(Color.BLANCO, this.filaDestino, this.columnaDestino);
        this.movimientosEspecialesController.coronarPeon(reina);
        cerrarVentana(reinaBlanco);
    }

    /**
     * Selecciona una reina negra para la coronación del peón.
     * Llama al método para coronar el peón y cierra la ventana de selección.
     */
    @FXML
    public void seleccionarReinaNegra() {
        Pieza reina = new Reina(Color.NEGRO, this.filaDestino, this.columnaDestino);
        this.movimientosEspecialesController.coronarPeon(reina);
        cerrarVentana(reinaNegro);
    }

    /**
     * Selecciona una torre blanca para la coronación del peón.
     * Llama al método para coronar el peón y cierra la ventana de selección.
     */
    @FXML
    public void seleccionarTorreBlanca() {
        Pieza torre = new Torre(Color.BLANCO, this.filaDestino, this.columnaDestino);
        this.movimientosEspecialesController.coronarPeon(torre);
        cerrarVentana(torreBlanco);
    }

    /**
     * Selecciona una torre negra para la coronación del peón.
     * Llama al método para coronar el peón y cierra la ventana de selección.
     */
    @FXML
    public void seleccionarTorreNegra() {
        Pieza torre = new Torre(Color.NEGRO, this.filaDestino, this.columnaDestino);
        this.movimientosEspecialesController.coronarPeon(torre);
        cerrarVentana(torreNegro);
    }

    /**
     * Selecciona un alfil blanco para la coronación del peón.
     * Llama al método para coronar el peón y cierra la ventana de selección.
     */
    @FXML
    public void seleccionarAlfilBlanco() {
        Pieza alfil = new Alfil(Color.BLANCO, this.filaDestino, this.columnaDestino);
        this.movimientosEspecialesController.coronarPeon(alfil);
        cerrarVentana(alfilBlanco);
    }

    /**
     * Selecciona un alfil negro para la coronación del peón.
     * Llama al método para coronar el peón y cierra la ventana de selección.
     */
    @FXML
    public void seleccionarAlfilNegro() {
        Pieza alfil = new Alfil(Color.NEGRO, this.filaDestino, this.columnaDestino);
        this.movimientosEspecialesController.coronarPeon(alfil);
        cerrarVentana(alfilNegro);
    }

    /**
     * Selecciona un caballo blanco para la coronación del peón.
     * Llama al método para coronar el peón y cierra la ventana de selección.
     */
    @FXML
    public void seleccionarCaballoBlanco() {
        Pieza caballo = new Caballo(Color.BLANCO, this.filaDestino, this.columnaDestino);
        this.movimientosEspecialesController.coronarPeon(caballo);
        cerrarVentana(caballoBlanco);
    }

    /**
     * Selecciona un caballo negro para la coronación del peón.
     * Llama al método para coronar el peón y cierra la ventana de selección.
     */
    @FXML
    public void seleccionarCaballoNegro() {
        Pieza caballo = new Caballo(Color.NEGRO, this.filaDestino, this.columnaDestino);
        this.movimientosEspecialesController.coronarPeon(caballo);
        cerrarVentana(caballoNegro);
    }

    /**
     * Cierra la ventana de selección de coronación.
     *
     * @param pieza La imagen de la pieza seleccionada.
     */
    private void cerrarVentana(ImageView pieza) {
        Stage stage = (Stage) pieza.getScene().getWindow();
        stage.close();
    }
}
