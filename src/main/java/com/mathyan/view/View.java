package com.mathyan.view;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import javax.swing.JFileChooser;

import com.mathyan.controller.ButtonEventListener;
import com.mathyan.controller.FileEvent;
import com.mathyan.controller.UpdateEvent;

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
    }

    /**
     * Initializes the swing window.
     */
    private void initialize() {
        windowFrame = new WindowFrame();
    }

    /**
     * Repacks and repaints the window.
     */
    public void refresh() {
        windowFrame.pack();
        windowFrame.repaint();
    }

    /**
     * Sends the button event listener to the window frame.
     *
     * @param listener
     */
    public void passButtonEventListener(ButtonEventListener listener) {
        windowFrame.passButtonEventListener(listener);
    }

    /**
     * Opens the save file dialog.
     *
     * @param e the file event
     */
    public void openSaveFileDialog(FileEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(e.getJson());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Opens the open file dialog.
     *
     * @param e the file event
     */
    public void openOpenFileDialog(FileEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            e.setFile(selectedFile);
        }
    }

    public void setCurrentWeek(int currentWeek) {
        this.windowFrame.setCurrentWeek(currentWeek);
    }

    public void updateTable(UpdateEvent e) {
        windowFrame.updateTable(e);
    }
}
