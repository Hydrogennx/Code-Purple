package com.hydrogennx.core;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundButton extends Button {

    MediaPlayer pressedSound;
    MediaPlayer releasedSound;

    public SoundButton() {

        //go to default style

        setOnMousePressed(me -> {

            getStyleClass().add("button-clicked");

            String mouseDownMusicFile = "src/com/hydrogennx/core/resource/MouseDown.mp3";
            Media pressed = new Media(new File(mouseDownMusicFile).toURI().toString());

            pressedSound = new MediaPlayer(pressed);
            pressedSound.play();

        });

        setOnMouseEntered(me -> getStyleClass().add("button-hover"));

        setOnMouseExited(me -> getStyleClass().remove("button-hover"));

        setOnMouseReleased(me -> {

            getStyleClass().remove("button-clicked");

            String mouseUpMusicFile = "src/com/hydrogennx/core/resource/MouseUp.mp3";
            Media released = new Media(new File(mouseUpMusicFile).toURI().toString());

            releasedSound = new MediaPlayer(released);
            releasedSound.play();

        });
    }

}
