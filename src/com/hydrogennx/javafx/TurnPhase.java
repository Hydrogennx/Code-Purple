package com.hydrogennx.javafx;

import com.hydrogennx.GameInstance;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TurnPhase implements Initializable {

    /*
    FIXME right now, every time turnPhase is created, an entirely new TurnPhase.fxml file is loaded.
    These should be created on initialization, and then shown on screen as appropriate.
    For now, JavaFXGameManager will just do this, and then handle IOExceptions as they come up.
    */
    private Parent mainWindow;
    private GameInstance gameInstance;

    public TurnPhase(GameInstance gameInstance) throws IOException {

        mainWindow = FXMLLoader.load(getClass().getResource("TurnPhase.fxml"));

        this.gameInstance = gameInstance;

    }

    public Parent getMainWindow() {
        return mainWindow;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
