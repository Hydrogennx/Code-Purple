package com.hydrogennx;

import com.hydrogennx.javafx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A game manager designed to work with JavaFX, rather than Swing, libraries.
 * All the methods and such are copy-pasted from the other. Inheritance was attempted, but failed.
 * Also, one version is going to be temporary anyway -- we don't much have to worry about changing both.
 */
public class GameManager extends Application {

    GameContext gameContext;

    ScreenFramework screenFramework = new ScreenFramework();
    GameInstance gameInstance;

    Stage primaryStage;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {

        System.out.println("Hello World!");

        this.primaryStage = primaryStage;

        updateGameWindow();

        startLoop();

    }

    public GameManager() {

        gameContext = GameContext.INACTIVE;

        screenFramework.setGameManager(this);
        screenFramework.loadAll();

        gameInstance = null;

        System.out.println("Game loaded.");

    }

    public void updateScreen() {
        switch (gameContext) {
            case INACTIVE:
                screenFramework.graphicsManager.setScreen("MAIN_MENU");
        }
    }

    protected void updateGameWindow() {

        primaryStage.setTitle("First Jump JavaFX MainMenu");
        primaryStage.setScene(new Scene(screenFramework.graphicsManager));
        primaryStage.show();

        updateScreen();

    }

    public void startLocalPractice() {

    }

    protected void startLoop() {

    }
}
