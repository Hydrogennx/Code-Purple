package com.hydrogennx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Phase {

    JPanel mainPanel;

    private JButton practiceButton;
    private JButton quitButton;

    private GameStateManager gameStateManager;

    public MainMenu(GameStateManager gameStateManager) {

        this.gameStateManager = gameStateManager;

        quitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               gameStateManager.changeGameState(GameState.DONE);
           }
        });

        practiceButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               gameStateManager.changeGameState(GameState.TURN);
           }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
