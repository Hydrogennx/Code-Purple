package com.hydrogennx;

import javax.swing.*;

/**
 * Handles the state of the game, transferring between menus and scenes as necessary.
 * Also handles transferring data between the Action phase and Turn phase, which is
 * probably a little sloppy. Definitely sloppy. We should fix that sometime.
 */
public class GameStateManager {

    GameState gameState;
    GameSetting gameSetting = GameSetting.LOCAL_PRACTICE;
    JFrame gameWindow = new JFrame("First Jump");

    Attack attackToDefendAgainst;

    public static void main(String[] args) {

        System.out.println("Hello World!");

        GameStateManager gameInstance = new GameStateManager();

        gameInstance.startLoop();

    }

    public GameStateManager() {

        gameState = GameState.MENU;

        updateGameWindow();

        System.out.println("Game loaded.");

    }

    private JPanel getContentPane() {
        switch (gameState) {
            case MENU:
                return new MainMenu(this).getMainPanel();
            case TURN:
                return new TurnPhase(this).getMainPanel();
            case ACTION:
                return new ActionPhase(this, attackToDefendAgainst).getMainPanel();
            default:
                return null;
        }
    }

    public void updateGameWindow() {

        if (gameState == GameState.DONE) {
            gameWindow.dispose();
        } else {
            gameWindow.setContentPane(getContentPane());
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.pack();
            gameWindow.setVisible(true);
        }

    }

    public void startLoop() {
        //something should be here, probably
    }

    public void changeGameState(GameState gameState) {
        if (this.gameState != gameState) {
            this.gameState = gameState;
            updateGameWindow();
        }
    }

    public void queueAttack(Attack attack) {
        if (gameSetting == GameSetting.LOCAL_PRACTICE) {
            attackToDefendAgainst = attack;
            changeGameState(GameState.ACTION);
        }
    }
}
