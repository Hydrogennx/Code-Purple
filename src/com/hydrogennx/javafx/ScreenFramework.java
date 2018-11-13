package com.hydrogennx.javafx;

import com.hydrogennx.GameManager;

/**
 * A static helper class. Loads an instance of every screen,
 * and records the indices for each one.
 */
public class ScreenFramework {

    GameManager gameManager;

    public WindowControllerManager graphicsManager = new WindowControllerManager();

    public final static String TURN_PHASE_ID = "TURN_PHASE";
    public final static String ACTION_PHASE_ID = "ACTION_PHASE";
    public final static String MAIN_MENU_ID = "MAIN_MENU";
    public final static String TURN_PHASE_FILE = "TurnPhase.fxml";
    public final static String ACTION_PHASE_FILE = "ActionPhase.fxml";
    public final static String MAIN_MENU_FILE = "MainMenu.fxml";

    public void setGameManager(GameManager gameManager) {
        if (this.gameManager == null) {
            this.gameManager = gameManager;
        }
    }

    public void loadMenus() {
        MainMenu mainMenu = (MainMenu) graphicsManager.loadScreen(MAIN_MENU_ID, MAIN_MENU_FILE);
        mainMenu.setGameManager(gameManager);

    }

    public void loadGameScreens() {

        TurnPhase turnPhase = (TurnPhase) graphicsManager.loadScreen(TURN_PHASE_ID, TURN_PHASE_FILE);
        ActionPhase actionPhase = (ActionPhase) graphicsManager.loadScreen(ACTION_PHASE_ID, ACTION_PHASE_FILE);

        turnPhase.setGameInstance(gameManager.getGameInstance());
        actionPhase.setGameInstance(gameManager.getGameInstance());

    }
}
