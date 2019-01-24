package com.hydrogennx.core.attack.bullet;

import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.GameActionPane;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * A bullet that accelerates forwards uniformly.
 * Damage is always 20%.
 */
public class DiamondBullet extends SpriteBullet {

    public DiamondBullet(GameActionPane context, AttackSequence source, Location location, Velocity velocity) {

        super(context, source, location, velocity, 0.15);

        //TODO create a dictionary for image ids rather than hard-coding them.
        sprite.setImage(new Image("/com/hydrogennx/core/resource/tracer-bullet.png"));

        sprite.setRotate(Math.toDegrees(velocity.getDirection()));

    }

    @Override
    public void update(double time) {

        super.update(time);

        velocity.setSpeed((velocity.getSpeed() + 0.2) / 1.05);

    }
}
