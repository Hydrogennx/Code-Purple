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

    int shake;

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

            if (shake > 0) {
                int xChange = random.nextInt(shake);
                int yChange = random.nextInt(shake);
                shake--;

                System.out.println(xChange);
                System.out.println(yChange);

                setLayoutX(xChange);
                setLayoutY(yChange);

            }

        }

        removeDeletedBullets();

    }

    public void setShake(double shake) {

        this.shake = (int) (50 * shake); //TODO change this equation so that shaking is more consistent, ie small shakes are felt and large ones don't kill your computer

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
