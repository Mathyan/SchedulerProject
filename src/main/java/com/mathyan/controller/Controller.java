package com.mathyan.controller;

import java.util.List;

import com.mathyan.model.Model;
import com.mathyan.view.View;

/**
 * This class represents the controller for the MVC pattern.
 * <p>
 * The controller is responsible for handling events from the view and updating
 * the model.
 */
public class Controller {
    private Model model;
    private View view;
    private int currentWeek;
    private List<Integer> weekList;
    /**
     * Constructs a Controller object with the given model and view.
     *
     * @param model the model
     * @param view  the view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.currentWeek = 1;
        model.setCurrentWeek(currentWeek);
        view.setCurrentWeek(currentWeek);
    }

    /**
     * Adds a button event listener to the view.
     *
     * @param listener the button event listener
     */
    public void passButtonEventListener(ButtonEventListener listener) {
        view.passButtonEventListener(listener);
    }

    /**
     * Adds an update event listener to the model.
     *
     * @param listener the update event listener
     */
    public void addUpdateEventListener(UpdateEventListener listener) {
        model.addUpdateEventListener(listener);
    }

    /**
     * Starts the controller.
     */
    public void start() {
        view.refresh();
        this.passButtonEventListener(
                new ButtonEventListener() {
                    @Override
                    public void saveFile(FileEvent e) {
                        model.saveFile(e);
                        view.openSaveFileDialog(e);
                        updateWeekList();
                    }

                    @Override
                    public void openFile(FileEvent e) {
                        view.openOpenFileDialog(e);
                        model.openFile(e);
                        updateWeekList();
                    }

                    @Override
                    public void nextWeek(ButtonEvent e) {
                        model.nextWeek();
                    }

                    @Override
                    public void previousWeek(ButtonEvent e) {
                        model.previousWeek();
                    }

                    @Override
                    public void editWeek(ButtonEvent e) {
                        view.toggleTableEditable();
                    }
                });

        this.addUpdateEventListener(
                (UpdateEvent e) -> view.updateTable(e));
    }

    /**
     * Updates the current week list.
     * 
     */
    public void updateWeekList() {
        weekList = model.getWeekList();
        view.updateWeekList(weekList);
    }

    /**
     * Gets the current week.
     *
     * @return the current week
     */
    public int getCurrentWeek() {
        return currentWeek;
    }

    /**
     * Sets the current week.
     *
     * @param currentWeek the current week
     */
    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

}
