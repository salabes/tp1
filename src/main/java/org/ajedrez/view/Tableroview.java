package org.ajedrez.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tableroview {
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/ajedrez/tablero-view.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tablero de Ajedrez");
        primaryStage.setScene(new Scene(root, 720, 720));
        primaryStage.show();
    }
}
