package com.hydrogennx.controller;

import com.hydrogennx.core.*;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.attack.AxisAttack;
import com.hydrogennx.core.attack.HealDefense;
import com.hydrogennx.core.attack.RainAttack;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TurnPhase extends WindowController implements Initializable {
    MediaPlayer pressedMusic;
    MediaPlayer releasedMusic;
    private GameInstance gameInstance;
    @FXML
    private ImageView instructions = new ImageView();
    @FXML
    private ProgressBar mainHealthBar;
    private ProgressIndicator waitingCircle = new ProgressIndicator();
    private boolean visible = false;

    public TurnPhase() throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void funAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new RainAttack(Direction.DOWN));

        gameInstance.queueAttack(attacks, gameInstance.getCurrentPlayer());

    }

    @FXML
    public void boringAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AxisAttack());

        gameInstance.queueAttack(attacks, gameInstance.getCurrentPlayer());

    }

    @FXML
    public void mixedAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new HealDefense());
        attacks.add(new RainAttack(Direction.UP));
        attacks.add(new RainAttack(Direction.DOWN));

        gameInstance.queueAttack(attacks, gameInstance.getCurrentPlayer());

    }

    @FXML
    public void allAtOnce() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AxisAttack());
        attacks.add(new RainAttack(Direction.LEFT));
        attacks.add(new RainAttack(Direction.RIGHT));

        gameInstance.queueAttack(attacks, gameInstance.getCurrentPlayer());

    }

    @FXML
    public void helpButtonPressed() {

        if (!visible) {
            instructions.setVisible(true);
            visible = true;
        } else if (visible) {
            instructions.setVisible(false);
            visible = false;
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

    public void backButton() {
        gameInstance.endGame();
    }

    /**
     * Updates the state of things in the TurnPhase.
     * As TurnPhase does not include any live action, this does not have to be called 60 times a second,
     * and is instead run whenever the game switches to it.
     */
    public void updateState() {

        mainHealthBar.setProgress(gameInstance.getCurrentPlayer().getHealth());


    }

    public void setGameInstance(GameInstance gameInstance) {
        if (this.gameInstance == null) {
            this.gameInstance = gameInstance;
        }
    }
}
