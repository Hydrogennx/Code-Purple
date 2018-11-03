package com.hydrogennx;

import com.hydrogennx.javafx.ActionPhase;
import com.hydrogennx.javafx.TurnPhase;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.List;

/**
 * An abstract class. This holds all relevant information about the current game.
 * This class is never used directly -- instead, a LocalPracticeInstance, ClientManager or HostManager should be created.
 */
public abstract class GameInstance {

    SwingGameManager gameManager;
    GameState gameState;

    public GameInstance(SwingGameManager gameManager) {
        this.gameManager = gameManager;
    }

    //TODO create a ClientManager and a HostManager when we are ready to begin netcode.

    public abstract void queueAttack(List<AttackSequence> attacksToDefendAgainst);

    //FIXME this only works with the JavaFX version of the game, and should be removed if we finish with Swing.
    public Parent getCurrentWindow() {
        try {
            switch (gameState) {
                case TURN:
                    return new TurnPhase(this).getMainWindow();
                case ACTION:
                    return new ActionPhase(this).getMainWindow();
                default:
                    return null; //should not happen
            }
        } catch (IOException e) {
            return null; //should not happen
        }
    }

}
