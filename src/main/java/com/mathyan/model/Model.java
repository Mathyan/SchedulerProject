package com.mathyan.model;

import java.util.*;

import com.mathyan.controller.ButtonEvent;
import com.mathyan.controller.FileEvent;
import com.mathyan.controller.UpdateEvent;
import com.mathyan.controller.UpdateEventListener;
import com.mathyan.model.week_data.Person;

/**
 * This class represents the model of the application.
 * <p>
 * The model consists of a set of weeks and a list of persons.
 * <p>
 * It is used to store the data of the application.
 * And to provide methods to access and modify the data.
 */
public class Model {
    private int currentWeek;
    private List<Person> persons;
    private UpdateEventListener updateEventListener;

    /**
     * Constructs a Model object.
     */
    public Model() {
        this.persons = new ArrayList<>();
    }


    /**
     * Adds a new person to the list of persons.
     * <p>
     * If the person is already in the list, the person is not added.
     * <p>
     * @param person the person to be added
     */
    public void addPerson(Person person){
        if (!persons.contains(person)) {
            persons.add(person);
        }
    }

    /**
     * Gets the list of persons.
     * <p>
     * @return the list of persons
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Removes a person from the list of persons.
     * <p>
     * If the person is not in the list, the list is not modified.
     * <p>
     * @param person the person to be removed
     */
    public void removePerson(Person person){
        persons.remove(person);
    }

    /**
     * Adds an update event listener to the model.
     *
     * @param listener
     */
    public void addUpdateEventListener(UpdateEventListener listener) {
        this.updateEventListener = listener;
    }

    /**
     * Saves the current data to a file.
     * <p>
     * @param e the file event
     */
    public void saveFile(FileEvent e) {
        DataManipulation.saveFile(e, persons);
        fireUpdateEvent();
    }

    /**
     * Opens a file.
     * <p>
     * @param e the file event
     */
    public void openFile(FileEvent e) {
        persons = DataManipulation.openFile(e);
        fireUpdateEvent();
    }

    /**
     * Fires an update event.
     */
    private void fireUpdateEvent() {
        if (updateEventListener != null) {
            updateEventListener.update( new UpdateEvent(this, this.currentWeek, DataManipulation.getAllPersonsWeekDataInTableFormat(persons, currentWeek)));
        }
    }


    public void nextWeek(ButtonEvent e) {
    }


    public void previousWeek(ButtonEvent e) {
    }


    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }


}
