package com.mathyan.model.week_data;

import java.io.Serializable;
import java.util.*;

/**
 * This class represents the data for a single person.
 * <p>
 * The name and surname are the name and surname of the person.
 * The id is the unique identifier of the person.
 * The week data is a map of week numbers to sets of DayData objects.
 * The weeks is a set of Week objects.
 * <p>
 * This class implements the Serializable interface so that it can be
 * serialized and deserialized.
 */
public class Person implements Serializable {
    private String name;
    private String surname;
    private int id;
    private static int idCounter = 0;
    private Map<Integer, Set<DayData>> weekData;

    /**
     * Constructs a Person object with the given name, surname and week number.
     *
     * @param name       the name of the person
     * @param surname    the surname of the person
     * @param weekNumber the week number of the person
     */
    public Person(String name , Optional<String> surname) {
        this.name = name;
        this.id = idCounter++;
        this.weekData = new HashMap<>();
        if (surname.isPresent()) {
            this.surname = surname.get();
        } else {
            this.surname = "";
        }
    }

    /**
     * Empty constructor for Person.
     */
    public Person() {
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the name of the person
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the surname of the person.
     *
     * @return the surname of the person
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the person.
     *
     * @param surname the surname of the person
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the id of the person.
     *
     * @return the id of the person
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the person.
     *
     * @param id the id of the person
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the week data of the person.
     *
     * @return the week data of the person
     */
    public Map<Integer, Set<DayData>> getWeekData() {
        return weekData;
    }

    /**
     * Sets the week data of the person.
     *
     * @param weekData the week data of the person
     */
    public void setWeekData(Map<Integer, Set<DayData>> weekData) {
        this.weekData = weekData;
    }


}
