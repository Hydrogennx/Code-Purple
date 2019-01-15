package com.hydrogennx.core.javafx;

import com.hydrogennx.controller.*;
import com.hydrogennx.core.GameManager;

/**
 * A static helper class. Loads an instance of every screen,
 * and records the indices for each one.
 * Also gives an access point to a static instance of a WindowControllerManager.
 */
public class ScreenFramework {

    GameManager gameManager;

    public WindowControllerManager wcm = new WindowControllerManager();

    public final static String TURN_PHASE_ID = "TURN_PHASE";
    public final static String ACTION_PHASE_ID = "ACTION_PHASE";
    public final static String MAIN_MENU_ID = "MAIN_MENU";
    public final static String GAME_OVER_ID = "GAME_OVER";
    public final static String PRACTICE_TURN_PHASE_ID = "PRACTICE_TURN_PHASE";
    public final static String SETTING_ID = "SETTING_ID";
    public final static String TURN_PHASE_FILE = "TurnPhase.fxml";
    public final static String ACTION_PHASE_FILE = "ActionPhase.fxml";
    public final static String MAIN_MENU_FILE = "MainMenu.fxml";
    public final static String GAME_OVER_FILE = "GameOver.fxml";
    public final static String PRACTICE_TURN_FILE = "PracticeTurnPhase.fxml";
    public final static String SETTING_FILE = "Setting.fxml";

    public void setGameManager(GameManager gameManager) {
        if (this.gameManager == null) {
            this.gameManager = gameManager;
        }
    }

    public void loadMenus() {

        wcm.loadScreen(MAIN_MENU_ID, MAIN_MENU_FILE);

        wcm.loadScreen(SETTING_ID, SETTING_FILE);

        MainMenu mainMenu = (MainMenu) wcm.getController(MAIN_MENU_ID);
        mainMenu.setGameManager(gameManager);


    }

    public void loadOptions() {
        wcm.loadScreen(SETTING_ID, SETTING_FILE);
        Setting setting = (Setting) wcm.getController(SETTING_ID);
        setting.setGameManager(gameManager);

    }

    public void loadGameScreens() {

        wcm.loadScreen(TURN_PHASE_ID, TURN_PHASE_FILE);
        wcm.loadScreen(ACTION_PHASE_ID, ACTION_PHASE_FILE);
        wcm.loadScreen(GAME_OVER_ID, GAME_OVER_FILE);
        wcm.loadScreen(PRACTICE_TURN_PHASE_ID, PRACTICE_TURN_FILE);

        TurnPhase turnPhase = (TurnPhase) wcm.getController(TURN_PHASE_ID);
        ActionPhase actionPhase = (ActionPhase) wcm.getController(ACTION_PHASE_ID);
        GameOver gameOver = (GameOver) wcm.getController(GAME_OVER_ID);
        PracticeTurnPhase practiceTurnPhase = (PracticeTurnPhase) wcm.getController(PRACTICE_TURN_PHASE_ID);

        turnPhase.setGameInstance(gameManager.getGameInstance());
        actionPhase.setGameInstance(gameManager.getGameInstance());
        gameOver.setGameInstance(gameManager.getGameInstance());
        practiceTurnPhase.setGameInstance(gameManager.getGameInstance());

    }

}
