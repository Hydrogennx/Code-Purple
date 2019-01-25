package com.hydrogennx.controller;

import com.hydrogennx.core.*;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.attack.AxisAttack;
import com.hydrogennx.core.attack.HealDefense;
import com.hydrogennx.core.attack.RainAttack;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    private GameInstance gameInstance;

    @FXML
    private ImageView instructions = new ImageView();

    @FXML
    private ProgressBar mainHealthBar;

    @FXML
    private Label mainName;

    @FXML
    private ImageView mainColor;

    @FXML
    private ProgressBar otherHealthBar;

    @FXML
    private Label otherName;

    @FXML
    private ImageView otherColor;

    @FXML
    private ProgressIndicator waitingCircle;

    List<ImageView> mainMana = new ArrayList<>();
    List<ImageView> bonusMana = new ArrayList<>();

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

        gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attacks);

    }

    @FXML
    public void boringAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AxisAttack());

        gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attacks);

    }

    @FXML
    public void mixedAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new HealDefense());
        attacks.add(new RainAttack(Direction.UP));
        attacks.add(new RainAttack(Direction.DOWN));

        gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attacks);

    }

    @FXML
    public void allAtOnce() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AxisAttack());
        attacks.add(new RainAttack(Direction.LEFT));
        attacks.add(new RainAttack(Direction.RIGHT));

        gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attacks);

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

        if (gameInstance instanceof NetworkGameInstance) {

            NetworkGameInstance networkGameInstance = (NetworkGameInstance) gameInstance;

            otherHealthBar.setProgress(networkGameInstance.getOtherPlayer().getHealth());

        }

    }

    public void setGameInstance(GameInstance gameInstance) {
        if (this.gameInstance == null) {
            this.gameInstance = gameInstance;

            if (!(gameInstance instanceof NetworkGameInstance)) {

                otherHealthBar.setVisible(false);
                otherName.setVisible(false);
                otherColor.setVisible(false);
                waitingCircle.setVisible(false);

            }

        }
    }

    public void setOtherPlayerDecision(boolean decided) {

        if (decided) {
            waitingCircle.setProgress(1);
        } else {
            waitingCircle.setProgress(-1);
        }


    }

}
