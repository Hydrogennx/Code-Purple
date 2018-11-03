package com.hydrogennx;

import java.util.List;

/**
 * An abstract class. This holds all relevant information about the current game.
 * This class is never used directly -- instead, a LocalPracticeManager, ClientManager or HostManager should be created.
 */
public abstract class GameInstance {

    GameManager gameManager;

    public GameInstance(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    //TODO create a ClientManager and a HostManager when we are ready to begin netcode.

    public abstract void queueAttack(List<AttackSequence> attacksToDefendAgainst);

}
