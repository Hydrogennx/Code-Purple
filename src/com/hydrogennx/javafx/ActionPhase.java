package com.hydrogennx.javafx;

import com.hydrogennx.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

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

    private double attackStartTime;
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
        gamePane.setScaleY(-1);
        healthBar.setProgress(1);

    }

    /**
     * Update method run every game frame.
     * As of right now, it decreases the user's health a bit and tells the attackSequences to update.
     * @param time
     */
    public void update(double time) {
        healthBar.setProgress(gameInstance.getCurrentPlayer().getHealth());

        if (attackStartTime == 0) {
            attackStartTime = time;

            startAttacks();

            controllableCharacter.setDefaultMovement();
            controllableCharacter.setContext(gamePane);
            controllableCharacter.setControllingPlayer(gameInstance.getCurrentPlayer());
        }

        gamePane.update(time);

        for (AttackSequence attackSequence : attackSequences) {

            boolean attackStillActive = attackSequence.update(time);

            if (!attackStillActive) {
                System.out.println("Attack is over!");
            }

        }

        controllableCharacter.update(time);

        clearAttacks();

        if (attackSequences.isEmpty()) {
            System.out.println("All attacks are over!");
            gameInstance.endAttack();
            reset();
        }

    }

    /**
     * Resets this ActionPhase to be indistinguishible from a newly created one.
     * Some variables are not reset, because their values should have already been previously, and instances where they are not should be detected and solved, not forgotten.
     */
    private void reset() {

        attackStartTime = 0;
        controllableCharacter.reset();
        gamePane.reset();

    }

    private void clearAttacks() {

        List<AttackSequence> attackSequencesToRemove = new ArrayList<>();

        for (AttackSequence attackSequence : attackSequences) {
            if (!attackSequence.isOngoing()) {
                attackSequencesToRemove.add(attackSequence);
            }
        }

        for (AttackSequence attackSequence : attackSequencesToRemove) {
            attackSequences.remove(attackSequence);
        }

    }

    public void startAttacks() {

        for (AttackSequence attackSequence : attackSequences) {

            attackSequence.startAttack(gamePane, attackStartTime);

        }

    }

    /**
     * Register the attacks that this action phase should use.
     * @param attackSequences
     */
    public void addAttackSequences(List<AttackSequence> attackSequences) {

        this.attackSequences.addAll(attackSequences);

    }

    public GameActionPane getGameActionPane() {

        return gamePane;

    }
}
