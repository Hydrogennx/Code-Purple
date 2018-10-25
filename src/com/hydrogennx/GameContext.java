package com.hydrogennx;

/**
 * Defines the context under which the game is played.
 * When a move is decided, it makes a difference whether the player is host or client in how the game must respond.
 * Additionally, local practice games act entirely differently, in that the player receives their own attacks.
 */
public enum GameContext {

    //A game is not currently going on.
    INACTIVE,

    //The game is a local practice session, and attacks are immediately executed against the defender.
    LOCAL_PRACTICE,

    //The game is hosted on this computer and must receive network requests and handle gameplay.
    HOST,

    //The game is hosted on another computer, so it must send network requests and handle gameplay.
    CLIENT;

}
