package com.hydrogennx.core.attack;

import com.hydrogennx.core.Direction;
import com.hydrogennx.core.GameActionPane;
import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.bullet.DiamondBullet;
import com.hydrogennx.core.attack.bullet.Rocket;

import java.util.Random;

public class RocketAttack extends AttackSequence {

    public static final double RAIN_SPEED = 2; //5 pixels per frame

    double lastAttackTime;
    int numAttacks;

    Direction direction;

    /**
     * Create a new rocket attack, in which bullets chase the player for way too long.
     */
    public RocketAttack() {

        super(2);

    }

    @Override
    public void startAttack(GameActionPane context, double time) {

        super.startAttack(context, time);

        this.lastAttackTime = attackStartTime;

    }

    @Override
    public boolean attackStep(double time) {

        if (time - lastAttackTime > 1.2 && numAttacks < 3) {

            Random random = new Random();

            Location location;
            Velocity velocity;

            double x = context.getWidth() * random.nextDouble();
            double y = context.getHeight();

            location = new Location(x, y);

            velocity = new Velocity(direction, RAIN_SPEED);

            Rocket testBullet = new Rocket(context, this, location, velocity);

            context.spawnBullet(testBullet);

            lastAttackTime = time;

            numAttacks++;

        }

        return true;

    }

}
