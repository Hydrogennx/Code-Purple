package com.hydrogennx.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JavaFXTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        System.out.println("Hello World!");

        URL resource = getClass().getResource("JavaFXTest.fxml");
        System.out.println(resource);
        Parent mainWindow = FXMLLoader.load(resource);

        primaryStage.setTitle("First Jump JavaFX MainMenu");
        primaryStage.setScene(new Scene(mainWindow));
        primaryStage.show();


    }
}
