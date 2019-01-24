package com.hydrogennx.core.network;

import com.hydrogennx.core.NetworkGameInstance;
import com.hydrogennx.core.Player;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends NetworkThread {

    private ServerStatus serverStatus = ServerStatus.UNDETERMINED;

    private Socket socketClient;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String ip;

    public Client(NetworkGameInstance gameInstance, String ip) {

        super(gameInstance);

        this.ip = ip;

        System.out.println("Starting client thread");

        start();

    }

    @Override
    public void run() {

        try {
            socketClient = new Socket(ip, Protocol.PORT);
            serverStatus = ServerStatus.ONLINE;

            System.out.println("Connected");

            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());

            System.out.println("Input and OutputStreams initialized");

            joinGame();

            while (true) {
                listen();
            }

        } catch (IOException e) {
            e.printStackTrace();
            serverStatus = ServerStatus.INVALID;
            System.out.println("Invalid Connection");
            closeConnection();
        }

    }

    private void listen() {

        try {

            Protocol messageType = (Protocol) in.readObject();

            gameInstance.networkLog("Recieved a request from " + socketClient.getRemoteSocketAddress());

            switch (messageType) {

                case START_GAME:
                    Platform.runLater(() -> gameInstance.startGame());
                    break;
                case CANCEL_ATTACK_CONFIRMED:
                    break;
                case SEND_ATTACK:
                    break;
                case CANCEL_ATTACK:
                    break;

            }

        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public ServerStatus serverStatus() {

        return serverStatus;

    }

    public void joinGame() {

        try {
            out.writeObject(Protocol.JOIN_GAME);
            List<Player> players = (List<Player>) in.readObject();
            out.writeObject(gameInstance.getCurrentPlayer());
            Platform.runLater(() -> gameInstance.addAllPlayers(players));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void closeConnection() {

        try {
            out.writeObject(Protocol.END_CONNECTION);
            out.writeObject(gameInstance.getCurrentPlayer());
            socketClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
