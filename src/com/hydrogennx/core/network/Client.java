package com.hydrogennx.core.network;

import com.hydrogennx.core.GameManager;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

public class Client extends NetworkThread {

    private ServerStatus serverStatus = ServerStatus.UNDETERMINED;

    private Socket socketClient;

    private String ip;

    public Client(String ip) {

        this.ip = ip;

        start();

    }

    @Override
    public void run() {

        try {
            socketClient = new Socket(ip, Protocol.PORT);
            serverStatus = ServerStatus.CONNECTED;
        } catch (IOException e) {
            e.printStackTrace();
            serverStatus = ServerStatus.INVALID;
        }

    }

    public ServerStatus serverStatus() {

        return serverStatus;

    }

    public void joinGame() {

        //

    }

    @Override
    public void closeConnection() {

        //

    }
}
