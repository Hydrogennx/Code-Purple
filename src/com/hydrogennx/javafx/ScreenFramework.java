package com.hydrogennx.javafx;

import com.hydrogennx.GameManager;

/**
 * A static helper class. Loads an instance of every screen,
 * and records the indices for each one.
 */
public class ScreenFramework {

    GameManager gameManager;

    public WindowControllerManager graphicsManager = new WindowControllerManager();

    public final String TURN_PHASE_ID = "TURN_PHASE";
    public final String ACTION_PHASE_ID = "ACTION_PHASE";
    public final String MAIN_MENU_ID = "MAIN_MENU";
    public final String TURN_PHASE_FILE = "TurnPhase.fxml";
    public final String ACTION_PHASE_FILE = "ActionPhase.fxml";
    public final String MAIN_MENU_FILE = "MainMenu.fxml";

    public void setGameManager(GameManager gameManager) {
        if (this.gameManager == null) {
            this.gameManager = gameManager;
        }
    }

    public void loadAll() {

        graphicsManager.loadScreen(TURN_PHASE_ID, TURN_PHASE_FILE);
        graphicsManager.loadScreen(ACTION_PHASE_ID, ACTION_PHASE_FILE);
        MainMenu mainMenu = (MainMenu) graphicsManager.loadScreen(MAIN_MENU_ID, MAIN_MENU_FILE);
        mainMenu.setGameManager(gameManager);

    }
}
