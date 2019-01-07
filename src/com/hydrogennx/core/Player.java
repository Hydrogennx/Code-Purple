package com.hydrogennx.core;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all relevant information pertaining to the player's long term condition.
 * This includes health and status effects, but not details like the player's location or invulnerability frames.
 * This class is in both the TurnPhase and ActionPhase.
 */
public class Player {

    Color color;
    String name;

    double health; //percentage, 0 to 1
    List<AttackStatusEffect> statusEffects = new ArrayList<>();

    /**
     * Contains a list of enemy allPlayers that this player must defeat in order to win.
     * In the standard gamemode, this also indicates every player that will be attacked.
     */
    List<Player> enemies = new ArrayList<>();

    public Player(Color color, String name) {

        this.color = color;
        this.name = name;

        health = 1;
        //statusEffects defaults to an empty arrayList

        //enemies should be empty using this constructor

    }

    /**
     * Adds an enemy to the list of enemies. Should only be run at the start of the game.
     * @param enemy The player to count as an enemy
     */
    public void addEnemy(Player enemy) {

        enemies.add(enemy);

        if (!enemy.isEnemyOf(this)) {
            enemy.addEnemy(this);
        }

    }

    public boolean isEnemyOf(Player enemy) {

        return enemies.contains(enemy);

    }

    public double getHealth() {

        return health;

    }

    public void registerDamage(double damage) {

        this.health -= damage;

        if (this.health > 1) {
            this.health = 1;
        }

    }
}
