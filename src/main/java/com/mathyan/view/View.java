package com.mathyan.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

import com.mathyan.controller.ButtonEventListener;
import com.mathyan.controller.Controller;
import com.mathyan.controller.FileEvent;
import com.mathyan.controller.UpdateEvent;
import com.mathyan.model.week_data.Person;

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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
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
            try (BufferedReader reader = new BufferedReader(new java.io.FileReader(selectedFile))) {
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }
                e.setJson(sb.toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Sets the current week.
     *
     * @param currentWeek the current week
     */
    public void setCurrentWeek(int currentWeek) {
        this.windowFrame.setCurrentWeek(currentWeek);
    }

    /**
     * Updates the table.
     *
     * @param e the update event
     */
    public void updateTable(UpdateEvent e) {
        windowFrame.updateTable(e);
    }

    /**
     * Updates the week list.
     *
     * @param weekList the week list
     */
    public void updateWeekList(List<Integer> weekList) {
        windowFrame.updateWeekList(weekList);
    }

    /**
     * Opens the edit person menu.
     *
     * @param persons the list of persons
     */
    public void openEditPersonMenu(List<Person> persons, int currentWeek) {
        windowFrame.openEditPersonMenu(persons, currentWeek);
    }

    /**
     * Sets the edit person window closed listener.
     *
     * @param controller the controller
     */
    public void setEditPersonWindowClosedListener(Controller controller) {
        windowFrame.setEditPersonWindowClosedListener(controller);
    }




}
