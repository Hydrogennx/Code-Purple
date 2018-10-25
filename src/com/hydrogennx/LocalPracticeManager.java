package com.hydrogennx;

import java.util.List;

/**
 * Manages the bit of the game we care about.
 * It moves the information about the game state from the TurnPhase to the ActionPhase and back again.
 */
public class LocalPracticeManager extends GameManager {

    List<AttackSequence> attackSequencesToDefendAgainst;

    public LocalPracticeManager(FirstJump firstJump) {
        super(firstJump);
    }

    public void queueAttack(List<AttackSequence> attackSequencesToDefendAgainst) {
        this.attackSequencesToDefendAgainst = attackSequencesToDefendAgainst;
        firstJump.changeGameState(GameState.ACTION);
    }


}
