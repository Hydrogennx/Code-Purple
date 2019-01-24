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

    public void writeToFile() {
        String fileName = "settings"; // File you want to write to (will overwrite file)
        try {
            File jarFile = new File(SettingsMenu.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            fileName = jarFile.getParent() + File.separator + fileName;  // File.separator is the same as a "/"

            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(gameManager.getSettings().getUsername());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }
//    @FXML
//    public void mousePressed() {
//        String mouseDownMusicFile = "src/com/hydrogennx/core/resource/MouseDown.mp3";
//        Media pressed = new Media(new File(mouseDownMusicFile).toURI().toString());
//
//        pressedMusic = new MediaPlayer(pressed);
//        pressedMusic.play();
//    }
//
//    @FXML
//    public void mouseReleased() {
//        String mouseUpMusicFile = "src/com/hydrogennx/core/resource/MouseUp.mp3";
//        Media released = new Media(new File(mouseUpMusicFile).toURI().toString());
//
//        releasedMusic = new MediaPlayer(released);
//        releasedMusic.play();
//    }

}
