package com.hydrogennx.core.network;

/**
 * Defines what information is being sent over the network to the other party.
 */
public enum Protocol {

    JOIN_GAME, SEND_ATTACK, CANCEL_ATTACK, UPDATE, END_CONNECTION,
    START_GAME, CANCEL_ATTACK_CONFIRMED;
    //REGISTER_PLAYER -- not used for the 2 player version of this game

    static final int PORT = 24077;

}
