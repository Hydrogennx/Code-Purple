package com.hydrogennx;

import javax.swing.*;
import java.awt.*;

public class FirstJump {

    GameState gameState;
    JFrame gameWindow = new JFrame("First Jump");

    public static void main(String[] args) {

        System.out.println("Hello World!");

        FirstJump gameInstance = new FirstJump();

        gameInstance.startLoop();

    }

    public FirstJump() {

        gameState = GameState.MENU;

        updateGameWindow();

        System.out.println("Game loaded.");

    }

    private JPanel getContentPane() {
        switch (gameState) {
            case MENU:
                return new MainMenu(this).getMainPanel();
            case PRACTICE:
                return new PracticeState(this).getMainPanel();
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
}
