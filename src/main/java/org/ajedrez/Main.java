package org.ajedrez;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ajedrez.controller.VentanaController;

/**
 * La clase Main es el punto de entrada de la aplicación de ajedrez.
 * Extiende la clase Application de JavaFX y maneja la inicialización de la interfaz gráfica.
 */
public class Main extends Application {

    /**
     * El método start es el punto de inicio de la aplicación JavaFX.
     * Aquí se inicializa la vista del tablero de ajedrez y se asigna al escenario principal (Stage).
     *
     * @param escenarioPrincipal el escenario principal de la aplicación donde se mostrarán las vistas.
     * @throws Exception si ocurre algún error durante la inicialización de la vista.
     */
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        VentanaController ventanaController = new VentanaController();
        ventanaController.abrirVentanaInicial(null,escenarioPrincipal);
    }

    /**
     * El método main es el punto de entrada del programa.
     * Llama al método launch para iniciar la aplicación JavaFX.
     *
     * @param args los argumentos de la línea de comandos.
     */
}

