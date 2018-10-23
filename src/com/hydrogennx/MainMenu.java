package com.hydrogennx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    private JPanel mainPanel;
    private JButton practiceButton;
    private JButton quitButton;

    private FirstJump gameInstance;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainMenu(FirstJump gameInstance) {

        this.gameInstance = gameInstance;

        quitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               gameInstance.changeGameState(GameState.DONE);
           }
        });

        practiceButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               gameInstance.changeGameState(GameState.PRACTICE);
           }
        });

    }

}
