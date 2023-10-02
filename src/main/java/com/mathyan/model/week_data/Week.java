package com.mathyan.model.week_data;

import java.io.Serializable;
import java.util.*;

/**
 * This class represents a week.
 * <p>
 * Week has a number and a array list of persons present in that week.
 * <p>
 * This class implements the Serializable interface so that it can be
 * serialized and deserialized.
 */
public class Week implements Serializable{
    private Integer weekNumber;
    private Set<Person> persons;

    /**
     * Constructs a Week object with the given week number.
     *
     * @param weekNumber the week number of the week
     */
    public Week(Integer weekNumber) {
        this.weekNumber = weekNumber;
        this.persons = new HashSet<>();
    }

    /**
     * Empty constructor for Week.
     */
    public Week() {
    }

    /**
     * Gets the week number of the week.
     * @return the week number of the week
     */
    public Integer getWeekNumber() {
        return weekNumber;
    }

    /**
     * Sets the week number of the week.
     * @param weekNumber the week number of the week
     */
    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    /**
     * Adds a person to the week.
     * @param person the person to be added
     */
    public void addPerson(Person person) {
        if (person != null) {
            persons.add(person);
        }
    }

    /**
     * Removes a person from the week.
     * @param person the person to be removed
     */
    public void removePerson(Person person) {
        if (person != null) {
            persons.remove(person);
        }
    }

    /**
     * Gets the persons in the week.
     * @return the persons in the week
     */
    public Set<Person> getPersons() {
        return persons;
    }
}
