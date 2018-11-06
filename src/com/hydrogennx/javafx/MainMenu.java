package com.hydrogennx.javafx;

import com.hydrogennx.JavaFXGameManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//FIXME have MainMenu.fxml point to THIS CLASS as its controller, rather than the whole GameManager.
public class MainMenu implements Initializable {

    private Parent mainWindow;
    private JavaFXGameManager gameManager;

//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws IOException {
//        primaryStage.setTitle("First Jump JavaFX MainMenu");
//        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
//        primaryStage.show();
//
//        System.out.println("JavaFX test successful.");
//
//    }

    public MainMenu(JavaFXGameManager gameManager) throws IOException {

        this.gameManager = gameManager;

        URL resource = getClass().getResource("MainMenu.fxml");
        System.out.println(resource);
        mainWindow = FXMLLoader.load(resource);

    }

    public Parent getMainWindow() {
        return mainWindow;
    }

    public void exitButtonPressed() throws Exception {
        gameManager.stop();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO do things here
    }
}
