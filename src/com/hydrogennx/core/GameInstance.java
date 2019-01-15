package com.hydrogennx.core;

import com.hydrogennx.controller.ActionPhase;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.javafx.ScreenFramework;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class. This holds all relevant information about the current game.
 * This class is never used directly -- instead, a LocalPracticeInstance, ClientManager or HostManager should be created.
 */
public abstract class GameInstance {

    GameManager gameManager;
    GameState gameState;

    /**
     * List of all players, in no particular order.
     */
    List<Player> allPlayers = new ArrayList<>();

    public GameInstance(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public abstract void updateScreen();

    protected void changeGameState(GameState gameState) {
        this.gameState = gameState;
        gameManager.updateScreen();
    }

    public void update(double time) {

        if (gameState == GameState.ACTION) {

            ActionPhase actionPhase = (ActionPhase) gameManager.screenFramework.wcm.getController(ScreenFramework.ACTION_PHASE_ID);

            actionPhase.update(time);

        }

    }

    //TODO create a ClientManager and a HostManager when we are ready to begin netcode.

    public abstract void queueAttack(List<AttackSequence> attackSequences, Player attacker);

    public abstract void recallAttack(List<AttackSequence> attackSequences, Player attacker);

    public abstract void updatePlayerState(Player player);

    public abstract void endAttack();
    public abstract void registerDefeat();

    public abstract void endGame();

    public abstract Player getCurrentPlayer();

    public abstract void networkLog(String s);

    public abstract void addPlayer(Player player);

}
