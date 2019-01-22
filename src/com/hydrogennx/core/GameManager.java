package com.hydrogennx.core;

import com.hydrogennx.core.javafx.*;
import com.hydrogennx.core.network.Client;
import com.hydrogennx.core.network.NetworkThread;
import com.hydrogennx.core.network.ServerStatus;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

/**
 * A game manager to create instances of the various gametypes that exist.
 */
public class GameManager extends Application {

    Menu menu;

    public static final String SETTINGS_FILE = "settings";
    Settings settings;

    private NetworkThread networkInstance;

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

        menu = menu.MAIN_MENU;
        screenFramework.setGameManager(this);
        screenFramework.loadMenus();

        loadSettings();

        gameInstance = null;

        System.out.println("Game loaded.");

    }

    public void updateScreen() {
        if (gameInstance != null) {
            gameInstance.updateScreen();
        } else {
            switch (menu) {
                case MAIN_MENU:
                    screenFramework.wcm.setScreen(screenFramework.MAIN_MENU_ID);
                    break;
                case SETTINGS:
                    screenFramework.wcm.setScreen(screenFramework.SETTING_ID);
            }
        }
    }

    protected void updateGameWindow() {

        primaryScene = new Scene(screenFramework.wcm, 750, 500);

        primaryStage.setTitle("Code Purple");
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        updateScreen();

    }

    public void startLocalPractice() {
        gameInstance = new LocalPracticeInstance(this);

        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void startNetworkGame() {

        gameInstance = new HostInstance(this);

        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void joinGame(String ip) {

        gameInstance = new ClientInstance(this);



        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void startTutorial() {
        gameInstance = new LocalTutorialInstance(this);

        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void openSettingsMenu() {
        screenFramework.loadOptions();
        menu = Menu.SETTINGS;
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

        gameInstance = null;

        if (networkInstance != null) {
            networkInstance.closeConnection();
            networkInstance = null;
        }


    }

    public Settings getSettings() {

        return settings;

    }

    public void saveSettings() {

        try {

            FileOutputStream file = new FileOutputStream(SETTINGS_FILE);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(settings);

            out.close();
            file.close();

        } catch (IOException e) {

            System.out.println("Error saving settings!");

        }

    }

    public void loadSettings() {

        try {

            FileInputStream file = new FileInputStream(SETTINGS_FILE);
            ObjectInputStream in = new ObjectInputStream(file);

            settings = (Settings) in.readObject();

            in.close();
            file.close();

        } catch (IOException e) {

            System.out.println("Error loading settings, using defaults!");

            settings = new Settings();

        } catch (ClassNotFoundException e) {

            System.err.println(e.getStackTrace());
            System.out.println("Using default settings");

        }

    }

}
