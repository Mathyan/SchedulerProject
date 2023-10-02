package com.mathyan.model.week_data;

import java.io.Serializable;
import java.util.*;

/**
 * This class implements the Serializable interface so that it can be
 * serialized and deserialized.
 */
public class Person implements Serializable {
    private String name;
    private String surname;
    private int id;
    private static int idCounter = 0;
    private Map<Integer, Set<DayData>> weekData;
    private Set<Week> weeks;

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
        this.weeks = new HashSet<>();
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

    /**
     * Gets the weeks of the person.
     *
     * @return the weeks of the person
     */
    public Set<Week> getWeeks() {
        return weeks;
    }

    /**
     * Sets the weeks of the person.
     *
     * @param weeks the weeks of the person
     */
    public void setWeeks(Set<Week> weeks) {
        this.weeks = weeks;
    }

    /**
     * Adds person to the week.
     *
     * @param week the week to be added
     */
    public void addWeek(Integer week){
        if (week != null) {
            weeks.add(new Week(week));
        }
    }

}
