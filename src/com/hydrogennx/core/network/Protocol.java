package com.hydrogennx.core.network;

/**
 * Defines what information is being sent over the network to the other party.
 */
public enum Protocol {

    JOIN_GAME, SEND_ATTACK, CANCEL_ATTACK, UPDATE, END_CONNECTION,
    REGISTER_PLAYER, START_GAME, CANCEL_ATTACK_CONFIRMED;

    static final int PORT = 24077;

}
