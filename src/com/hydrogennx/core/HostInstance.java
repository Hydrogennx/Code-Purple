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

        gameState = GameState.YET_TO_BEGIN;

        gameManager.screenFramework.wcm.getScreen(ScreenFramework.SERVER_SETUP_ID);

    }

    @Override
    public void updateScreen() {
        switch (gameState) {
            case YET_TO_BEGIN:
                gameManager.screenFramework.wcm.setScreen(ScreenFramework.SERVER_SETUP_ID);
            case TURN:
                gameManager.screenFramework.wcm.setScreen(ScreenFramework.TURN_PHASE_ID);
                break;
            case ACTION:
                gameManager.screenFramework.wcm.setScreen(ScreenFramework.ACTION_PHASE_ID);
                break;
            case GAME_OVER:
                gameManager.screenFramework.wcm.setScreen(ScreenFramework.GAME_OVER_ID);
                break;
            default:
                return; //should not happen
        }
    }

    @Override
    public void queueAttack(List<AttackSequence> attackSequences, Player player) {

        //queue attack, and if all players have a queued attack...

        changeGameState(GameState.ACTION);

        ActionPhase actionPhase = (ActionPhase) gameManager.screenFramework.wcm.getController(ScreenFramework.ACTION_PHASE_ID);
        actionPhase.addAttackSequences(attackSequences);

    }

    @Override
    public void recallAttack(List<AttackSequence> attackSequences, Player attacker) {

    }

    @Override
    public void updatePlayerState(Player player) {

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

        changeGameState(GameState.YET_TO_BEGIN);

    }

    @Override
    public Player getCurrentPlayer() {
        return mainPlayer;
    }

    @Override
    public void networkLog(String s) {

    }

    @Override
    public void addPlayer(Player player) {

    }


}
