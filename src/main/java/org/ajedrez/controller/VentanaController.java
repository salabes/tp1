package org.ajedrez.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import org.ajedrez.model.Color;

/**
 * Controlador para manejar las ventanas de la aplicación de ajedrez.
 * Permite abrir, reutilizar y cerrar diferentes ventanas en la interfaz gráfica.
 */
public class VentanaController {

    @FXML
    private Label nombreTexto; // Etiqueta que muestra el texto en la ventana.

    /**
     * Reutiliza la ventana inicial, cerrando la ventana actual y mostrando un nuevo mensaje.
     *
     * @param event El evento de acción que desencadena este método.
     * @param textoVentana El texto que se mostrará en la ventana reutilizada.
     * @param etiqueta La etiqueta que se utilizará para cerrar la ventana actual.
     * @throws Exception Si ocurre un error al cargar la ventana.
     */
    @FXML
    public void reutilizarVentanaInicial(ActionEvent event, String textoVentana, Label etiqueta) throws Exception {
        if (event == null) {
            cerrarVentana(etiqueta);
        } else {
            cerrarVentana(event);
        }
        Stage escenario = new Stage();
        FXMLLoader cargadorFXML = CargarFxml(event, escenario, "jugar-salir.fxml", "POWERCHESS");
        VentanaController ventanaController = cargadorFXML.getController();
        ventanaController.setNombreTexto(textoVentana);
    }

    /**
     * Abre la ventana inicial.
     *
     * @param event El evento de acción que desencadena este método.
     * @param escenario La etapa (ventana) en la que se abrirá la ventana inicial.
     * @throws Exception Si ocurre un error al cargar la ventana.
     */
    public void abrirVentanaInicial(ActionEvent event, Stage escenario) throws Exception {
        CargarFxml(event, escenario, "jugar-salir.fxml", "POWERCHESS");
    }

    /**
     * Abre la ventana de gestión de usuarios.
     *
     * @param event El evento de acción que desencadena este método.
     * @throws Exception Si ocurre un error al cargar la ventana.
     */
    @FXML
    public void abrirVentanaUsuarios(ActionEvent event) throws Exception {
        Stage escenario = new Stage();
        CargarFxml(event, escenario, "registro-usuario.fxml", "Gestión de Usuarios");
    }

    /**
     * Abre la ventana del tablero de ajedrez.
     *
     * @param event El evento de acción que desencadena este método.
     * @return El controlador de la ventana del tablero.
     * @throws Exception Si ocurre un error al cargar la ventana.
     */
    public TableroController abrirVentanaTablero(ActionEvent event) throws Exception {
        Stage escenario = new Stage();
        FXMLLoader cargadorFXML = CargarFxml(event, escenario, "tablero-view.fxml", "POWERCHESS");
        TableroController tableroController = cargadorFXML.getController();
        return tableroController;
    }

    /**
     * Abre la ventana de coronación de una pieza.
     *
     * @param event El evento de acción que desencadena este método.
     * @param colorPieza El color de la pieza que se va a coronar.
     * @param filaDestino La fila donde se ubicará la pieza coronada.
     * @param columnaDestino La columna donde se ubicará la pieza coronada.
     * @param movimientosEspeciales El controlador de movimientos especiales.
     * @throws Exception Si ocurre un error al cargar la ventana.
     */
    public void abrirVentanaCoronacion(ActionEvent event, Color colorPieza, int filaDestino, int columnaDestino, MovimientosEspecialesController movimientosEspeciales) throws Exception {
        Stage escenario = new Stage();
        escenario.initStyle(StageStyle.UNDECORATED);
        FXMLLoader cargadorFXML;

        if (colorPieza == Color.BLANCO) {
            cargadorFXML = CargarFxml(event, escenario, "Coronar-peonBlanco.fxml", "Coronación");
        } else {
            cargadorFXML = CargarFxml(event, escenario, "Coronar-peonNegro.fxml", "Coronación");
        }

        CoronarController coronarController = cargadorFXML.getController();
        coronarController.setPosicionPeon(filaDestino, columnaDestino);
        coronarController.setMovimientosEspecialesController(movimientosEspeciales);
    }

    /**
     * Carga un archivo FXML en una nueva ventana.
     *
     * @param event El evento de acción que desencadena este método.
     * @param escenario La etapa (ventana) que se abrirá.
     * @param nombreArchivo El nombre del archivo FXML a cargar.
     * @param Titulo El título que se mostrará en la ventana.
     * @return El cargador FXML utilizado para cargar la ventana.
     * @throws Exception Si ocurre un error al cargar la ventana.
     */
    public FXMLLoader CargarFxml(ActionEvent event, Stage escenario, String nombreArchivo, String Titulo) throws Exception {
        // Cargar el archivo FXML de la nueva ventana
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/org/ajedrez/" + nombreArchivo));
        Parent root = cargadorFXML.load();
        // Crear una nueva escena con el contenido del archivo FXML
        escenario.setTitle(Titulo);
        escenario.setScene(new Scene(root));
        escenario.setResizable(false);
        escenario.show();

        if (event != null) {
            // Obtener el Stage actual y cerrarlo
            cerrarVentana(event);
        }

        return cargadorFXML;
    }

    /**
     * Establece el texto de la etiqueta que muestra el nombre.
     *
     * @param texto El texto que se establecerá en la etiqueta.
     */
    public void setNombreTexto(String texto) {
        nombreTexto.setText(texto);
    }

    /**
     * Cierra la ventana actual a partir del evento.
     *
     * @param event El evento de acción que desencadena este método.
     * @throws Exception Si ocurre un error al cerrar la ventana.
     */
    @FXML
    public void cerrarVentana(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Cierra la ventana actual a partir de una etiqueta.
     *
     * @param etiqueta La etiqueta que se utilizará para cerrar la ventana.
     */
    public void cerrarVentana(Label etiqueta) {
        Stage stage = (Stage) etiqueta.getScene().getWindow();
        stage.close();
    }
}
