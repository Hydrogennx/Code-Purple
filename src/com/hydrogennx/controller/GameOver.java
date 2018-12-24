package com.hydrogennx.controller;

import com.hydrogennx.core.GameInstance;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOver extends WindowController implements Initializable {

    private GameInstance gameInstance;

    @FXML
    Button mainMenuButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    public void setGameInstance(GameInstance gameInstance) {
        if (gameInstance != null) {
            this.gameInstance = gameInstance;
        }
    }



    @FXML
    public void mainMenuButtonPressed() {
        gameInstance.endGame();
    }

}
