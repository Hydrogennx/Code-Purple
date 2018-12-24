package com.hydrogennx.core.attack.bullet;

import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.GameActionPane;

public abstract class SpriteBullet extends Bullet {

    double xVelocity;
    double yVelocity;

    public SpriteBullet(GameActionPane context, AttackSequence source, double damage) {
        super(context, source, damage);
    }

    @Override
    public void update(double time) {

        sprite.setY(sprite.getY() + yVelocity);
        sprite.setX(sprite.getX() + xVelocity);

        if (collidingWithPlayer()) {

            context.destroyBullet(this);
            context.getCharacter().registerHit(damage);

        }

    }

    protected boolean collidingWithPlayer() {

        return getBoundsInParent().intersects(context.getCharacter().getBoundsInParent());

    }

}
