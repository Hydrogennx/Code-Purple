package com.hydrogennx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * A bullet created by the MainMenu AttackSequence.
 * It moves in a random direction at a slow speed.
 */
public class DiamondBullet extends SpriteBullet {

    public DiamondBullet(GameActionPane context, AttackSequence source) {

        super(context, source, 0.2);

        yVelocity = -5;

        //TODO create a dictionary for image ids rather than hard-coding them.
        sprite.setImage(new Image("file:res/falling-bullet.png"));

        Random random = new Random();

        sprite.setX(random.nextInt((int) context.getWidth()));
        sprite.setY(0);

    }

    @Override
    public void update(double time) {

        super.update(time);

        yVelocity += 0.5;
        yVelocity /= 1.05;

        if (sprite.getY() > context.getHeight()) {

            context.destroyBullet(this);

        }

    }
}
