package com.hydrogennx.controller;

import com.hydrogennx.core.NetworkGameInstance;
import com.hydrogennx.core.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ServerSetup extends WindowController {
    MediaPlayer pressedMusic;
    MediaPlayer releasedMusic;
    @FXML
    Label playerList;

    @FXML
    Button startGameButton;

    @FXML
    Button stopSessionButton;

    @FXML
    Button joinGameButton;

    @FXML
    TextField ipTextField;

    NetworkGameInstance gameInstance;

    int refreshes;

    @FXML
    public void startGame() {
        gameInstance.beginNetworkGame();
    }

    public void hideIpEntryField() {
        joinGameButton.setDisable(true);
        ipTextField.setDisable(true);
    }

    public void setGameCanBegin(boolean serverCanBegin) {

        if (serverCanBegin) {
            startGameButton.setDisable(false);
        } else {
            startGameButton.setDisable(true);
        }

    }

    public void setGameInstance(NetworkGameInstance networkGameInstance) {
        if (this.gameInstance == null) {
            this.gameInstance = networkGameInstance;

            if (networkGameInstance.isHosting()) {

                hideIpEntryField();

            }
        }
    }

    @FXML
    public void joinGame() {
        gameInstance.joinGame(ipTextField.getText());
    }

    @FXML
    public void stopSession() {

        gameInstance.endGame();

    }

    @FXML
    public void refreshPlayerList() {

        updatePlayers();

    }

    public void updatePlayers() {

        refreshes++;

        String playerListText = "";

        for (Player player : gameInstance.getAllPlayers()) {

            playerListText += player.getName() + "\n";

        }

        playerList.setText(playerListText + refreshes);

    }

}
