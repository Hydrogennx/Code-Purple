package com.hydrogennx;

import com.hydrogennx.javafx.ActionPhase;
import com.hydrogennx.javafx.MainMenu;
import com.hydrogennx.javafx.TurnPhase;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

/**
 * A game manager designed to work with JavaFX, rather than Swing, libraries.
 * All the methods and such are copy-pasted from the other. Inheritance was attempted, but failed.
 * Also, one version is going to be temporary anyway -- we don't much have to worry about changing both.
 */
public class JavaFXGameManager extends Application {

    GameContext gameContext;

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

    public JavaFXGameManager() {

        gameContext = GameContext.INACTIVE;

        gameInstance = null;

        System.out.println("Game loaded.");

    }

    /**
     * Creates and returns a content pane based on the current game state.
     * @return A content pane based on the current game state.
     */
    private Parent getContentPane() {
        try {
            switch (gameContext) {
                case INACTIVE:
                    return new MainMenu(this).getMainWindow();
                default:
                    return gameInstance.getCurrentWindow();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    protected void updateGameWindow() {

        primaryStage.setTitle("First Jump JavaFX MainMenu");
        primaryStage.setScene(new Scene(getContentPane()));
        primaryStage.show();

    }

    public void startLocalPractice() {

    }

    protected void startLoop() {

    }
}
