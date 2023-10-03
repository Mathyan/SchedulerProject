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
	 * @param fileEvent the button event
	 */
	void saveFile(FileEvent fileEvent);

	/**
	 * This method is called when the open button is pressed.
	 *
	 * @param fileEvent the button event
	 */
	void openFile(FileEvent fileEvent);

	/**
	 * This method is called when the next week button is pressed.
	 *
	 * @param e the button event
	 */
	void nextWeek(ButtonEvent e);

	/**
	 * This method is called when the previous week button is pressed.
	 *
	 * @param e the button event
	 */
	void previousWeek(ButtonEvent e);

	/**
	 * This method is called when the edit week button is pressed.
	 *
	 * @param e the button event
	 */
	void editWeek(ButtonEvent e);

}