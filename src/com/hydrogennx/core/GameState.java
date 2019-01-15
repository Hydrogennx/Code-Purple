package com.hydrogennx.core;

/**
 * Indicates what the player is looking at.
 */
public enum GameState {

    //Indicates that the player is still setting up the game, and therefore in a menu.
    YET_TO_BEGIN,

    //Indicates that the player is deciding what move to make.
    TURN,

    //Indicates that the player is executing their turn.
    ACTION,

    //Game is over, you are looking at stats and chatting with the other player
    GAME_OVER
}
