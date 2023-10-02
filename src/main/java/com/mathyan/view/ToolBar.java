package com.mathyan.view;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.image.ImageObserver;

import com.mathyan.controller.ButtonEvent;
import com.mathyan.controller.ButtonEventListener;

/**
 * This class represents the toolbar of the application.
 * <p>
 * The toolbar contains the save and open buttons.
 */
public class ToolBar extends JToolBar {
    private JButton saveFileButton;
    private JButton openFileButton;

    private ButtonEventListener listener;
    private ButtonEvent event;
    /**
     * Constructs a ToolBar object.
     */
    public ToolBar() {
        super();
        initialize();
        activate();
    }

    /**
     * Initializes the toolbar.
     */
    private void initialize() {
        this.setFloatable(false);
        this.setRollover(true);
        this.setOrientation(SwingConstants.HORIZONTAL);
        this.setSize(ImageObserver.WIDTH,29);
        saveFileButton = new JButton("Save");
        openFileButton = new JButton("Open");
        this.add(saveFileButton);
        this.add(openFileButton);
    }

    /**
     * Activates the toolbar.
     * Adds action listeners to the buttons.
     */
    private void activate() {
        saveFileButton.addActionListener(e -> {
            event = new ButtonEvent(this, "save");
            listener.saveFile(event);
        });
        openFileButton.addActionListener(e -> {
            event = new ButtonEvent(this, "open");
            listener.openFile(event);
        });
    }

    /**
     * Sets the button event listener.
     *
     * @param listener the button event listener
     */
    public void setButtonEventListener(ButtonEventListener listener) {
        this.listener = listener;
    }

}
