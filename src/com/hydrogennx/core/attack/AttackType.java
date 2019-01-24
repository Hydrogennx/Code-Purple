package com.hydrogennx.core.attack;

/**
 * Enum used to declare over the network what type of attack is being used.
 */
public enum AttackType {

    AXIS_ATTACK(false), RAIN_ATTACK(false), HEAL_DEFENSE(true);

    boolean defensive;

    AttackType(boolean defensive) {
        this.defensive = defensive;
    }

}
