package com.hydrogennx;

import javax.swing.*;

public class ActionPhase extends Phase {

    final static int TARGET_FRAMES_PER_SECOND = 60;
    final static int TARGET_NANOSECONDS_PER_FRAME = 1000000000 / TARGET_FRAMES_PER_SECOND;

    GameStateManager gameStateManager;
    private JPanel mainPanel;
    private JPanel innerPanel;

    public ActionPhase(GameStateManager gameStateManager, Attack attackToDefendAgainst) {

        this.gameStateManager = gameStateManager;
        play();

    }

    //Method borrowed from Stack Overflow: https://stackoverflow.com/questions/18283199/java-main-game-loop
    public void play() {

        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;

        while (true) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double difference = updateLength / ((double)TARGET_NANOSECONDS_PER_FRAME);

            lastFpsTime += updateLength;
            if(lastFpsTime >= 1000000000) {
                lastFpsTime = 0;
            }

            if (difference < updateLength) {
                gameTick(difference);
            }

            //TODO implement graphics
            //graphicsHandler.paintImmediately();

            try {
                long gameTime = (lastLoopTime + (TARGET_NANOSECONDS_PER_FRAME - System.nanoTime())) / 1000000;
                System.out.println(gameTime);
                Thread.sleep(gameTime);
            } catch (Exception e) {
                //TODO don't do pokemon exception handling
            }

            //TODO allow exiting the minigame instance
        }

    }

    public void gameTick(double difference) {
        //TODO make the game do something
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
