package com.hydrogennx.core.network;

import com.hydrogennx.core.NetworkGameInstance;
import com.hydrogennx.core.Player;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

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

        } catch (IOException e) {
            e.printStackTrace();
            serverStatus = ServerStatus.INVALID;
            System.out.println("Invalid Connection");
            closeConnection();
        }

    }

    public ServerStatus serverStatus() {

        return serverStatus;

    }

    public void joinGame() {

        try {
            out.writeObject(Protocol.JOIN_GAME);
            List<Player> players = (List<Player>) in.readObject();
            Platform.runLater(() -> gameInstance.addAllPlayers(players));
            out.writeObject(gameInstance.getCurrentPlayer());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void closeConnection() {

        try {
            out.writeObject(Protocol.END_CONNECTION);
            socketClient.close();
            gameInstance.closeClient();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
