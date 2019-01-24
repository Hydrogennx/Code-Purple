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


        setOnMousePressed(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent me) {

                String mouseDownMusicFile = "src/com/hydrogennx/core/resource/MouseDown.mp3";
                Media pressed = new Media(new File(mouseDownMusicFile).toURI().toString());

                pressedSound = new MediaPlayer(pressed);
                pressedSound.play();

            }

        });


        setOnMouseReleased(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent me) {

                String mouseUpMusicFile = "src/com/hydrogennx/core/resource/MouseUp.mp3";
                Media released = new Media(new File(mouseUpMusicFile).toURI().toString());

                releasedSound = new MediaPlayer(released);
                releasedSound.play();

            }

        });
    }

}
