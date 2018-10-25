package com.hydrogennx;

import java.util.ArrayList;
import java.util.List;

/**
 * An attack sequence. A list of these are sent to the defender, even though that list usually only contains one item.
 * Each one has an attack type and its modifiers, which as of right now only includes status effects on contact.
 */
public class AttackSequence {

    AttackType attackType;

    List<AttackStatusEffect> modifiers;

    public AttackSequence(AttackType attackType) {
        this.attackType = attackType;
        this.modifiers = new ArrayList<>();
    }

}
