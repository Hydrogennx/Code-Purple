package com.hydrogennx.core;

import com.hydrogennx.controller.ServerSetup;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.network.Client;
import javafx.scene.paint.Color;

import java.util.List;

public class ClientInstance extends GameInstance {

    Client client;

    Player mainPlayer;

    ServerSetup serverSetup;

    public ClientInstance(GameManager gameManager, Client client) {
        super(gameManager);

        this.client = client;

        mainPlayer = new Player(Color.BLUE, "Client");

        allPlayers.add(mainPlayer);

        client.joinGame();

    }

    @Override
    public void updateScreen() {

    }

    @Override
    public void queueAttack(List<AttackSequence> attackSequences, Player attacker) {

    }

    @Override
    public void recallAttack(List<AttackSequence> attackSequences, Player attacker) {

    }

    @Override
    public void updatePlayerState(Player player) {

    }

    @Override
    public void endAttack() {

    }

    @Override
    public void registerDefeat() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    @Override
    public void networkLog(String s) {

    }

    @Override
    public void addPlayer(Player player) {

    }
}
