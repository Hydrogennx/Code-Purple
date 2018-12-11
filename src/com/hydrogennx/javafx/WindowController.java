package com.hydrogennx.javafx;

import javafx.scene.Node;

/**
 * Abstract class representing any Java class that directly controls a window.
 */
public abstract class WindowController {

    private static Node mainWindow;
    private WindowControllerManager manager;

    public void setManager(WindowControllerManager windowControllerManager) {
        this.manager = windowControllerManager;
    }
}
