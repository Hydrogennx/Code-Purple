package com.hydrogennx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * A bullet created by the MainMenu AttackSequence.
 * It moves in a random direction at a slow speed.
 */
public class TestBullet extends Bullet {

    double velocity = 0;

    public TestBullet(GameActionPane context, AttackSequence source) {

        super(context, source, 0.2);

        //TODO create a dictionary for image ids rather than hard-coding them.
        sprite.setImage(new Image("file:res/falling-bullet.png"));

        Random random = new Random();

        sprite.setX(random.nextInt((int) context.getWidth()));
        sprite.setY(0);

    }

    @Override
    public void update(double time) {

        velocity += 0.1;
        velocity /= 1.05;

        sprite.setY(sprite.getY() + velocity);

        if (sprite.getY() > context.getHeight()) {

            context.destroyBullet(this);

        }

        if (collidingWithPlayer()) {

            context.destroyBullet(this);
            context.getCharacter().registerHit(damage);

        }

    }

    private boolean collidingWithPlayer() {

        return getBoundsInParent().intersects(context.getCharacter().getBoundsInParent());

    }
}
