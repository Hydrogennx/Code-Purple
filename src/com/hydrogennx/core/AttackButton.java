package com.hydrogennx.core;

import com.hydrogennx.core.attack.AttackSequence;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.event.MouseEvent;
import java.util.List;

public class AttackButton extends SoundButton {

    List<AttackSequence> attackSequences;

    GameInstance gameInstance;

    public AttackButton(List<AttackSequence> attackSequences) {

        setOnMousePressed(me -> {

            gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attackSequences);

        });

    }

}
