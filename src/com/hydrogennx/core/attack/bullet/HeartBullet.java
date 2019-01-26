package com.hydrogennx.core.attack.bullet;

import com.hydrogennx.core.GameActionPane;
import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.AttackSequence;
import javafx.scene.image.Image;

public class HeartBullet extends SpriteBullet {

    public HeartBullet(GameActionPane context, AttackSequence source, Location location, Velocity velocity) {
        super(context, source, location, velocity, 0.25);

        //TODO create a dictionary for image ids rather than hard-coding them.
        sprite.setImage(new Image("/com/hydrogennx/core/resource/falling-bullet.png"));

        sprite.setRotate(Math.toDegrees(velocity.getDirection()));
    }

    @Override
    public void update(double time) {

        super.update(time);

        velocity.setSpeed((velocity.getSpeed() + 0.2) / 1.01);

    }

}
