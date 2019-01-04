package com.hydrogennx.core.attack.bullet;

import com.hydrogennx.core.Location;
import com.hydrogennx.core.Velocity;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.GameActionPane;
import javafx.scene.image.Image;

import java.util.Random;

public class SpearBullet extends SpriteBullet {

    static final double START_SPEED = 2;

    public SpearBullet(GameActionPane context, AttackSequence source) {

        super(context, source, null, null, 0.05);

        //TODO create a dictionary for image ids rather than hard-coding them.
        sprite.setImage(new Image("/com/hydrogennx/core/resource/tracer-bullet.png"));

        Random random = new Random();

        double attackAngle = random.nextDouble();
        attackAngle *= Math.PI * 2;

        double startingX = Math.cos(attackAngle) * 100 + context.getWidth()/2;
        double startingY = Math.sin(attackAngle) * 100 + context.getHeight()/2;

        location = new Location(startingX, startingY);

        //double startingX = context.getWidth()/2;
        //double startingY = context.getHeight()/2;

        sprite.setX(startingX);
        sprite.setY(startingY);

        double direction = attackAngle + Math.PI;
        if (direction > Math.PI * 2) {
            direction -= Math.PI * 2;
        }

        sprite.setRotate(direction * 360 / (Math.PI * 2));

        velocity = new Velocity(Math.toDegrees(attackAngle), START_SPEED);

    }

    @Override
    public void update(double time) {

        super.update(time);

    }
}
