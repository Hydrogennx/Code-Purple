package com.hydrogennx.javafx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JavaFXTest {

    public static Node getMainWindow() throws IOException {
        URL resource = getClass().getResource("JavaFXTest.fxml");
        System.out.println(resource);
        Parent mainWindow = FXMLLoader.load(resource);
        return mainWindow;
    }

    @FXML
    public void sayHi() {
        System.out.println("Hello world!");
    }

}
