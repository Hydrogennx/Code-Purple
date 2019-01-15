package com.hydrogennx.core.attack;

import com.hydrogennx.core.GameActionPane;
import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.bullet.DiamondBullet;

import java.util.Random;

public class AxisAttack extends AttackSequence {

    static final double INITIAL_DISTANCE = 200;

    double lastAttackTime;
    int numAttacks;

    public AxisAttack() {
        super(2);
    }

    @Override
    public void startAttack(GameActionPane context, double time) {

        super.startAttack(context, time);

        this.lastAttackTime = attackStartTime;

    }

    @Override
    public boolean attackStep(double time) {

        if (time - lastAttackTime > 0.3 && numAttacks < 80) {

            Location playerLocation = context.getCharacter().getLocation();

            double direction = new Random().nextDouble() * 360;

            Location location = new Location(
                    playerLocation.getActualX() + Math.cos( Math.toRadians(direction) ) * INITIAL_DISTANCE,
                    playerLocation.getActualY() + Math.sin( Math.toRadians(direction) ) * INITIAL_DISTANCE);
            Velocity velocity = new Velocity(direction + 180, 0.001);

            DiamondBullet testBullet = new DiamondBullet(context, this, location, velocity);

            context.spawnBullet(testBullet);

            lastAttackTime = time;

            numAttacks++;

        }

        return true;

    }

}
