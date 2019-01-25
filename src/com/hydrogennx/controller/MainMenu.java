package com.hydrogennx.controller;

import com.hydrogennx.core.GameManager;
import com.hydrogennx.core.network.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
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

    @FXML
    Button joinGameButton;

    @FXML
    ImageView publisherCredit;

    @FXML
    ImageView devCredit;

    public MainMenu() throws IOException {

    }

    @FXML
    public void exitButtonPressed() {
        gameManager.exit();
        //Exits the app
    }

    @FXML
    public void practiceButtonPressed() {
        gameManager.startLocalPractice();
        //Opens the playing menu
    }

    @FXML
    public void hostGameButtonPressed() {
        gameManager.startNetworkGame();
    }

    @FXML
    public void joinGame() {
        gameManager.joinGame();
    }

    @FXML
    public void optionButtonPressed() {
        gameManager.openSettingsMenu();
    }

    public void setGameManager(GameManager gameManager) {
        if (this.gameManager == null) {
            this.gameManager = gameManager;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateTransition(double time) {

        if (time < 3) {
            publisherCredit.setOpacity(1 - (time - 2));
        } else if (time > 5 && time < 6) {
            devCredit.setOpacity(1 - (time - 5));
        } else if (time > 6) {
            devCredit.setVisible(false);
            publisherCredit.setVisible(false);
        }

    }
}
