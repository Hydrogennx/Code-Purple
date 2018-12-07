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

        setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("Enter Pressed");
                }
            }
        });

    }

}
