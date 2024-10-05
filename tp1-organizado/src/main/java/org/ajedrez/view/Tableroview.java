package org.ajedrez.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La clase Tableroview es responsable de configurar y mostrar la interfaz gráfica del tablero de ajedrez.
 */
public class Tableroview {

    /**
     * Inicia la interfaz del tablero de ajedrez.
     *
     * @param escenarioPrincipal El escenario principal de la aplicación donde se mostrará el tablero.
     * @throws Exception Si ocurre un error durante la carga del archivo FXML.
     */
    public void start(Stage escenarioPrincipal) throws Exception {
        // Carga el archivo FXML que define la interfaz del tablero
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/org/ajedrez/tablero-view.fxml"));
        Parent root = cargadorFXML.load();

        // Configura el título del escenario principal
        escenarioPrincipal.setTitle("Tablero de Ajedrez");

        // Crea y establece la escena con el contenido cargado
        escenarioPrincipal.setScene(new Scene(root, 720, 720));

        // Muestra el escenario principal
        escenarioPrincipal.show();
    }


}
