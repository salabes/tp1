package org.ajedrez.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Tableroview {
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/ajedrez/tablero-view.fxml"));
        Parent raiz = loader.load();
        primaryStage.setTitle("Tablero de Ajedrez");
        primaryStage.setScene(new Scene(raiz, 720, 720));
        primaryStage.setResizable(false);

        Image icon = new Image(getClass().getResourceAsStream("/org/ajedrez/imagenes/Icono.png"));
        primaryStage.getIcons().add(icon);

        primaryStage.show();
    }
}
