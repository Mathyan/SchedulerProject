package com.mathyan.controller;

import java.util.EventListener;

/**
 * This interface represents a listener for button events.
 * <p>
 * The saveFile method is called when the save button is pressed.
 * The openFile method is called when the open button is pressed.
 */
public interface ButtonEventListener extends EventListener {

    /**
     * This method is called when the save button is pressed.
     *
     * @param e the button event
     */
    void saveFile(ButtonEvent e);

    /**
     * This method is called when the open button is pressed.
     *
     * @param e the button event
     */
    void openFile(ButtonEvent e);
}