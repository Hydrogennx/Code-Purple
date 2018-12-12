package com.hydrogennx;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * A pane that can only hold bullets and the main character.
 * Contains helpful methods for providing game context and actions for those objects.
 */
public class GameActionPane extends Pane {

    List<Bullet> bulletsToRemove = new ArrayList<>();

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

        }

        removeDeletedBullets();

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
}
