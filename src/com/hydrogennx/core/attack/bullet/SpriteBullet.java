package com.hydrogennx.core.attack.bullet;

import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.GameActionPane;

public abstract class SpriteBullet extends Bullet {

    Velocity velocity;

    public SpriteBullet(GameActionPane context, AttackSequence source, Location location, Velocity velocity, double damage) {
        super(context, source, location, damage);

        this.velocity = velocity;

        sprite.setY(location.getActualY());
        sprite.setX(location.getActualX());

    }

    @Override
    public void update(double time) {

        location.addVelocity(velocity);

        sprite.setY(location.getActualY());
        sprite.setX(location.getActualX());

        if (collidingWithPlayer()) {

            context.destroyBullet(this);
            context.getCharacter().registerHit(damage);

        }

        if (location.getActualX() > context.getWidth() || location.getActualX() < 0) {

            context.destroyBullet(this);

        }

        if (location.getActualY() > context.getHeight() || location.getActualY() < 0) {

            context.destroyBullet(this);

        }

    }

    protected boolean collidingWithPlayer() {

        return getBoundsInParent().intersects(context.getCharacter().getBoundsInParent());

    }

}
