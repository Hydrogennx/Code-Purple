package com.hydrogennx.core;

import javafx.scene.paint.Color;

public enum PlayerColor {

    RED(Color.RED), BLUE(Color.BLUE), GREEN(Color.GREEN), YELLOW(Color.YELLOW), NEUTRAL(Color.DARKGRAY);

    Color color;

    PlayerColor(Color color) {
        this.color = color;
    }

}
