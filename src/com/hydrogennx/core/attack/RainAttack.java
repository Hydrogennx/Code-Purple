package com.hydrogennx.core.attack;

import com.hydrogennx.core.GameActionPane;
import com.hydrogennx.core.attack.bullet.DiamondBullet;

public class RainAttack extends AttackSequence {

    double lastAttackTime;
    int numAttacks;

    @Override
    public void startAttack(GameActionPane context, double time) {

        super.startAttack(context, time);

        this.lastAttackTime = attackStartTime;

    }

    @Override
    public boolean attackStep(double time) {

        if (time - lastAttackTime > 0.1 && numAttacks < 80) {

            DiamondBullet testBullet = new DiamondBullet(context, this);

            context.spawnBullet(testBullet);

            lastAttackTime = time;

            numAttacks++;

        }

        return true;

    }

}
