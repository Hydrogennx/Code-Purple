package com.hydrogennx.core;

public enum Direction {

    UP(90), DOWN(270), LEFT(180), RIGHT(0);

    int angle;

    Direction(int angle) {
        this.angle = angle;
    }

    public double getAngle() {

        return angle;

    }

    public Direction getOpposite() {
        switch(this) {
            case DOWN:
                return UP;
            case UP:
                return DOWN;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                throw new IllegalArgumentException("There is no opposite for this direction, which makes no sense.");
        }
    }

}
