package com.hydrogennx;

import javax.swing.*;

/**
 * The top-level class. This creates and destroys content panes, the various game managers, and the like.
 */
public class FirstJump {

    GameContext gameContext;
    GameState gameState;

    GameInstance gameInstance;

    JFrame gameWindow = new JFrame("First Jump");

    public static void main(String[] args) {

        System.out.println("Hello World!");

        FirstJump gameInstance = new FirstJump();

        gameInstance.startLoop();

    }

    public FirstJump() {

        gameState = GameState.MENU;
        gameContext = GameContext.INACTIVE;

        gameInstance = null;

        updateGameWindow();

        System.out.println("Game loaded.");

    }

    /**
     * Creates and returns a content pane based on the current game state.
     * @return A content pane based on the current game state.
     */
    private JPanel getContentPane() {
        switch (gameState) {
            case MENU:
                return new MainMenu(this).getMainPanel();
            case TURN:
                return new TurnPhase(gameInstance).getMainPanel();
            case ACTION:
                return new ActionPhase(gameInstance).getMainPanel();
            default:
                return null;
        }
    }

    /**
     * Removes the current game window, and opens a new one according to the current game state.
     * This method should only be called when the game state changes from one to another.
     */
    private void updateGameWindow() {

        if (gameState == GameState.DONE) {
            gameWindow.dispose();
        } else {
            gameWindow.setContentPane(getContentPane());
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.pack();
            gameWindow.setVisible(true);
        }

    }

    private void startLoop() {
        //TODO something should be here, probably
    }

    public void startLocalPractice() {
        gameInstance = new LocalPracticeManager(this);
        changeGameState(GameState.TURN);
    }

    /**
     * Changes the game state from one content pane to another. Used for drastic changes in what is drawn to the screen.
     * @param gameState
     */
    public void changeGameState(GameState gameState) {
        if (this.gameState != gameState) {
            this.gameState = gameState;
            updateGameWindow();
        }
    }
}
