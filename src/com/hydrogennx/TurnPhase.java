package com.hydrogennx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurnPhase extends Phase {

    JPanel mainPanel;
    private JButton funAttackButton;

    private GameStateManager gameStateManager;

    private ActionPhase actionPhase;

    public TurnPhase(GameStateManager gameStateManager) {

        this.gameStateManager = gameStateManager;

        funAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAttack(new Attack(AttackType.TEST));
            }
        });

    }

    private void performAttack(Attack attack) {
        gameStateManager.queueAttack(attack);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
