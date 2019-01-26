package com.hydrogennx.core.attack.bullet;

import com.hydrogennx.core.GameActionPane;
import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.AttackSequence;

public class Rocket extends SpriteBullet {

    public Rocket(GameActionPane context, AttackSequence source, Location location, Velocity velocity) {
        super(context, source, location, velocity, 0.25);
    }

    @Override
    public void update(double time) {

        super.update(time);

        velocity.setSpeed((velocity.getSpeed() + 0.2) / 1.05);

        Location playerLocation = context.getCharacter().getLocation();

        double relativeX = playerLocation.getActualX() - location.getActualX();
        double relativeY = playerLocation.getActualY() - location.getActualY();

        double direction = Math.atan2(relativeY, relativeX);

        velocity.setX((velocity.getX() + Math.cos(direction)) / 1.05);
        velocity.setY((velocity.getY() + Math.sin(direction)) / 1.05);

    }

}
