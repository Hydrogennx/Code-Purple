package com.hydrogennx.javafx;

import com.hydrogennx.AttackSequence;
import com.hydrogennx.AttackType;
import com.hydrogennx.GameInstance;
import com.hydrogennx.TestBullet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TurnPhase extends WindowController implements Initializable {

    private GameInstance gameInstance;

    public TurnPhase() throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void funAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AttackSequence(AttackType.TEST) {

            double lastAttackTime = -1;
            ActionPhase actionPhase;

            @Override
            public void startAttack(ActionPhase actionPhase) { //TODO don't feed it an entire action phase, geez

                this.actionPhase = actionPhase;

            }

            @Override
            public void update(double time) {

                if (lastAttackTime == -1) {
                    lastAttackTime = time;
                }

                if (time - lastAttackTime > 0.1) {

                    TestBullet testBullet = new TestBullet();

                    actionPhase.spawnBullet(testBullet);

                    lastAttackTime = time;

                }

                System.out.println(lastAttackTime);

            }

        });

        gameInstance.queueAttack(attacks);

    }

    public void setGameInstance(GameInstance gameInstance) {
        if (this.gameInstance == null) {
            this.gameInstance = gameInstance;
        }
    }
}
