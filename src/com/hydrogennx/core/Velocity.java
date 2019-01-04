package com.hydrogennx.core;

public class Velocity {

    double xDirection;
    double yDirection;

    /**
     * Creates a velocity based on a given direction and speed.
     * @param degree
     * @param speed
     */
    public Velocity(double degree, double speed) {

        xDirection = Math.cos( Math.toRadians(degree) ) * speed;
        yDirection = Math.sin( Math.toRadians(degree) ) * speed;

    }

    public Velocity(Direction direction, double speed) {

        xDirection = Math.cos( Math.toRadians( direction.getAngle() ) ) * speed;
        yDirection = Math.sin( Math.toRadians( direction.getAngle() ) ) * speed;

    }

    public double getX() {
        return xDirection;
    }

    public void setX(double xDirection) {
        this.xDirection = xDirection;
    }

    public double getY() {
        return yDirection;
    }

    public void setY(double yDirection) {
        this.yDirection = yDirection;
    }

    public double getDirection() {
        return Math.atan2(yDirection, xDirection);
    }

    public double getSpeed() {
        return Math.sqrt(yDirection * yDirection + xDirection * xDirection);
    }

    public void setSpeed(double speed) {
        double oldSpeed = getSpeed();
        xDirection = xDirection / oldSpeed * speed;
        yDirection = yDirection / oldSpeed * speed;
    }

}
