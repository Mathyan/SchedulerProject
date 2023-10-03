package com.mathyan.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.mathyan.controller.ButtonEventListener;
import com.mathyan.controller.UpdateEvent;

/**
 * This class represents the window frame of the application.
 * <p>
 * The window frame contains the toolbar and the main panel.
 */
public class WindowFrame extends JFrame{
    private ToolBar toolBar;
    private MainPanel mainPanel;

    /**
     * Constructs a WindowFrame object.
     */
    public WindowFrame(){
        super();
        initialize();
        addToolBar();
        addMainPanel();
    }

    /**
     * Initializes the window frame.
     */
    private void initialize() {
        this.setTitle("Scheduler Project");
        this.setSize((FontWidth.getFontWidth() + 20) * 8 + 100, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(rootPaneCheckingEnabled);
        this.setLayout(new BorderLayout());
    }

    /**
     * Adds the toolbar to the window.
     */
    private void addToolBar() {
        toolBar = new ToolBar();
        this.add(toolBar, BorderLayout.NORTH);
    }

    /**
     * Adds the main panel to the window.
     */
    private void addMainPanel() {
        mainPanel = new MainPanel();
        mainPanel.setPreferredSize(new Dimension(1000, 600));
        this.add(mainPanel, BorderLayout.CENTER);
    }


    /**
     * Passes the button event listener for other components to use.
     *
     * @param listener
     */
    public void passButtonEventListener(ButtonEventListener listener) {
        mainPanel.addButtonEventListener(listener);
        toolBar.addButtonEventListener(listener);

    }

    public void setCurrentWeek(int currentWeek) {
        mainPanel.setCurrentWeek(currentWeek);
    }

    public void updateTable(UpdateEvent e) {
        mainPanel.updateTable(e);
    }
}
