package com.hydrogennx.core;

import com.hydrogennx.core.javafx.*;
import javafx.animation.AnimationTimer;
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

    public ScreenFramework screenFramework = new ScreenFramework();
    GameInstance gameInstance;

    Stage primaryStage;
    Scene primaryScene;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        updateGameWindow();

        startLoop();

    }

    public GameManager() {

        gameContext = GameContext.INACTIVE;

        screenFramework.setGameManager(this);
        screenFramework.loadMenus();

        gameInstance = null;

        System.out.println("Game loaded.");

    }

    public void updateScreen() {
        switch (gameContext) {
            case INACTIVE:
                screenFramework.wcm.setScreen("MAIN_MENU");
                break;
            default:
                gameInstance.updateScreen();
        }
    }

    protected void updateGameWindow() {

        primaryScene = new Scene(screenFramework.wcm, 750, 500);

        primaryStage.setTitle("First Jump JavaFX MainMenu");
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        updateScreen();

    }

    public void startLocalPractice() {
        gameContext = GameContext.LOCAL_PRACTICE;
        gameInstance = new LocalPracticeInstance(this);

        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void startHost() {

        gameContext = GameContext.HOST;
        gameInstance = new HostInstance(this);

        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void startTutorial() {
        gameContext = GameContext.LOCAL_PRACTICE;
        gameInstance = new LocalTutorialInstance(this);

        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void startOption() {
        screenFramework.loadOptions();
        updateScreen();
    }

    protected void startLoop() {

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                if (getGameInstance() != null) {

                    getGameInstance().update(t);

                }

            }
        }.start();

    }

    public GameInstance getGameInstance() {

        return gameInstance;

    }

    public void exit() {

        primaryStage.close();

    }

    public void stopGame() {

        gameContext = GameContext.INACTIVE;
        gameInstance = null;

    }
}
