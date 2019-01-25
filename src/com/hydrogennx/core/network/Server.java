package com.hydrogennx.core.network;

import com.hydrogennx.core.GameManager;
import com.hydrogennx.core.NetworkGameInstance;
import com.hydrogennx.core.Player;
import com.hydrogennx.core.attack.AttackSequence;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends NetworkThread {

    private ServerSocket socketServer;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ServerStatus serverStatus = ServerStatus.UNDETERMINED;

    private boolean serverRunning;

    public Server(NetworkGameInstance gameInstance) {

        super(gameInstance);

        start();

    }

    @Override
    public void run() {

        try {
            socketServer = new ServerSocket(Protocol.PORT);

            System.out.println("Host started");
            serverRunning = true;

            serverStatus = ServerStatus.ONLINE;

            while (serverRunning) {
                listen();
            }

            socketServer.close();

        } catch (IOException e) {
            gameInstance.getGameManager().writeToLogFile(e);
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void listen() {

        try (Socket server = socketServer.accept()) {

            gameInstance.networkLog("Recieved a request from " + server.getRemoteSocketAddress());

            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());

            infoLoop:
            while (serverRunning) {

                Protocol messageType = (Protocol) in.readObject();

                switch (messageType) {

                    case JOIN_GAME:
                        addPlayer();
                        break;
                    case SEND_ATTACK:
                        registerAttack();
                        break;
                    case CANCEL_ATTACK:
                        unregisterAttack();
                        break;
                    case UPDATE:
                        System.out.println("Got a request from the client to update");
                        updatePlayerState();
                        break;
                    case END_CONNECTION:
                        registerLeaving();
                        break infoLoop;

                }

            }

        } catch (IOException | ClassNotFoundException e) {
            gameInstance.getGameManager().writeToLogFile(e);
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void registerLeaving() throws IOException, ClassNotFoundException {

        Player player = (Player) in.readObject();

        Platform.runLater(() -> gameInstance.removePlayer(player));

    }

    public void startGame() {

        try {
            System.out.println("Trying to start the game");
            out.writeObject(Protocol.START_GAME);
            System.out.println("Client has been told to start the game");

        } catch (IOException e) {
            gameInstance.getGameManager().writeToLogFile(e);
            e.printStackTrace();
        }

    }

    private void updatePlayerState() throws IOException, ClassNotFoundException {

        Player player = (Player) in.readObject();
        System.out.println("Got the player");
        double health = in.readDouble();
        System.out.println("Got the health");

        player.setHealth(health);

        System.out.println("Got all information, updating!");

        gameInstance.updatePlayerState(player, health);

    }

    private void registerAttack() throws IOException, ClassNotFoundException {

        Player attacker = (Player) in.readObject();
        List<AttackSequence> attackSequences = (List<AttackSequence>) in.readObject();

        Platform.runLater(() -> gameInstance.queueAttackLocally(attacker, attackSequences));

    }

    private void unregisterAttack() throws IOException, ClassNotFoundException {

        Player attacker = (Player) in.readObject();

        Platform.runLater(() -> gameInstance.recallAttack(attacker));

    }

    private void addPlayer() throws IOException, ClassNotFoundException {

        out.writeObject(gameInstance.getAllPlayers());

        Player player = (Player) in.readObject();

        Platform.runLater(() -> gameInstance.addPlayer(player));

    }

    public void closeConnection() {

        serverRunning = false;

    }

    public void sendAttack(List<AttackSequence> attackSequences, Player player) {

        try {
            out.writeObject(Protocol.SEND_ATTACK);
            out.writeObject(player);
            out.writeObject(attackSequences);
        } catch (IOException e) {
            gameInstance.getGameManager().writeToLogFile(e);
            e.printStackTrace();
        }

    }

    public void sendUpdate(Player player) {

        try {
            out.writeObject(Protocol.UPDATE);
            out.writeObject(player);
            out.writeDouble(player.getHealth());
            System.out.println("Update sent from server");
        } catch (IOException e) {
            gameInstance.getGameManager().writeToLogFile(e);
            e.printStackTrace();
        }

    }
}
