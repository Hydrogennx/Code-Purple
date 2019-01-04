package com.hydrogennx.core;

/**
 * A custom location class designed to remove ambiguity when defining where bullets and the player should be.
 * (0,0) is defined as the center of the playable area, with an increase of 1 indicating a single pixel.
 */
public class Location {

    /**
     * The actual pixel y value on the screen, with the origin in the top-left corner.
     */
    double x;

    /**
     * The actual pixel y value on the screen, with the origin in the top-left corner.
     */
    double y;

    /**
     * Create a new location, using the actual pixel values for x and y.
     * @param x
     * @param y
     */
    public Location(double x, double y) {

        this.x = x;
        this.y = y;

    }

    public double getActualX() {
        return x;
    }

    public void setActualX(double actualX) {
        this.x = actualX;
    }

    public double getActualY() {
        return y;
    }

    public void setActualY(double actualY) {
        this.y = actualY;
    }

    public void addVelocity(double xVelocity, double yVelocity) {

        x += xVelocity;
        y += yVelocity;

    }

}
