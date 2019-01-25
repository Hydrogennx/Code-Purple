package com.hydrogennx.core;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

//TODO figure out how to add custom JavaFX elements via SceneBuilder.

/**
 * Represents the object controlled by the player during the ActionPhase sessions.
 */
public class ControllableCharacter extends Group {

    MediaPlayer hitMusic;

    final static double FRAMES_TO_FULL_SPEED = 4;
    final static double TOP_SPEED = 16;
    final static double SPEED = TOP_SPEED / FRAMES_TO_FULL_SPEED;
    final static double INVULNERABILITY_FRAMES = 20;

    boolean W;
    boolean A;
    boolean S;
    boolean D;
    Location location;
    Velocity velocity = new Velocity(0,0);

    double invulnerabilityFrames = 0;

    Player controllingPlayer;
    GameActionPane context;

    ImageView sprite;

    public ControllableCharacter() {

        Image imageToUse = new Image("/com/hydrogennx/core/resource/coward.png");
        sprite = new ImageView(imageToUse);
        sprite.setRotate(-45);

        getChildren().add(sprite);

    }

    /**
     * A temporary method that will hard-code the movement of ControllableCharacter to certain keys.
     * Ideally, the player would be able to change keybinds however they please.
     */
    public void setDefaultMovement() {

        setOnKeyPressed(event -> {

            switch (event.getCode()) {
                case W:
                    W = true;
                    break;
                case S:
                    S = true;
                    break;
                case A:
                    A = true;
                    break;
                case D:
                    D = true;
                    break;
            }
            move();

        }); //This switch statement detects when the player pushes one of the control buttons and calls the move method to
            //perform movements

        setOnKeyReleased(event -> {

            switch (event.getCode()) {
                case W:
                    W = false;
                    break;
                case S:
                    S = false;
                    break;
                case A:
                    A = false;
                    break;
                case D:
                    D = false;
                    break;
            }
            move();

        }); //This switch statement detects when the player lets go of a button and stops the character from moving it

        requestFocus();

    }

    public void move() { //This method adjusts the speed and direction the character is moving in according to the player input
        if (W) {
            velocity.setY(-SPEED);
        } else if (S) {
            velocity.setY(SPEED);
        } else {
            velocity.setY(0);
        }
        if (D) {
            velocity.setX(SPEED);
        } else if (A) {
            velocity.setX(-SPEED);
        } else {
            velocity.setX(0);
        }
    }

    public void update(double time) {

        location.addVelocity(velocity);

        setLayoutX(location.getActualX());
        setLayoutY(location.getActualY());

        sprite.setOpacity(1 - (invulnerabilityFrames / INVULNERABILITY_FRAMES));

        if (isInvulnerable()) {

            invulnerabilityFrames--;
        }

        keepSpriteInBounds();

    }

    private void keepSpriteInBounds() {

        if (location.getActualX() < 0) {
            location.setActualX(0);
        } else if (location.getActualX() > context.getWidth()) {
            location.setActualX(context.getWidth());
        }

        if (location.getActualY() < 0) {
            location.setActualY(0);
        } else if (location.getActualY() > context.getHeight()) {
            location.setActualY(context.getHeight());
        }

    }

    public void setContext(GameActionPane context) {

        this.context = context;

        location = new Location();

        //location.setActualX(context.getWidth() / 2);
        //location.setActualY(context.getHeight() / 2);
        location.setActualX(getLayoutX());
        location.setActualY(getLayoutY());


    }

    public void setControllingPlayer(Player controllingPlayer) {

        this.controllingPlayer = controllingPlayer;

    }

    /**
     * Resets this controllableCharacter to be indistinguishable from a newly created one.
     */
    public void reset() {

        W = false;
        A = false;
        S = false;
        D = false;

        move();

        location.setActualX(context.getWidth() / 2);
        location.setActualY(context.getHeight() / 2);

    }

    public Player getPlayer() {

        return controllingPlayer;

    }

    public boolean isInvulnerable() {

        return invulnerabilityFrames > 0;

    }

    public void registerHit(double damage) {

        if (damage > 0 && isInvulnerable()) return;

        getPlayer().registerDamage(damage);

        if (damage > 0) { //deals damage as opposed to healing

            String hitMusicFile = getClass().getResource("/hit.mp3").toString();
            Media hit = new Media(hitMusicFile);

            hitMusic = new MediaPlayer(hit);
            hitMusic.play();

            context.setShake(damage);

            invulnerabilityFrames = INVULNERABILITY_FRAMES;
        }


    }

    public boolean isDead() {


        return getPlayer().getHealth() <= 0;

    }

    public Location getLocation() {
        return location;
    }

}