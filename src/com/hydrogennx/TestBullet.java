package com.hydrogennx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * A bullet created by the MainMenu AttackSequence.
 * It moves in a random direction at a slow speed.
 */
public class TestBullet extends Bullet {

    public TestBullet() {

        System.out.println("Creating a new test bullet.");

        ImageView imageView = new ImageView();

        imageView.setImage(new Image("file:res/heart.png"));

        Random random = new Random();

        imageView.setX(random.nextInt(100));
        imageView.setY(random.nextInt(100));

        this.getChildren().add(imageView);

    }

}
