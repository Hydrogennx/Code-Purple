package com.hydrogennx;

import javax.swing.*;
import java.awt.*;

public class PracticeState {

    private JPanel mainPanel;

    private FirstJump gameInstance;

    public PracticeState(FirstJump gameInstance) {

        this.gameInstance = gameInstance;

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
