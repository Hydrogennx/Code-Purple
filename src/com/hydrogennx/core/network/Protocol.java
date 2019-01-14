package com.hydrogennx.core.network;

/**
 * Defines what information is being sent over the network to the other party.
 */
public enum Protocol {

    JOIN_GAME, START_GAME, SEND_ATTACK, CANCEL_ATTACK, CANCEL_ATTACK_CONFIRMED, UPDATE, END_CONNECTION;

    static final int PORT = 24077;

}
