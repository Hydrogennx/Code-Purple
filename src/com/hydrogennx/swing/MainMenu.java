package com.hydrogennx.swing;

import com.hydrogennx.SwingGameManager;
import com.hydrogennx.GameState;
import com.hydrogennx.Phase;

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

    private SwingGameManager gameManager;

    public MainMenu(SwingGameManager gameManager) {

        this.gameManager = gameManager;

        quitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               gameManager.changeGameState(GameState.DONE);
           }
        });

        practiceButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               gameManager.startLocalPractice();
           }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
