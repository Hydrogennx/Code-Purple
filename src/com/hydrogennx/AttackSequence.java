package com.hydrogennx;

import com.hydrogennx.javafx.ActionPhase;

import java.util.ArrayList;
import java.util.List;

/**
 * An attack sequence. A list of these are sent to the defender, even though that list usually only contains one item.
 * Each one has an attack type and its modifiers, which as of right now only includes status effects on contact.
 */
public abstract class AttackSequence {

    AttackType attackType;

    List<AttackStatusEffect> modifiers;

    public AttackSequence(AttackType attackType) {
        this.attackType = attackType;
        this.modifiers = new ArrayList<>();
    }

    /**
     * A generic method run at the beginning of an attack sequence.
     * Ideally, this is used to create the initial wave of objects.
     */
    public abstract void startAttack(ActionPhase actionPhase);

    /**
     * A generic method run 60 times a second.
     * Updates the attack sequence and all the objects referenced by it.
     * @param time The time since the game began in seconds
     */
    public abstract void update(double time);
}
