package com.hydrogennx;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Represents any object that appears during the ActionPhase, including bullets and the player.
 */
public abstract class GameEntity extends ImageView {

    protected double xSpeed;
    protected double ySpeed;

}
