package com.hydrogennx.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent testWindow = FXMLLoader.load(getClass().getResource("TestWindow.fxml"));
        primaryStage.setTitle("First Jump JavaFX Test");
        primaryStage.setScene(new Scene(testWindow));
        primaryStage.show();

        System.out.println("JavaFX test successful.");

    }
}
