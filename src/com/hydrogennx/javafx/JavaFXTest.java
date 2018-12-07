package com.hydrogennx.javafx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Testing app. Will be removed soon.
 */
public class JavaFXTest extends WindowController implements Initializable {

    @FXML
    public void sayHi() {
        System.out.println("Hello world!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
}
