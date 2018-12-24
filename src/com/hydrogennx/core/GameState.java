package com.hydrogennx.core;

/**
 * Indicates what the player is looking at.
 */
public enum GameState {

    //Indicates that the player is in the main menu.
    MENU,

    //Indicates that the player is in the tutorial
    TUTORIAL,

    //Indicates that the player is deciding what move to make.
    TURN,

    //Indicates that the player is executing their turn.
    ACTION,

    //Game is over, you are looking at stats and chatting with the other player
    GAME_OVER
}
