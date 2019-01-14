package com.hydrogennx.core;

import com.hydrogennx.controller.ActionPhase;
import com.hydrogennx.controller.TurnPhase;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.javafx.ScreenFramework;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Controls the server side of a two-player network session.
 */
public class HostInstance extends GameInstance {

    Player mainPlayer;

    public HostInstance(GameManager gameManager) {
        super(gameManager);

        mainPlayer = new Player(Color.RED, "Host");

        //TODO add players as they join

        mainPlayer.addEnemy(mainPlayer);

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
