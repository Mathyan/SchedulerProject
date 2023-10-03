package com.mathyan.view;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.image.ImageObserver;

import com.mathyan.controller.ButtonEventListener;
import com.mathyan.controller.FileEvent;

/**
 * This class represents the toolbar of the application.
 * <p>
 * The toolbar contains the save and open buttons.
 */
public class ToolBar extends JToolBar {
    private JButton saveFileButton;
    private JButton openFileButton;

    /**
     * Constructs a ToolBar object.
     */
    public ToolBar() {
        super();
        initialize();
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
     * Adds the button event listener to the buttons.
     *
     * @param listener the button event listener
     */
    public void addButtonEventListener(ButtonEventListener listener) {
        saveFileButton.addActionListener(e -> listener.saveFile(new FileEvent(this, "Save")));
        openFileButton.addActionListener(e -> listener.openFile(new FileEvent(this, "Open")));
    }


}
