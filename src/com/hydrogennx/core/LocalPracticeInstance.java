package com.hydrogennx.core;

import com.hydrogennx.controller.ActionPhase;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.javafx.ScreenFramework;
import com.hydrogennx.controller.TurnPhase;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Manages the bit of the game we care about.
 * It moves the information about the game state from the TurnPhase to the ActionPhase and back again.
 */
public class LocalPracticeInstance extends GameInstance {

    Player mainPlayer;

    public LocalPracticeInstance(GameManager gameManager) {
        super(gameManager);

        mainPlayer = new Player(Color.AQUA, "Practice");

        mainPlayer.addEnemy(mainPlayer); //you are your own worst enemy, because you are your only enemy

        allPlayers.add(mainPlayer);

        gameState = GameState.TURN;

        gameManager.screenFramework.wcm.getScreen(ScreenFramework.ACTION_PHASE_ID);

    }

    @Override
    public void queueAttack(List<AttackSequence> attackSequences) {
        changeGameState(GameState.ACTION);

        ActionPhase actionPhase = (ActionPhase) gameManager.screenFramework.wcm.getController(ScreenFramework.ACTION_PHASE_ID);
        actionPhase.addAttackSequences(attackSequences);

    }


    @Override
    public void endAttack() {
        changeGameState(GameState.TURN);

        TurnPhase turnPhase = (TurnPhase) gameManager.screenFramework.wcm.getController(ScreenFramework.TURN_PHASE_ID);
        turnPhase.updateState();

    }

    @Override
    public void registerDefeat() {

        changeGameState(GameState.GAME_OVER);

    }

    @Override
    public void endGame() {

        gameManager.stopGame();

        changeGameState(GameState.MENU);

    }

    @Override
    public Player getCurrentPlayer() {
        return mainPlayer;
    }


}
