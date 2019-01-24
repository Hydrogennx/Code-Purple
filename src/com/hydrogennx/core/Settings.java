package com.hydrogennx.core;

import java.io.Serializable;

public class Settings implements Serializable {

    String username;

    boolean musicEnabled;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setMusicEnabled(boolean musicEnabled) { this.musicEnabled = musicEnabled; }

    public boolean getMusicEnabled() { return musicEnabled; }

}
