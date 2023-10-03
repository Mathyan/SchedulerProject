package com.mathyan.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.mathyan.model.week_data.DayData;
import com.mathyan.model.week_data.Person;

/**
 * This class represents the edit person window.
 * <p>
 * The edit person window is used to edit a person's information.
 */
public class EditPersonWindow extends JDialog {
	private int currentWeek;
	private List<Person> persons;
	private JPanel editPersonPanel;
	private JButton saveButton;
	private JButton cancelButton;
	private JButton addPersonButton;
	private JComboBox<String> personComboBox;
	private JTextField nameTextField;
	private JTextField surnameTextField;
	private JTextField weekNumberTextField;
	private JTextField[][] dayTimeTextFields;

	/**
	 * The time format.
	 */
	private static final String TIME_FORMAT = "HH:mm";

	private static final String ERROR_STRING = "Error";

	/**
	 * Constructs an EditPersonWindow object.
	 * 
	 * @param mainWindow the main window of the application
	 * @param persons    the list of persons to edit
	 */
	public EditPersonWindow(JFrame mainWindow, List<Person> persons, Integer week) {
		super(mainWindow, "Edit Person", true);
		this.persons = persons;
		this.currentWeek = week;

		setPreferredSize(new Dimension(500, 500));
		setResizable(false);
		setLocationRelativeTo(mainWindow);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		requestFocus();

		initializePersonForm();
		activateForm();

		pack();
		setVisible(true);
	}

	/**
	 * Initializes the edit person form.
	 */
	private void initializePersonForm() {
		editPersonPanel = new JPanel(new GridBagLayout());
		editPersonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(editPersonPanel);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		editPersonPanel.add(new JLabel("Person:"), c);

		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		personComboBox = new JComboBox<>();
		for (Person person : persons) {
			personComboBox.addItem(person.toString());
		}
		editPersonPanel.add(personComboBox, c);

		c.gridx = 3;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		addPersonButton = new JButton("Add Person");
		editPersonPanel.add(addPersonButton, c);

		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		editPersonPanel.add(new JLabel("Name:"), c);

		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		nameTextField = new JTextField();
		editPersonPanel.add(nameTextField, c);

		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		editPersonPanel.add(new JLabel("Surname:"), c);

		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		surnameTextField = new JTextField();
		editPersonPanel.add(surnameTextField, c);

		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_END;
		editPersonPanel.add(new JLabel("Week Number:"), c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		weekNumberTextField = new JTextField();
		editPersonPanel.add(weekNumberTextField, c);

		// Add fields for each day of the week
		String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		dayTimeTextFields = new JTextField[daysOfWeek.length][2];
		for (int i = 0; i < daysOfWeek.length; i++) {
			String day = daysOfWeek[i];
			c.gridx = 0;
			c.gridy = 3 + i;
			c.anchor = GridBagConstraints.LINE_END;
			editPersonPanel.add(new JLabel(day + ":"), c);

			c.gridx = 1;
			c.gridy = 3 + i;
			c.anchor = GridBagConstraints.LINE_START;
			JTextField startTimeTextField = new JTextField();
			startTimeTextField.setMinimumSize(new Dimension(100, 20));
			startTimeTextField.setPreferredSize(new Dimension(100, 20));
			startTimeTextField.setMaximumSize(new Dimension(100, 20));
			c.fill = GridBagConstraints.HORIZONTAL;
			editPersonPanel.add(startTimeTextField, c);
			dayTimeTextFields[i][0] = startTimeTextField;

			c.gridx = 2;
			c.gridy = 3 + i;
			c.anchor = GridBagConstraints.LINE_END;
			editPersonPanel.add(new JLabel("-"), c);

			c.gridx = 3;
			c.gridy = 3 + i;
			c.anchor = GridBagConstraints.LINE_START;
			JTextField endTimeTextField = new JTextField();
			endTimeTextField.setMinimumSize(new Dimension(100, 20));
			endTimeTextField.setPreferredSize(new Dimension(100, 20));
			endTimeTextField.setMaximumSize(new Dimension(100, 20));
			c.fill = GridBagConstraints.HORIZONTAL;
			editPersonPanel.add(endTimeTextField, c);
			dayTimeTextFields[i][1] = endTimeTextField;
		}

		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		saveButton = new JButton("Save");
		editPersonPanel.add(saveButton, c);

		c.gridx = 2;
		c.gridy = 10;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		cancelButton = new JButton("Cancel");
		editPersonPanel.add(cancelButton, c);
	}

	/**
	 * Activates the edit person form.
	 */
	private void activateForm() {
		addComboBoxListener();
		addPersonButtonListener();
		addCancelButtonListener();
		addSaveButtonListener();
	}

	/**
	 * Adds a listener to the person combo box.
	 */
	private void addComboBoxListener() {
		personComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				handleComboBoxSelection();
			}
		});
	}

	/**
	 * Handles the selection of the person combo box.
	 */
	private void handleComboBoxSelection() {
		int selectedIndex = personComboBox.getSelectedIndex();
		if (selectedIndex >= 0 && selectedIndex < persons.size()) {
			Person selectedPerson = persons.get(selectedIndex);
			nameTextField.setText(selectedPerson.getName());
			surnameTextField.setText(selectedPerson.getSurname());
			weekNumberTextField.setText(Integer.toString(currentWeek));
			setDayTimeTextFields(selectedPerson);
		}
	}

	/**
	 * Sets the day time text fields for the specified person.
	 * 
	 * @param selectedPerson the person to set the day time text fields for
	 */
	private void setDayTimeTextFields(Person selectedPerson) {
		IntStream.range(0, dayTimeTextFields.length)
				.forEach(i -> {
					String startTime = selectedPerson.getWeekData().get(currentWeek).get(i).getStartTime()
							.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
					String endTime = selectedPerson.getWeekData().get(currentWeek).get(i).getEndTime()
							.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
					setStartTime(i, startTime);
					setEndTime(i, endTime);
				});
	}

	/**
	 * Adds a listener to the add person button.
	 */
	private void addPersonButtonListener() {
		addPersonButton.addActionListener(e -> initializeFieldsForNewPerson());
	}

	/**
	 * Initializes the fields for a new person.
	 */
	private void initializeFieldsForNewPerson() {
		nameTextField.setText("Name");
		surnameTextField.setText("not mandatory delete");
		weekNumberTextField.setText(Integer.toString(currentWeek));
		IntStream.range(0, dayTimeTextFields.length)
				.forEach(i -> {
					setStartTime(i, "00:00");
					setEndTime(i, "00:00");
				});
	}

	/**
	 * Adds a listener to the cancel button.
	 */
	private void addCancelButtonListener() {
		cancelButton.addActionListener(e -> {
			dispose();
		});
	}

	/**
	 * Adds a listener to the save button.
	 */
	private void addSaveButtonListener() {
		saveButton.addActionListener(e -> {
			handleSaveButtonClick();
		});
	}

	/**
	 * Handles the save button click.
	 */
	private void handleSaveButtonClick() {
		if (!validateInputFields()) {
			return;
		}

		String name = nameTextField.getText();
		String surname = surnameTextField.getText().equals("not mandatory delete") ? null : surnameTextField.getText();
		int weekNumberInt = Integer.parseInt(weekNumberTextField.getText());

		List<String> errors = new ArrayList<>();
		List<LocalTime> startTimes = new ArrayList<>();
		List<LocalTime> endTimes = new ArrayList<>();

		IntStream.range(0, dayTimeTextFields.length)
				.forEach(i -> {
					String startTime = getStartTime(i);
					String endTime = getEndTime(i);
					if (!isValidTimeFormat(startTime) || !isValidTimeFormat(endTime)) {
						errors.add("Invalid time format for day " + (i + 1) + ".");
					} else {
						LocalTime[] times = parseTimes(startTime, endTime);
						startTimes.add(times[0]);
						endTimes.add(times[1]);
					}
				});

		if (!errors.isEmpty()) {
			String errorMessage = String.join("\n", errors);
			JOptionPane.showMessageDialog(this, errorMessage, ERROR_STRING, JOptionPane.ERROR_MESSAGE);
			return;
		}

		handlePersonData(name, surname, weekNumberInt, startTimes, endTimes);
		JOptionPane.showMessageDialog(this, "Person saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Validates the input fields.
	 *
	 * @return true if the input fields are valid, false otherwise
	 */
	private boolean validateInputFields() {
		String name = nameTextField.getText();
		String weekNumber = weekNumberTextField.getText();

		if (name.equals("Name") || weekNumber.equals("\\d+")) {
			JOptionPane.showMessageDialog(this, "Please enter a name and week number.", ERROR_STRING,
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		int weekNumberInt;
		try {
			weekNumberInt = Integer.parseInt(weekNumber);
			if (weekNumberInt < 1 || weekNumberInt > 52) {
				JOptionPane.showMessageDialog(this, "Please enter a valid week number.", ERROR_STRING,
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid week number.", ERROR_STRING,
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Checks if the time is in the correct format.
	 *
	 * @param time the time to check
	 * @return true if the time is in the correct format, false otherwise
	 */
	private boolean isValidTimeFormat(String time) {
		return time.matches("\\d{1,2}:\\d{2}");
	}

	/**
	 * Handles the person data.
	 * 
	 * @param name           the name of the person
	 * @param surname        the surname of the person
	 * @param weekNumberInt  the week number
	 * @param startTimes     the start times for each day of the week
	 * @param endTimes       the end times for each day of the week
	 */
	private void handlePersonData(String name, String surname, int weekNumberInt, List<LocalTime> startTimes,
			List<LocalTime> endTimes) {
		Person person = findOrCreatePerson(name, surname);
		updatePersonData(person, weekNumberInt, startTimes, endTimes);
	}

	/**
	 * Finds or creates a person.
	 * 
	 * @param name    the name of the person
	 * @param surname the surname of the person
	 * @return the person
	 */
	private Person findOrCreatePerson(String name, String surname) {
		if (persons.isEmpty()) {
			Person newPerson = new Person(name, surname);
			persons.add(newPerson);
			return newPerson;
		}
		for (Person person : persons) {
			if (person.getName().equals(name) && Objects.equals(person.getSurname(), surname)) {
				return person;
			}
		}

		Person newPerson = new Person(name, surname);
		persons.add(newPerson);
		return newPerson;
	}

	/**
	 * Updates the person data.
	 * 
	 * @param person         the person to update
	 * @param weekNumberInt  the week number
	 * @param startTimes     the start times for each day of the week
	 * @param endTimes       the end times for each day of the week
	 */
	private void updatePersonData(Person person, int weekNumberInt, List<LocalTime> startTimes,
			List<LocalTime> endTimes) {
		if (!person.getWeekData().containsKey(weekNumberInt)) {
			person.getWeekData().put(weekNumberInt, new ArrayList<>());
		}

		if (person.getWeekData().get(weekNumberInt).isEmpty()) {
			IntStream.range(0, dayTimeTextFields.length)
					.forEach(i -> {
						DayData dayTime = new DayData();
						dayTime.setStartTime(startTimes.get(i));
						dayTime.setEndTime(endTimes.get(i));
						person.getWeekData().get(weekNumberInt).add(dayTime);
					});
		} else {
			IntStream.range(0, dayTimeTextFields.length)
					.forEach(i -> {
						person.getWeekData().get(weekNumberInt).get(i).setStartTime(startTimes.get(i));
						person.getWeekData().get(weekNumberInt).get(i).setEndTime(endTimes.get(i));
					});
		}
		personComboBox.addItem(person.toString());
		personComboBox.setSelectedIndex(personComboBox.getItemCount() - 1);
	}

	/**
	 * Parses the times from the String.
	 * And adjusts the time if end time is before start time.
	 * 
	 * @param startTime the start time for the specified day of the week
	 * @param endTime   the end time for the specified day of the week
	 * @return the list of times
	 */
	private LocalTime[] parseTimes(String startTime, String endTime) {
		LocalTime[] times = new LocalTime[2];
		System.out.println(startTime);
		System.out.println(endTime);
		times[0] = LocalTime.parse(startTime, DateTimeFormatter.ofPattern(TIME_FORMAT));
		times[1] = LocalTime.parse(endTime, DateTimeFormatter.ofPattern(TIME_FORMAT));
		if (times[1].isBefore(times[0])) {
			times[1] = times[1].plusHours(24);
		}
		return times;
	}

	/**
	 * Gets the start time for the specified day of the week.
	 * 
	 * @param dayOfWeek the day of the week (0 = Monday, 1 = Tuesday, etc.)
	 * @return the start time for the specified day of the week
	 */
	public String getStartTime(int dayOfWeek) {
		return dayTimeTextFields[dayOfWeek][0].getText();
	}

	/**
	 * Gets the end time for the specified day of the week.
	 * 
	 * @param dayOfWeek the day of the week (0 = Monday, 1 = Tuesday, etc.)
	 * @return the end time for the specified day of the week
	 */
	public String getEndTime(int dayOfWeek) {
		return dayTimeTextFields[dayOfWeek][1].getText();
	}

	/**
	 * Sets the start time for the specified day of the week.
	 * 
	 * @param dayOfWeek the day of the week (0 = Monday, 1 = Tuesday, etc.)
	 * @param startTime the start time for the specified day of the week
	 */
	public void setStartTime(int dayOfWeek, String startTime) {
		dayTimeTextFields[dayOfWeek][0].setText(startTime);
	}

	/**
	 * Sets the end time for the specified day of the week.
	 * 
	 * @param dayOfWeek the day of the week (0 = Monday, 1 = Tuesday, etc.)
	 * @param endTime   the end time for the specified day of the week
	 */
	public void setEndTime(int dayOfWeek, String endTime) {
		dayTimeTextFields[dayOfWeek][1].setText(endTime);
	}
}
