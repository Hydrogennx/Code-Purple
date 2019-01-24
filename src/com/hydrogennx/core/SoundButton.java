package com.hydrogennx.core;

import com.hydrogennx.controller.GameOver;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundButton extends Button {
    MediaPlayer pressedMusic;
    MediaPlayer releasedMusic;

    public SoundButton() {
        this.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse click detected! " + mouseEvent.getSource());
            }
        });
    }

    @Override
    public void onMousePressed() {
        String mouseDownMusicFile = "src/com/hydrogennx/core/resource/MouseDown.mp3";
        Media pressed = new Media(new File(mouseDownMusicFile).toURI().toString());

        pressedMusic = new MediaPlayer(pressed);
        pressedMusic.play();
    }

    @Override
    public void mouseReleased() {
        String mouseUpMusicFile = "src/com/hydrogennx/core/resource/MouseUp.mp3";
        Media released = new Media(new File(mouseUpMusicFile).toURI().toString());

        releasedMusic = new MediaPlayer(released);
        releasedMusic.play();
    }

}
