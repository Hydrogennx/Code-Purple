package com.hydrogennx.controller;

import com.hydrogennx.core.GameManager;
import com.hydrogennx.core.javafx.ScreenFramework;
import com.hydrogennx.core.javafx.WindowControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class SettingsMenu extends WindowController implements Initializable {

    private GameManager gameManager = null;
    public WindowControllerManager wcm = new WindowControllerManager();

    @FXML
    TextField nameTextField;

    @FXML
    CheckBox musicEnabledCheckbox;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveButtonPressed() {

        gameManager.getSettings().setUsername(nameTextField.getText());
        gameManager.getSettings().setMusicEnabled(musicEnabledCheckbox.isSelected());

        gameManager.saveSettings();

        gameManager.toMainMenu();

    }

    public void cancelButtonPressed() {
        gameManager.toMainMenu();
    }

    public void setGameManager(GameManager gameManager) {
        if (this.gameManager == null) {
            this.gameManager = gameManager;

            nameTextField.setText(gameManager.getSettings().getUsername());
            musicEnabledCheckbox.setSelected(gameManager.getSettings().getMusicEnabled());
        }
    }


}
