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

            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println("Enter Pressed");
            }


        });

        requestFocus();

    }

    /**
     * Resets this controllableCharacter to be indistinguishable from a newly created one.
     */
    public void reset() {


    }
}
