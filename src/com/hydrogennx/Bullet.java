package com.hydrogennx;

import com.hydrogennx.javafx.ActionPhase;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Any bullet that appears on the screen during the ActionPhase.
 * Make sure all bullets extend Bullet, not Group!
 */
public abstract class Bullet extends Group {

    double damage;

    GameActionPane context;
    AttackSequence source;

    ImageView sprite = new ImageView();

    public Bullet(GameActionPane context, AttackSequence source, double damage) {

        this.context = context;
        this.source = source;

        this.damage = damage;

        this.getChildren().add(sprite);

    }

    public abstract void update(double time);

}
