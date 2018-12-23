package com.hydrogennx.core;

/**
 * An attack status effect.
 * If added to an attack sequence, the player has these affects added the entire game.
 * Where we're going to define what they actually do... is a good question.
 */
public enum AttackStatusEffect {

    //User takes constant damage.
    CONSTANT_DAMAGE,

    //All attacks do more damage than usual.
    AMPLIFIED_DAMAGE,

    //Must wait longer before the attack ends. May not be feasible.
    //LONGER_WAIT,

    //Slows down the player's acceleration but does not affect top speed.
    SLIPPERY_MOVES,

    //Good effect, clears any status the opponent may have had in the past.
    CLEAR_STATUS,

    //Good effect, reverses the player's damage upon touching the enemy.
    HEAL;

}
