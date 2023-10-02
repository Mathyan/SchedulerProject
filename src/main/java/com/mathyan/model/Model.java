package com.mathyan.model;

import java.util.*;

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

    private List<Person> persons;

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


}
