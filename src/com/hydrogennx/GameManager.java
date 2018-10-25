package com.hydrogennx;

import java.util.List;

/**
 * An abstract class. This holds all relevant information about the current game.
 * This class is never used directly -- instead, a LocalPracticeManager, ClientManager or HostManager should be created.
 */
public abstract class GameManager {

    FirstJump firstJump;

    public GameManager(FirstJump firstJump) {
        this.firstJump = firstJump;
    }

    //TODO create a ClientManager and a HostManager when we are ready to begin netcode.

    public abstract void queueAttack(List<AttackSequence> attacksToDefendAgainst);

}
