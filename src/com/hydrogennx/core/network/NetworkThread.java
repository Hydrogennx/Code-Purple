package com.hydrogennx.core.network;

import com.hydrogennx.core.NetworkGameInstance;
import com.hydrogennx.core.Player;

public abstract class NetworkThread extends Thread {

    public NetworkGameInstance gameInstance;

    public NetworkThread(NetworkGameInstance gameInstance) {

        this.gameInstance = gameInstance;

    }

    public abstract void closeConnection();

}
