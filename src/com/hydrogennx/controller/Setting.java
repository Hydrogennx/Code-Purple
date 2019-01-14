package com.hydrogennx.controller;

import com.hydrogennx.core.GameManager;
import com.hydrogennx.core.javafx.ScreenFramework;
import com.hydrogennx.core.javafx.WindowControllerManager;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;




public class Setting extends WindowController implements Initializable {

    private GameManager gameManager = null;
    public WindowControllerManager wcm = new WindowControllerManager();

    public void initialize(URL location, ResourceBundle resources) {




    }

    public void saveButtonPressed() {

    }

    public void cancelButtonPressed() {
        gameManager.screenFramework.wcm.setScreen(ScreenFramework.MAIN_MENU_ID);

    }
    
}
