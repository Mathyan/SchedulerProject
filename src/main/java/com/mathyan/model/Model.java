package com.mathyan.model;

import java.util.*;

import com.mathyan.model.week_data.Person;
import com.mathyan.model.week_data.Week;

/**
 * This class represents the model of the application.
 * <p>
 * The model consists of a set of weeks and a list of persons.
 * <p>
 * It is used to store the data of the application.
 * And to provide methods to access and modify the data.
 * <p>
 */
public class Model {
    private HashMap<Integer, Week> weeks;

    private List<Person> persons;

    /**
     * Constructs a Model object.
     */
    public Model() {
        this.weeks = new HashMap<>();
        this.persons = new ArrayList<>();
    }

    /**
     * Adds a person to a week and to the list of persons.
     * <p>
     * If the week does not exist, it is created.
     * If the person already exists, it is not added.
     * <p>
     * @param person the person to be added
     * @param weekNumber the week number of the week to which the person is added
     */
    public void addPersonToWeek(Person person, Integer weekNumber) {
        if (person != null) {
            if (weeks.containsKey(weekNumber)) {
                weeks.get(weekNumber).addPerson(person);
            } else {
                Week week = new Week(weekNumber);
                week.addPerson(person);
                weeks.put(weekNumber, week);
            }
            addPerson(person);
        }
    }

    /**
     * Adds a person to the list of persons.
     * <p>
     * If the person already exists, it is not added.
     * <p>
     * @param person the person to be added
     */
    public void addPerson(Person person) {
        if (person != null && !persons.contains(person)){
                persons.add(person);
            }
    }


}
