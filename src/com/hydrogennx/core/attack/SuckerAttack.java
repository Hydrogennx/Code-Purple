package com.hydrogennx.core.attack;

import com.hydrogennx.core.Direction;
import com.hydrogennx.core.GameActionPane;
import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.bullet.DiamondBullet;
import com.hydrogennx.core.attack.bullet.HeartBullet;

import java.util.Random;

public class SuckerAttack extends AttackSequence {

    public static final double RAIN_SPEED = 4;

    double lastAttackTime;
    int numAttacks;

    Direction direction;

    /**
     * Create a new storm attack, in which bullets fall from one side of the screen to the other.
     * @param direction The direction the bullets are going to fall.
     */
    public SuckerAttack(Direction direction) {

        super(2);

        this.direction = direction;

    }

    @Override
    public void startAttack(GameActionPane context, double time) {

        super.startAttack(context, time);

        this.lastAttackTime = attackStartTime;

    }

    @Override
    public boolean attackStep(double time) {

        if (time - lastAttackTime > 0.15 && numAttacks < 80) {

            Random random = new Random();

            Location location = null;
            Velocity velocity;

            double x = context.getWidth();
            double y = context.getHeight();

            if (direction == Direction.DOWN) {
                x *= random.nextDouble();

                location = new Location(x, y);

            } else if (direction == Direction.UP) {
                x *= random.nextDouble();
                y = 0;

                location = new Location(x, y);

            } else if (direction == Direction.LEFT) {
                y *= random.nextDouble();

                location = new Location(x, y);

            } else if (direction == Direction.RIGHT) {
                x = 0;
                y *= random.nextDouble();

                location = new Location(x, y);

            }

            velocity = new Velocity(direction, RAIN_SPEED);

            HeartBullet testBullet = new HeartBullet(context, this, location, velocity);

            context.spawnBullet(testBullet);

            lastAttackTime = time;

            numAttacks++;

        }

        return true;

    }
}
