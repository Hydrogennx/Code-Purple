package com.hydrogennx;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

//TODO figure out how to add custom JavaFX elements via SceneBuilder.

/**
 * Represents the object controlled by the player during the ActionPhase sessions.
 */
public class ControllableCharacter extends Group {

    public static final double FRAMES_TO_FULL_SPEED = 4;
    public static final double TOP_SPEED = 16;

    public double xSpeed = 0;
    public double ySpeed = 0;

    Player controllingPlayer;
    GameActionPane context;

    ImageView sprite;

    public ControllableCharacter() {

        Image imageToUse = new Image("file:res/coward.png");
        sprite = new ImageView(imageToUse);

        getChildren().add(sprite);

    }

    /**
     * A temporary method that will hard-code the movement of ControllableCharacter to certain keys.
     * Ideally, the player would be able to change keybinds however they please.
     */
    public void setDefaultMovement() {

        System.out.println("Default movement set!");

        setOnKeyPressed(event -> {

            System.out.println("Key pressed!");

            double speed = TOP_SPEED / FRAMES_TO_FULL_SPEED;

            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println("Ow.");
                controllingPlayer.registerDamage(0.1);
            }

            if (event.getCode().equals(KeyCode.W)) {
                ySpeed = -speed;
            }

            if (event.getCode().equals(KeyCode.S)) {
                ySpeed = speed;
            }

            if (event.getCode().equals(KeyCode.A)) {
                xSpeed = -speed;
            }

            if (event.getCode().equals(KeyCode.D) ) {
                xSpeed = speed;
            }


        });

        requestFocus();

    }

    public void update(double time) {

        sprite.setX(sprite.getX() + xSpeed);
        sprite.setY(sprite.getY() + ySpeed);

    }

    public void setContext(GameActionPane context) {

        this.context = context;

    }

    public void setControllingPlayer(Player controllingPlayer) {

        this.controllingPlayer = controllingPlayer;

    }

    /**
     * Resets this controllableCharacter to be indistinguishable from a newly created one.
     */
    public void reset() {

        //nothing needs to be done, you can't even move the character lol

    }
}
