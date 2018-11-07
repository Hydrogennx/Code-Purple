package com.hydrogennx;

import java.util.List;

/**
 * Manages the bit of the game we care about.
 * It moves the information about the game state from the TurnPhase to the ActionPhase and back again.
 */
public class LocalPracticeInstance extends GameInstance {

    List<AttackSequence> attackSequencesToDefendAgainst;

    public LocalPracticeInstance(GameManager gameManager) {
        super(gameManager);
    }

    public void queueAttack(List<AttackSequence> attackSequencesToDefendAgainst) {
        this.attackSequencesToDefendAgainst = attackSequencesToDefendAgainst;
        changeGameState(GameState.ACTION);
    }


}
