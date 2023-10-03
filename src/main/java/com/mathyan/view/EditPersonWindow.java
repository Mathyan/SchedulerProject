package com.mathyan.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.mathyan.model.week_data.Person;

/**
 * This class represents the edit person window.
 * <p>
 * The edit person window is used to edit a person's information.
 */
public class EditPersonWindow extends JDialog { 
    private List<Person> persons;
    private JPanel editPersonPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox<String> personComboBox;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    


    /**
     * Constructs an EditPersonWindow object.
     * @param persons
     * @param windowFrame
     */
    public EditPersonWindow(JFrame mainWindow, List<Person> persons) {
        super(mainWindow, "Edit Person" , true);
        setVisible(true);
        setSize(300, 300);
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        requestFocus();
        this.persons = new ArrayList<>(persons);
        initializePersonForm();
    }

    /**
     * Initializes the edit person form.
     */
    private void initializePersonForm() {
        editPersonPanel = new JPanel();
        editPersonPanel.setLayout(new BoxLayout(editPersonPanel, BoxLayout.Y_AXIS));
        editPersonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(editPersonPanel);

        JLabel personLabel = new JLabel("Person:");
        editPersonPanel.add(personLabel);

        personComboBox = new JComboBox<>();
        for (Person person : persons) {
            personComboBox.addItem(person.getName() + " " + person.getSurname());
        }
        editPersonPanel.add(personComboBox);

        JLabel nameLabel = new JLabel("Name:");
        editPersonPanel.add(nameLabel);

        nameTextField = new JTextField();
        editPersonPanel.add(nameTextField);

        JLabel surnameLabel = new JLabel("Surname:");
        editPersonPanel.add(surnameLabel);

        surnameTextField = new JTextField();
        editPersonPanel.add(surnameTextField);

        saveButton = new JButton("Save");
        editPersonPanel.add(saveButton);

        cancelButton = new JButton("Cancel");
        editPersonPanel.add(cancelButton);
    }


}
