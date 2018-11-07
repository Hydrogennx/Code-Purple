package com.hydrogennx.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppTest extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        primaryStage.setTitle("First Jump JavaFX MainMenu");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }
}
