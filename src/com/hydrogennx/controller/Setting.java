package com.hydrogennx.controller;

import com.hydrogennx.core.GameManager;
import com.hydrogennx.core.javafx.ScreenFramework;
import com.hydrogennx.core.javafx.WindowControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class Setting extends WindowController implements Initializable {

    private GameManager gameManager = null;
    public WindowControllerManager wcm = new WindowControllerManager();
    public String nickName = " ";

    @FXML
    TextField nameTextField;

    public void initialize(URL location, ResourceBundle resources) {




    }

    public void saveButtonPressed() {
        nickName = nameTextField.getText();
        System.out.println(nickName);

    }

    public void cancelButtonPressed() {
        gameManager.screenFramework.wcm.setScreen(ScreenFramework.MAIN_MENU_ID);

    }

    public void setGameManager(GameManager gameManager) {
        if (this.gameManager == null) {
            this.gameManager = gameManager;
        }
    }

    public void writeToFile() {
        String fileName = "settingsData"; // File you want to write to (will overwrite file)
        try {
            File jarFile = new File(Setting.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            fileName = jarFile.getParent() + File.separator + fileName;  // File.separator is the same as a "/"

            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("This is writing");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

}
