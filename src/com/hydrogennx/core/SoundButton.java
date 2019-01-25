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
    MediaPlayer hoverSound;

    public SoundButton() {

        setOnMousePressed(me -> {

            getStyleClass().add("button-clicked");

            String mouseDownMusicFile = getClass().getResource("/MouseDown.mp3").toString();
            Media pressed = new Media(mouseDownMusicFile);

            pressedSound = new MediaPlayer(pressed);
            pressedSound.play();

        });

        setOnMouseEntered(me -> {
            getStyleClass().add("button-hover");

            String mouseHoverFile = getClass().getResource("/hover.mp3").toString();
            Media hover = new Media(mouseHoverFile);

            hoverSound = new MediaPlayer(hover);
            hoverSound.play();

        });

        setOnMouseExited(me -> getStyleClass().remove("button-hover"));

        setOnMouseReleased(me -> {

            getStyleClass().remove("button-clicked");

            String mouseUpMusicFile = getClass().getResource("/MouseUp.mp3").toString();
            Media released = new Media(mouseUpMusicFile);

            releasedSound = new MediaPlayer(released);
            releasedSound.play();

        });
    }

}
