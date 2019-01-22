package com.hydrogennx.core;

import java.io.Serializable;

public class Settings implements Serializable {

    String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
