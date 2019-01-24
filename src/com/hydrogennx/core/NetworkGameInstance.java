package com.hydrogennx.core;

import com.hydrogennx.controller.ActionPhase;
import com.hydrogennx.controller.ServerSetup;
import com.hydrogennx.controller.TurnPhase;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.javafx.ScreenFramework;
import com.hydrogennx.core.network.Client;
import com.hydrogennx.core.network.Server;
import javafx.application.Platform;

import java.util.*;

/**
 * Controls the server side of a two-player network session.
 */
public class NetworkGameInstance extends GameInstance {

    Player mainPlayer;

    Client client;
    Server server;

    boolean hosting;

    List<Player> serversidePlayerList = new ArrayList<>();

    Map<Player, List<AttackSequence>> queuedAttacks = new HashMap<>();

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

    public void queueAttackLocally(Player player, List<AttackSequence> attackSequences) {

        queuedAttacks.put(player, attackSequences);

        if (queuedAttacks.size() == 2) {

            changeGameState(GameState.ACTION);

            ActionPhase actionPhase = (ActionPhase) gameManager.getWindowController(ScreenFramework.ACTION_PHASE_ID);

            for (Player attacker : getAllPlayers()) {

                if (mainPlayer.isEnemyOf(attacker)) {
                    actionPhase.addAttackSequences(queuedAttacks.get(attacker));
                }

            }

            queuedAttacks.clear();

        }

    }

    @Override
    public void queueAttack(Player player, List<AttackSequence> attackSequences) {

        if (!isHosting()) {
            client.sendAttack(attackSequences, player);
        } else {
            server.sendAttack(attackSequences, player);
        }

        queueAttackLocally(player, attackSequences);

    }

    @Override
    public void recallAttack(Player attacker) {

        queuedAttacks.remove(attacker);

    }

    @Override
    public void updatePlayerState(Player playerToUpdate) {

        for (Player player : allPlayers) {

            if (player.getName().equals(playerToUpdate.getName())) {

                player.update(playerToUpdate);

            }

        }

    }


    @Override
    public void endAttack() {

        if (isHosting()) {
            server.sendUpdate(mainPlayer);
        } else {
            client.sendUpdate(mainPlayer);
        }

        System.out.println("Update sent.");

        changeGameState(GameState.TURN);

        TurnPhase turnPhase = (TurnPhase) gameManager.getWindowController(ScreenFramework.TURN_PHASE_ID);
        turnPhase.updateState();

    }

    @Override
    public void registerDefeat() {

        if (isHosting()) {
            server.sendUpdate(mainPlayer);
        } else {
            client.sendUpdate(mainPlayer);
        }

        System.out.println("Update sent.");

        changeGameState(GameState.GAME_OVER);

    }

    @Override
    public void endGame() {

        super.endGame();

        gameManager.stopGame();

        if (isHosting()) {
            server.closeConnection();
        } else if (client != null) {
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

                serverSetup.setGameCanBegin(true);
            }
        }
    }

    public void beginNetworkGame() {

        server.startGame();

        startGame();

    }

    public void startGame() {

        int startingMana = 1 + new Random().nextInt(5);

        if (isHosting()) {
            for (Player player : allPlayers) {

                player.setStartingMana(startingMana);

                server.sendUpdate(player);

            }
        }

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

    public Player getOtherPlayer() {

        for (Player player: allPlayers) {
            if (mainPlayer.equals(player)) continue;
            else return player;
        }

        return null;

    }
}
