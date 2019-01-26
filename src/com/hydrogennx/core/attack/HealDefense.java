package com.hydrogennx.core.attack;

import com.hydrogennx.core.GameActionPane;
import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.bullet.DiamondBullet;
import com.hydrogennx.core.attack.bullet.HealthPack;

import java.util.Random;

public class HealDefense extends AttackSequence {

    static final double INITIAL_DISTANCE = 300;

    double lastAttackTime;
    int numAttacks;

    public HealDefense() {
        super(1);

        defensive = true;
    }

    @Override
    public void startAttack(GameActionPane context, double time) {

        super.startAttack(context, time);

        this.lastAttackTime = attackStartTime;

    }

    @Override
    public boolean attackStep(double time) {

        if (time - lastAttackTime > 1.2 && numAttacks < 4) {

            Location playerLocation = context.getCharacter().getLocation();

            double direction = new Random().nextDouble() * 360;

            Location location = new Location(
                    playerLocation.getActualX() + Math.cos( Math.toRadians(direction) ) * INITIAL_DISTANCE,
                    playerLocation.getActualY() + Math.sin( Math.toRadians(direction) ) * INITIAL_DISTANCE);
            Velocity velocity = new Velocity(direction, 2.0);

            HealthPack bullet = new HealthPack(context, this, location, velocity);

            context.spawnBullet(bullet);

            lastAttackTime = time;

            numAttacks++;

        }

        return true;

    }

}
