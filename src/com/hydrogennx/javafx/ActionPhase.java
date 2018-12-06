package com.hydrogennx.javafx;

import com.hydrogennx.ControllableCharacter;
import com.hydrogennx.GameInstance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionPhase extends WindowController implements Initializable {

    private GameInstance gameInstance;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView characterSprite;

    public ActionPhase() throws IOException {



    }

    public void setGameInstance(GameInstance gameInstance) {
        if (gameInstance != null) {
            this.gameInstance = gameInstance;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO do things
        healthBar.setProgress(0.5);
    }

    public void update(double time) {
        healthBar.setProgress(healthBar.getProgress() - 0.01);
    }

}
