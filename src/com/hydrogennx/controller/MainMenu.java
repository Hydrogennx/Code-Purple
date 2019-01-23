package com.hydrogennx.controller;

import com.hydrogennx.core.GameManager;
import com.hydrogennx.core.network.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    TextField ipTextField;

    @FXML
    Button joinGameButton;

    @FXML
    Label connectionLog;

    @FXML
    public void ipChanged() {

        if (ipTextField.getText().length() > 0) {

            joinGameButton.setDisable(false);

        } else {

            joinGameButton.setDisable(true);

        }

    }

    @FXML
    public void joinGame() {

        gameManager.joinGame(ipTextField.getText());

    }

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
        Server server = new Server();
        server.gameInstance = gameManager.getGameInstance();

    }


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
}
