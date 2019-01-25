package com.hydrogennx.core;

import com.hydrogennx.controller.SettingsMenu;
import com.hydrogennx.controller.MainMenu;
import com.hydrogennx.controller.WindowController;
import com.hydrogennx.core.javafx.ScreenFramework;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A game manager to create instances of the various gametypes that exist.
 */
public class GameManager extends Application {

    Menu menu;

    public static final String SETTINGS_FILE = "settings";
    Settings settings;

    private ScreenFramework screenFramework = new ScreenFramework();
    GameInstance gameInstance;

    Stage primaryStage;
    Scene primaryScene;

    public static void main(String[] args) {
        launch(args);
    }

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

        primaryScene = new Scene(screenFramework.wcm, 750, 500, Color.BLACK);

        primaryScene.setFill(Color.BLACK);

        primaryScene.getStylesheets().add("com/hydrogennx/core/resource/stylesheet.css");

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

        gameInstance = new NetworkGameInstance(this, true);

        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void joinGame() {

        gameInstance = new NetworkGameInstance(this, false);

        screenFramework.loadGameScreens();

        updateScreen();

    }

    public void openSettingsMenu() {
        screenFramework.loadOptions();
        menu = Menu.SETTINGS;
        updateScreen();
    }

    long lastFramePrintMillis = 0;
    int frames = 0;

    protected void startLoop() {

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                if (menu == Menu.MAIN_MENU) {

                    MainMenu mainMenu = (MainMenu) screenFramework.wcm.getController(ScreenFramework.MAIN_MENU_ID);

                    System.out.println(t);

                    mainMenu.updateTransition(t);

                }

                if (getGameInstance() != null) {

                    getGameInstance().update(t);
                    frames++;
                }
                if(System.currentTimeMillis()- lastFramePrintMillis >1000)
                {
                    System.out.println("FPS: "+frames);
                    frames = 0;
                    lastFramePrintMillis = System.currentTimeMillis();
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
            writeToLogFile(e);
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
            writeToLogFile(e);
            System.out.println("Error loading settings, using defaults!");

            settings = new Settings();

            settings.setUsername("Player");
            settings.setMusicEnabled(true);

        } catch (ClassNotFoundException e) {
            writeToLogFile(e);
            System.err.println(e.getStackTrace());
            System.out.println("Using default settings");

        }

    }

    public WindowController getWindowController(String windowControllerId) {
        return screenFramework.wcm.getController(windowControllerId);
    }

    public void setScreen(String windowControllerId) {
        screenFramework.wcm.setScreen(windowControllerId);
    }

    public void toMainMenu() {

        menu = Menu.MAIN_MENU;

        setScreen(ScreenFramework.MAIN_MENU_ID);

    }

    public void writeToLogFile(Exception message) {
        String fileName = "Log";
        LocalDateTime now = LocalDateTime.now();
        String toWrite = now + ": " + message.toString();
        System.out.println(toWrite);
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Files.write(Paths.get(fileName), toWrite.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
