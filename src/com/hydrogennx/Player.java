package com.hydrogennx;

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
     * Contains a list of enemy players that this player must defeat in order to win.
     * In the standard gamemode, this also indicates every player that will be attacked.
     */
    List<Player> enemies = new ArrayList<>();

}
