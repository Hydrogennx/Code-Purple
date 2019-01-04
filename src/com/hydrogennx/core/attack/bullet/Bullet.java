package com.hydrogennx.core.attack.bullet;

import com.hydrogennx.core.Location;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.GameActionPane;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 * Any bullet that appears on the screen during the ActionPhase.
 * Make sure all bullets extend Bullet, not Group!
 */
public abstract class Bullet extends Group {

    double damage;

    GameActionPane context;
    AttackSequence source;

    Location location;

    ImageView sprite = new ImageView();

    public Bullet(GameActionPane context, AttackSequence source, Location location, double damage) {

        this.context = context;
        this.source = source;

        this.location = location;

        this.damage = damage;

        this.getChildren().add(sprite);

    }

    public abstract void update(double time);

}
