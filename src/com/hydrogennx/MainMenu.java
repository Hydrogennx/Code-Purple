package com.hydrogennx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main menu. Other significant menus should probably be given their own GUI form.
 * As of right now this only exists to go to practice.
 */
public class MainMenu extends Phase {

    JPanel mainPanel;

    private JButton practiceButton;
    private JButton quitButton;

    private FirstJump firstJump;

    public MainMenu(FirstJump firstJump) {

        this.firstJump = firstJump;

        quitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               firstJump.changeGameState(GameState.DONE);
           }
        });

        practiceButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               firstJump.startLocalPractice();
           }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
