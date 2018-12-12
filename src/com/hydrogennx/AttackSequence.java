package com.hydrogennx;

import java.util.ArrayList;
import java.util.List;

/**
 * An attack sequence. A list of these are sent to the defender, even though that list usually only contains one item.
 * Each one has an attack type and its modifiers, which as of right now only includes status effects on contact.
 */
public abstract class AttackSequence {

    public static final double ATTACK_LENGTH = 10.0; //all attacks default to 10 seconds

    protected boolean timed = true; //Timed attacks end after a certain amount of time has passed.
    protected boolean attackOngoing = true;

    protected double attackStartTime = 0;

    protected List<AttackStatusEffect> modifiers;
    protected GameActionPane context;

    public AttackSequence() {
        this.modifiers = new ArrayList<>();
    }

    /**
     * A generic method run at the beginning of an attack sequence.
     * Ideally, this is used to create the initial wave of objects.
     */
    public void startAttack(GameActionPane context, double time) {

        this.context = context;
        this.attackStartTime = time;

    }

    /**
     * This is the method overwritten by any given AttackSequence.
     * @param time The time of the attack since the start of the game.
     * @return False, if the attack is over and there is no longer anything to update.
     */
    public abstract boolean attackStep(double time);

    /**
     * This is the method run by ActionPhase 60 times a second.
     * Updates the attack sequence and all the objects referenced by it.
     * @param time The time since the game began in seconds
     * @return False, if the attack is over and there is no longer anything to update.
     */
    public boolean update(double time) {

        if (timed && time - attackStartTime > ATTACK_LENGTH) {
            attackOngoing = false;
        }

        if (!attackOngoing) return false; //attack was ended, nothing left to do.

        attackOngoing = attackStep(time); //do the attack as defined

        return attackOngoing;

    }

    public boolean isOngoing() {
        return attackOngoing;
    }
}
