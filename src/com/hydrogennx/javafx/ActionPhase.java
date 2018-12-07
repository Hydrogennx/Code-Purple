package com.hydrogennx.javafx;

import com.hydrogennx.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ActionPhase extends WindowController implements Initializable {

    private GameInstance gameInstance;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private GameActionPane gamePane;

    @FXML
    private ControllableCharacter controllableCharacter;

    private List<AttackSequence> attackSequences = new ArrayList<>();

    public ActionPhase() throws IOException {



    }

    public void setGameInstance(GameInstance gameInstance) {
        if (gameInstance != null) {
            this.gameInstance = gameInstance;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        healthBar.setProgress(1);

    }

    /**
     * Update method run every game frame.
     * As of right now, it decreases the user's health a bit and tells the attackSequences to update.
     * @param time
     */
    public void update(double time) {
        healthBar.setProgress(healthBar.getProgress() - 0.001);

        gamePane.update(time);

        for (AttackSequence attackSequence : attackSequences) {

            attackSequence.update(time);

        }

    }

    /**
     * Register the attacks that this action phase should use.
     * @param attackSequences
     */
    public void addAttackSequences(List<AttackSequence> attackSequences) {

        this.attackSequences.addAll(attackSequences);

        for (AttackSequence attackSequence : attackSequences) {

            attackSequence.startAttack(this);

        }

    }

    public GameActionPane getGameActionPane() {

        return gamePane;

    }
}
