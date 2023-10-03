package com.mathyan.model;

import java.util.*;

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
	private List<Integer> weekList;

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
        persons.forEach(Person::testToString);
		int minWeek = DataManipulation.getMinWeek(persons);
		setCurrentWeek(minWeek);
		System.out.println("minWeek: " + minWeek);
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

	/**
	 * Moves to the next week.
	 */
	public void nextWeek() {
		currentWeek = weekList.get(weekList.indexOf(currentWeek) + 1);
		fireUpdateEvent();
	}


	/**
	 * Moves to the previous week.
	 */
	public void previousWeek() {
		currentWeek = weekList.get(weekList.indexOf(currentWeek) - 1);
		fireUpdateEvent();
	}

	/**
	 * Sets the current week.
	 * <p>
	 * @param currentWeek the current week
	 */
	public void setCurrentWeek(int currentWeek) {
		this.currentWeek = currentWeek;
	}

	/**
	 * Gets valid week numbers from the list of persons.
	 * @return the current week list
	 */
	public List<Integer> getWeekList() {
		List<Integer> weekListCalculated = new ArrayList<>();
		for (Person person : persons) {
			for (Integer weekNumber : person.getWeekData().keySet()) {
				if (!weekListCalculated.contains(weekNumber)) {
					weekListCalculated.add(weekNumber);
				}
			}
		}
		this.weekList = weekListCalculated;
		Collections.sort(weekListCalculated);
		return weekListCalculated;
	}


}
