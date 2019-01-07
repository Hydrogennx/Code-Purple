package com.hydrogennx.core.attack.bullet;

import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.GameActionPane;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * A healing projectile that slows down from where it spawns.
 * Damage is always -25%.
 */
public class HealthPack extends SpriteBullet {

    public HealthPack(GameActionPane context, AttackSequence source, Location location, Velocity velocity) {

        super(context, source, location, velocity, -0.25);

        //TODO create a dictionary for image ids rather than hard-coding them.
        sprite.setImage(new Image("/com/hydrogennx/core/resource/purple-ready.png"));

        sprite.setRotate(Math.toDegrees(velocity.getDirection()));

    }

    @Override
    public void update(double time) {

        super.update(time);

        velocity.setSpeed((velocity.getSpeed()) / 1.05);

    }
}
