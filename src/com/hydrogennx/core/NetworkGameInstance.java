package com.hydrogennx.core;

import com.hydrogennx.controller.ActionPhase;
import com.hydrogennx.controller.ServerSetup;
import com.hydrogennx.controller.TurnPhase;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.javafx.ScreenFramework;
import com.hydrogennx.core.network.Client;
import com.hydrogennx.core.network.Server;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the server side of a two-player network session.
 */
public class NetworkGameInstance extends GameInstance {

    Player mainPlayer;

    Client client;
    Server server;

    boolean hosting;

    List<Player> serversidePlayerList = new ArrayList<>();

    public NetworkGameInstance(GameManager gameManager, boolean host) {
        super(gameManager);

        mainPlayer = new Player(PlayerColor.BLUE, gameManager.getSettings().getUsername());

        allPlayers.add(mainPlayer);

        gameState = GameState.YET_TO_BEGIN;

        if (host) {
            hosting = true;
            server = new Server(this);
        }

    }

    @Override
    public void updateScreen() {
        switch (gameState) {
            case YET_TO_BEGIN:
                gameManager.setScreen(ScreenFramework.SERVER_SETUP_ID);
                break;
            case TURN:
                gameManager.setScreen(ScreenFramework.TURN_PHASE_ID);
                break;
            case ACTION:
                gameManager.setScreen(ScreenFramework.ACTION_PHASE_ID);
                break;
            case GAME_OVER:
                gameManager.setScreen(ScreenFramework.GAME_OVER_ID);
                break;
            default:
                return; //should not happen
        }
    }

    @Override
    public void queueAttack(List<AttackSequence> attackSequences, Player player) {

        //queue attack, and if all players have a queued attack...

        changeGameState(GameState.ACTION);

        ActionPhase actionPhase = (ActionPhase) gameManager.getWindowController(ScreenFramework.ACTION_PHASE_ID);
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

        TurnPhase turnPhase = (TurnPhase) gameManager.getWindowController(ScreenFramework.TURN_PHASE_ID);
        turnPhase.updateState();

    }

    @Override
    public void registerDefeat() {

        changeGameState(GameState.GAME_OVER);

    }

    @Override
    public void endGame() {

        gameManager.stopGame();

        if (isHosting()) {
            server.closeConnection();
        } else {
            client.closeConnection();
        }

        changeGameState(GameState.YET_TO_BEGIN);

    }

    @Override
    public Player getCurrentPlayer() {
        return mainPlayer;
    }

    public void networkLog(String s) {

        System.out.println(s);

    }

    @Override
    public void addPlayer(Player player) {
        if (gameState == GameState.YET_TO_BEGIN) {

            if (allPlayers.contains(player)) return;

            allPlayers.add(player);

            for (Player enemy : allPlayers) {
                if (!player.equals(enemy)) {
                    player.addEnemy(enemy);
                }
            }

            ServerSetup serverSetup = (ServerSetup) gameManager.getWindowController(ScreenFramework.SERVER_SETUP_ID);

            serverSetup.updatePlayers();

            if (allPlayers.size() == 2 && isHosting()) {

                System.out.println(serverSetup); //is null for some reason

                serverSetup.setGameCanBegin(true);
            }
        }
    }

    public void beginNetworkGame() {

        server.startGame();

        startGame();

    }

    public void startGame() {

        gameState = GameState.TURN;

        updateScreen();

    }

    public void addAllPlayers(List<Player> players) {
        for (Player player : players) {
            addPlayer(player);
        }
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public void joinGame(String ip) {

        client = new Client(this, ip);

    }

    public void closeClient() {

        client = null;
        client.closeConnection();

    }

    public boolean isHosting() {

        return hosting;

    }

    public void removePlayer(Player player) {

        if (gameState == GameState.YET_TO_BEGIN) {
            allPlayers.remove(player);

            ServerSetup serverSetup = (ServerSetup) gameManager.getWindowController(ScreenFramework.SERVER_SETUP_ID);

            serverSetup.setGameCanBegin(false);
            serverSetup.updatePlayers();

        } else if (gameState != GameState.GAME_OVER) {
            registerDefeat();
        } else {
            //nothing
        }

    }
}
