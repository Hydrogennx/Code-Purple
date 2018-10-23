package com.hydrogennx;

import java.util.ArrayList;
import java.util.List;

public class Attack {

    AttackType attackType;

    List<AttackStatusEffect> modifiers;

    public Attack(AttackType attackType) {
        this.attackType = attackType;
        this.modifiers = new ArrayList<>();
    }

}
