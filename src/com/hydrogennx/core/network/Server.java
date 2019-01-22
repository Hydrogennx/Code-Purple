package com.hydrogennx.core.network;

import com.hydrogennx.core.Player;
import com.hydrogennx.core.attack.AttackSequence;

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

    private boolean serverRunning;

    public Server() {

        start();

    }

    @Override
    public void run() {

        try {
            socketServer = new ServerSocket(Protocol.PORT);

            System.out.println("Host started");

            while (serverRunning) {
                listen();
            }

            socketServer.close();

        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void listen() {

        try (Socket server = socketServer.accept()) {

            gameInstance.networkLog("Recieved a request from " + server.getRemoteSocketAddress());

            in = new ObjectInputStream(server.getInputStream());
            out = new ObjectOutputStream(server.getOutputStream());

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
                        updateGameState();
                        break;
                    case END_CONNECTION:
                        registerLeaving();
                        break infoLoop;

                }

            }

        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void registerLeaving() {

        //

    }

    private void updateGameState() throws IOException, ClassNotFoundException {

        Player player = (Player) in.readObject();

        gameInstance.updatePlayerState(player);

    }

    private void registerAttack() throws IOException, ClassNotFoundException {

        List<AttackSequence> attackSequences = (List<AttackSequence>) in.readObject();
        Player attacker = (Player) in.readObject();

        gameInstance.queueAttack(attackSequences, attacker);

    }

    private void unregisterAttack() throws IOException, ClassNotFoundException {

        List<AttackSequence> attackSequences = (List<AttackSequence>) in.readObject();
        Player attacker = (Player) in.readObject();

        gameInstance.recallAttack(attackSequences, attacker);

    }

    private void addPlayer() throws IOException, ClassNotFoundException {

        Player player = (Player) in.readObject();

        gameInstance.addPlayer(player);

    }

    public void closeConnection() {

        serverRunning = false;

    }
}
