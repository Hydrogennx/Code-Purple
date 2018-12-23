package com.hydrogennx.controller;

import com.hydrogennx.core.GameManager;
import com.hydrogennx.core.javafx.WindowController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//FIXME have MainMenu.fxml point to THIS CLASS as its controller, rather than the whole GameManager.
public class MainMenu extends WindowController implements Initializable {

    private GameManager gameManager = null;

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

    public MainMenu() throws IOException {

    }

    @FXML
    public void exitButtonPressed() {
        //TODO actually exit the app
        gameManager.exit();
    }

    @FXML
    public void playButtonPressed() {
        //TODO actually play the game
        gameManager.startLocalPractice();
    }

    public void setGameManager(GameManager gameManager) {
        if (this.gameManager == null) {
            this.gameManager = gameManager;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
