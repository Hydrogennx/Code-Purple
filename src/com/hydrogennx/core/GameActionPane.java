package com.hydrogennx.core;

import com.hydrogennx.core.attack.bullet.Bullet;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A pane that can only hold bullets and the main character.
 * Contains helpful methods for providing game context and actions for those objects.
 */
public class GameActionPane extends Pane {

    List<Bullet> bulletsToRemove = new ArrayList<>();

    ControllableCharacter controllableCharacter;

    Random random = new Random();

    final double SHAKE_TOTAL_TIME = 120;
    double shakeIntensity; // intensity of the shake
    double shakeTime; //what amount of time until the shake is over

    public void spawnBullet(Bullet bullet) {

        getChildren().add(bullet);

    }

    public void destroyBullet(Bullet bullet) {

        bulletsToRemove.add(bullet);

    }


    public void update(double time) {

        for (Node n : getChildren()) {

            if (n instanceof Bullet) {
                Bullet bullet = (Bullet) n;

                bullet.update(time);
            }

            if (shakeTime > 0) {
                double xChange = random.nextDouble() * shakeIntensity * shakeTime / SHAKE_TOTAL_TIME;
                double yChange = random.nextDouble() * shakeIntensity * shakeTime / SHAKE_TOTAL_TIME;
                shakeTime--;
                //shakeIntensity;

                System.out.println(xChange);
                System.out.println(yChange);
                System.out.println(shakeTime);

                setTranslateX(xChange);
                setTranslateY(yChange);

            }

        }

        removeDeletedBullets();

    }

    public void setShake(double damage) {

        shakeIntensity = 2 + 14 * damage; //TODO change this equation so that shaking is more consistent, ie small shakes are felt and large ones don't kill your computer
        shakeTime = SHAKE_TOTAL_TIME;

        //1% damage: 2 shake for 1 second
        //100% damage: 14 shake for 1 second

    }

    /**
     * Removes bullets queued for deletion.
     */
    private void removeDeletedBullets() {

        for (Bullet bullet : bulletsToRemove) {

            getChildren().remove(bullet);

        }

        bulletsToRemove.clear();

    }

    /**
     * Resets the game pane to be indistinguishable from a newly created one.
     */
    public void reset() {

        for (Node child : getChildren()) {
            if (child instanceof Bullet) {
                bulletsToRemove.add((Bullet) child);
            }
        }

        removeDeletedBullets();

    }

    public void setCharacter(ControllableCharacter controllableCharacter) {

        this.controllableCharacter = controllableCharacter;
        controllableCharacter.setContext(this);

    }

    public ControllableCharacter getCharacter() {

        return controllableCharacter;

    }
}
