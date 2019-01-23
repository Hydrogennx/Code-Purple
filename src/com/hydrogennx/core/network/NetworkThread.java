package com.hydrogennx.core.network;

import com.hydrogennx.core.NetworkGameInstance;

public abstract class NetworkThread extends Thread {

    public NetworkGameInstance gameInstance;

    public NetworkThread(NetworkGameInstance gameInstance) {

        this.gameInstance = gameInstance;

    }

    public abstract void closeConnection();

}
