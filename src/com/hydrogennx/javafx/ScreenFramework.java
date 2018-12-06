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

        graphicsManager.loadScreen(MAIN_MENU_ID, MAIN_MENU_FILE);

        MainMenu mainMenu = (MainMenu) graphicsManager.getController(MAIN_MENU_ID);
        mainMenu.setGameManager(gameManager);

    }

    public void loadGameScreens() {

        graphicsManager.loadScreen(TURN_PHASE_ID, TURN_PHASE_FILE);
        graphicsManager.loadScreen(ACTION_PHASE_ID, ACTION_PHASE_FILE);

        TurnPhase turnPhase = (TurnPhase) graphicsManager.getController(TURN_PHASE_ID);
        ActionPhase actionPhase = (ActionPhase) graphicsManager.getController(ACTION_PHASE_ID);

        turnPhase.setGameInstance(gameManager.getGameInstance());
        actionPhase.setGameInstance(gameManager.getGameInstance());

    }
}
