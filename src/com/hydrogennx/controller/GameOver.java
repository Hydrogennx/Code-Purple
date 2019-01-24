package com.hydrogennx.controller;

import com.hydrogennx.core.GameInstance;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOver extends WindowController implements Initializable {
    MediaPlayer pressedMusic;
    MediaPlayer releasedMusic;
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
    public void mousePressed() {
        String mouseDownMusicFile = "src/com/hydrogennx/core/resource/MouseDown.mp3";
        Media pressed = new Media(new File(mouseDownMusicFile).toURI().toString());

        pressedMusic = new MediaPlayer(pressed);
        pressedMusic.play();
    }

    @FXML
    public void mouseReleased() {
        String mouseUpMusicFile = "src/com/hydrogennx/core/resource/MouseUp.mp3";
        Media released = new Media(new File(mouseUpMusicFile).toURI().toString());

        releasedMusic = new MediaPlayer(released);
        releasedMusic.play();
    }

    @FXML
    public void mainMenuButtonPressed() {
        gameInstance.endGame();
    }

}
