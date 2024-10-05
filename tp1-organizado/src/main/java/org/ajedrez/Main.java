package org.ajedrez;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ajedrez.view.Tableroview;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Tableroview view = new Tableroview();
        view.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
