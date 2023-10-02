package com.mathyan.controller;

import java.util.EventObject;

/**
 * This class represents an event that is fired when a button is pressed.
 * <p>
 * The source of the event is the button that was pressed.
 * The command is the command that the button was associated with.
 */
public class ButtonEvent extends EventObject{
    private String command;

    /**
     * Constructs a ButtonEvent object with the given source and command.
     *
     * @param source  the source of the event
     * @param command the command associated with the button
     */
    public ButtonEvent(Object source, String command) {
        super(source);
        this.command = command;
    }

    /**
     * Gets the command associated with the button.
     *
     * @return the command associated with the button
     */
    public String getCommand() {
        return command;
    }

}
