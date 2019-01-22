package com.hydrogennx.core.network;

import com.hydrogennx.core.GameInstance;

public abstract class NetworkThread extends Thread {

    protected GameInstance gameInstance;

    public abstract void closeConnection();

}
