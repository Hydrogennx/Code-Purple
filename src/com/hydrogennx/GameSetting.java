package com.hydrogennx;

public enum GameSetting {

    //A game is not currently going on.
    INACTIVE,

    //The game is a local practice session, and attacks are immediately executed against the defender.
    LOCAL_PRACTICE,

    //The game is hosted on this computer and must receive network requests and handle gameplay.
    HOST,

    //The game is hosted on another computer, so it must send network requests and handle gameplay.
    CLIENT;

}
