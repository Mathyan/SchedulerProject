package com.mathyan.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.mathyan.controller.ButtonEventListener;
import com.mathyan.controller.DeletePeronListener;
import com.mathyan.controller.UpdateEvent;
import com.mathyan.model.week_data.Person;
import com.mathyan.controller.EditPersonWindowClosedListener;

/**
 * This class represents the window frame of the application.
 * <p>
 * The window frame contains the toolbar and the main panel.
 */
public class WindowFrame extends JFrame {
    private ToolBar toolBar;
    private MainPanel mainPanel;
    private transient EditPersonWindowClosedListener editPersonWindowClosedListener;

    /**
     * Constructs a WindowFrame object.
     */
    public WindowFrame() {
        super();
        initialize();
        addToolBar();
        addMainPanel();
        addFooter();
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
     * Adds the footer to the window.
     */
    private void addFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel footerLabel = new JLabel(" ");
        footerPanel.add(footerLabel);
        this.add(footerPanel, BorderLayout.SOUTH);
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

    /**
     * Sets the current week.
     *
     * @param currentWeek the current week
     */
    public void setCurrentWeek(int currentWeek) {
        mainPanel.setCurrentWeek(currentWeek);
    }

    /**
     * Updates the table.
     *
     * @param e the update event
     */
    public void updateTable(UpdateEvent e) {
        mainPanel.updateTable(e);
    }

    /**
     * Updates the week list.
     *
     * @param weekList the week list
     */
    public void updateWeekList(List<Integer> weekList) {
        mainPanel.updateWeekList(weekList);
    }

    /**
     * Opens the edit person menu.
     * <p>
     * Makes main window unfocusable.
     *
     ** @param persons the list of persons
     */
    public void openEditPersonMenu(List<Person> persons, int currentWeek) {
        setFocusableWindowState(false);

        EditPersonWindow editPersonWindow = new EditPersonWindow(this, persons, currentWeek);
        editPersonWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        editPersonWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setFocusableWindowState(true);
                if (editPersonWindowClosedListener != null) {
                    editPersonWindowClosedListener.onEditPersonWindowClosed();
                    System.out.println("Window closed");
                }
            }
        });
    }

    /**
     * Sets the edit person window closed listener.
     *
     * @param listener the listener
     */
    public void setEditPersonWindowClosedListener(EditPersonWindowClosedListener listener) {
        this.editPersonWindowClosedListener = listener;
    }

    /**
     * Removes the edit person window closed listener.
     */
    public void removeEditPersonWindowClosedListener() {
        this.editPersonWindowClosedListener = null;
    }

    public void passDeletePersonListener(DeletePeronListener listener) {
        mainPanel.passDeletePersonListener(listener);
    }

}
