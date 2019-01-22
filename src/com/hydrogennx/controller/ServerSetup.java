package com.hydrogennx.controller;

import com.hydrogennx.core.HostInstance;
import com.hydrogennx.core.network.Server;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ServerSetup extends WindowController {

    @FXML
    Label playerList;

    @FXML
    Button startGameButton;

    @FXML
    Button stopHostingButton;

    HostInstance gameInstance;

    public void setGameCanBegin(boolean serverCanBegin) {

        if (serverCanBegin) {
            startGameButton.setDisable(false);
        } else {
            startGameButton.setDisable(true);
        }

    }

    public void setGameInstance(HostInstance hostInstance) {
        if (this.gameInstance == null) {
            this.gameInstance = hostInstance;
        }
    }

    @FXML
    public void stopHosting() {

        gameInstance.endGame();

    }

}
