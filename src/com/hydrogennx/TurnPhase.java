package com.hydrogennx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The TurnPhase. This is essentially an AttackSequence builder.
 */
public class TurnPhase extends Phase {

    JPanel mainPanel;
    private JButton funAttackButton;

    GameInstance gameInstance;

    private FirstJump firstJump;

    private ActionPhase actionPhase;

    public TurnPhase(GameInstance gameInstance) {

        this.gameInstance = gameInstance;

        funAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAttack(new AttackSequence(AttackType.TEST));
            }
        });

    }

    private void performAttack(AttackSequence attackSequence) {
        List<AttackSequence> attackSequences = new ArrayList<AttackSequence>();
        attackSequences.add(attackSequence);
        gameInstance.queueAttack(attackSequences);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
