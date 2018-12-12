package com.hydrogennx;

import com.hydrogennx.javafx.ActionPhase;
import com.hydrogennx.javafx.ScreenFramework;
import com.hydrogennx.javafx.TurnPhase;

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

    @Override
    public void queueAttack(List<AttackSequence> attackSequences) {
        this.attackSequencesToDefendAgainst = attackSequences;
        changeGameState(GameState.ACTION);

        ActionPhase actionPhase = (ActionPhase) gameManager.screenFramework.wcm.getController(ScreenFramework.ACTION_PHASE_ID);
        actionPhase.addAttackSequences(attackSequences);

    }


    @Override
    public void endAttack() {
        changeGameState(GameState.TURN);

        TurnPhase turnPhase = (TurnPhase) gameManager.screenFramework.wcm.getController(ScreenFramework.TURN_PHASE_ID);
        //TODO transfer health and status debuffs between games
        //turnPhase.updatePlayerData();

    }


}
