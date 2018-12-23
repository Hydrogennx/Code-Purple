package com.hydrogennx.controller;

import com.hydrogennx.core.*;
import com.hydrogennx.core.bullet.DiamondBullet;
import com.hydrogennx.core.bullet.SpearBullet;
import com.hydrogennx.core.javafx.WindowController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TurnPhase extends WindowController implements Initializable {

    private GameInstance gameInstance;

    @FXML
    private ProgressBar mainHealthBar;
    private ProgressIndicator waitingCircle = new ProgressIndicator();

    public TurnPhase() throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void funAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AttackSequence() {

            double lastAttackTime;
            int numAttacks;

            @Override
            public void startAttack(GameActionPane context, double time) {

                super.startAttack(context, time);

                this.lastAttackTime = attackStartTime;

            }

            @Override
            public boolean attackStep(double time) {

                if (time - lastAttackTime > 0.1 && numAttacks < 80) {

                    DiamondBullet testBullet = new DiamondBullet(context, this);

                    context.spawnBullet(testBullet);

                    lastAttackTime = time;

                    numAttacks++;

                }

                return true;

            }

        });

        gameInstance.queueAttack(attacks);

    }

    @FXML
    public void boringAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AttackSequence() {

            double lastAttackTime;
            int numAttacks;

            @Override
            public void startAttack(GameActionPane context, double time) {

                super.startAttack(context, time);

                this.lastAttackTime = attackStartTime;

            }

            @Override
            public boolean attackStep(double time) {

                if (time - lastAttackTime > 0.1 && numAttacks < 80) {

                    SpearBullet testBullet = new SpearBullet(context, this);

                    context.spawnBullet(testBullet);

                    lastAttackTime = time;

                    numAttacks++;

                }

                return true;

            }

        });

        gameInstance.queueAttack(attacks);

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