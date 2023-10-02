package com.mathyan.view;

import java.awt.BorderLayout;

/**
 * This class represents the view of the application.
 * <p>
 * App is JSwing application.
 * <p>
 */
public class View {

    WindowFrame windowFrame;
    /**
     * Constructs a View object.
     */
    public View() {
        initialize();
        addToolBar();
        addMainPanel();
    }

    /**
     * Initializes the swing window.
     */
    private void initialize() {
        windowFrame = new WindowFrame();
        windowFrame.setTitle("Scheduler Project");
        windowFrame.setLayout(new BorderLayout());
        }

    /**
     * Adds the toolbar to the window.
     */
    private void addToolBar() {
        ToolBar toolBar = new ToolBar();
        windowFrame.add(toolBar, BorderLayout.NORTH);
    }

    /**
     * Adds the main panel to the window.
     */
    public void addMainPanel() {
        MainPanel mainPanel = new MainPanel();
        windowFrame.add(mainPanel, BorderLayout.CENTER);
    }




}
