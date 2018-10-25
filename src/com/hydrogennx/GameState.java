package com.hydrogennx;

/**
 * Indicates what the player is looking at.
 */
public enum GameState {

    //Indicates that the player is in the main menu.
    MENU,

    //Indicates that the player is deciding what move to make.
    TURN,

    //Indicates that the player is executing their turn.
    ACTION,

    //Indicates that the window has been closed, and cleanup is (or should be) in progress.
    DONE;

}
