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

        //TODO create and use a bullet image rather than a heart image
        imageView.setImage(new Image("file:res/heart.png"));

        Random random = new Random();

        //TODO have the test bullet initialized with context awareness
        //TODO set the test bullet to be in a random location ON THE TOP OF THE BOARD, rather than some corner of it
        imageView.setX(random.nextInt(100));
        imageView.setY(random.nextInt(100));

        this.getChildren().add(imageView);

    }

}
