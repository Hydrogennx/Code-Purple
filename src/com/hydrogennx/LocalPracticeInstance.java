package com.hydrogennx;

import com.hydrogennx.javafx.ActionPhase;
import com.hydrogennx.javafx.ScreenFramework;

import java.util.List;

/**
 * Manages the bit of the game we care about.
 * It moves the information about the game state from the TurnPhase to the ActionPhase and back again.
 */
public class LocalPracticeInstance extends GameInstance {

    List<AttackSequence> attackSequencesToDefendAgainst;

    public LocalPracticeInstance(GameManager gameManager) {
        super(gameManager);

        gameState = GameState.TURN;

        gameManager.screenFramework.wcm.getScreen(ScreenFramework.ACTION_PHASE_ID);

    }

    public void queueAttack(List<AttackSequence> attackSequencesToDefendAgainst) {
        this.attackSequencesToDefendAgainst = attackSequencesToDefendAgainst;
        changeGameState(GameState.ACTION);

        ActionPhase actionPhase = (ActionPhase) gameManager.screenFramework.wcm.getController(ScreenFramework.ACTION_PHASE_ID);
        actionPhase.addAttackSequences(attackSequencesToDefendAgainst);

    }


}
