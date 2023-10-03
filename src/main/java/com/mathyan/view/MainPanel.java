package com.mathyan.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mathyan.controller.ButtonEvent;
import com.mathyan.controller.ButtonEventListener;
import com.mathyan.controller.UpdateEvent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

/**
 * This class represents the main panel of the application.
 * <p>
 * The main panel contains the schedule table and the buttons.
 */
public class MainPanel extends JPanel {
    private JScrollPane scrollPane;
    private ScheduleTable scheduleTable;
    private JLabel weekLabel;
    private JButton previousWeekButton;
    private JButton nextWeekButton;
    private JButton editScheduleButton;

    private List<Integer> weekList;

    /**
     * Constructs a MainPanel object.
     */
    public MainPanel() {
        initializeSchedule();
        initializeButtons();
        addScheduleToPanel();
        addButtonsToPanel();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * Initializes the schedule table.
     */
    private void initializeSchedule() {
        this.setVisible(true);
        scheduleTable = new ScheduleTable(new String[10][8], 00);
        scheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(scheduleTable);
        scrollPane.setPreferredSize(new Dimension(scheduleTable.getPreferredSize().width + 20, 600));
        this.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width + 20, 600));
        scheduleTable.getTableHeader().setReorderingAllowed(false);
    }

    private static final String WEEK_LABEL_PREFIX = "Week: ";
    /**
     * Initializes the buttons.
     */
    private void initializeButtons() {
        weekLabel = new JLabel(WEEK_LABEL_PREFIX + scheduleTable.getWeek() + " ");
        previousWeekButton = new JButton("Previous Week");
        previousWeekButton.setEnabled(false);
        nextWeekButton = new JButton("Next Week");
        nextWeekButton.setEnabled(false);
        editScheduleButton = new JButton("Edit Schedule");
    }

    /**
     * Adds the items to the panel.
     */
    private void addScheduleToPanel() {
        JPanel centarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centarPanel.add(scrollPane);
        this.setLayout(new BorderLayout());
        this.add(centarPanel, BorderLayout.CENTER);
    }

    /**
     * Adds the buttons to the panel.
     */
    private void addButtonsToPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());
        northPanel.setPreferredSize(new Dimension(this.getPreferredSize().width - 50, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        northPanel.add(new JLabel("   "), gbc);

        gbc.gridx = 1;
        northPanel.add(weekLabel, gbc);

        gbc.gridx = 2;
        northPanel.add(new JLabel(" "), gbc);

        gbc.gridx = 3;
        northPanel.add(previousWeekButton, gbc);

        gbc.gridx = 4;
        northPanel.add(new JLabel(" "), gbc);

        gbc.gridx = 5;
        northPanel.add(nextWeekButton, gbc);

        gbc.weightx = 1.0;
        gbc.gridx = 6;
        northPanel.add(new JLabel(" "), gbc);

        gbc.gridx = 7;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        northPanel.add(editScheduleButton, gbc);

        gbc.gridx = 8;
        northPanel.add(new JLabel("   "), gbc);

        this.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Adds the button event listener.
     *
     * @param listener the button event listener
     */
    public void addButtonEventListener(ButtonEventListener listener) {
        previousWeekButton.addActionListener(e -> {
            ButtonEvent event = new ButtonEvent(this, "previousWeek");
            listener.previousWeek(event);
        });
        nextWeekButton.addActionListener(e -> {
            ButtonEvent event = new ButtonEvent(this, "nextWeek");
            listener.nextWeek(event);
        });
        editScheduleButton.addActionListener(e -> {
            ButtonEvent event = new ButtonEvent(this, "editSchedule");
            listener.editWeek(event);
        });
    }

    /**
     * Updates the table data.
     *
     * @param tableData the table data
     * @param week      the week
     */
    public void updateTableData(String[][] tableData, Integer week) {
        scheduleTable.updateTableData(tableData, week);
        weekLabel.setText(WEEK_LABEL_PREFIX + scheduleTable.getWeek() + " ");
        this.revalidate();
        this.repaint();
    }

    /**
     * Displays the current week number.
     * 
     * @param currentWeek the current week
     */
    public void setCurrentWeek(int currentWeek) {
        scheduleTable.setCurrentWeek(currentWeek);
        weekLabel.setText(WEEK_LABEL_PREFIX + scheduleTable.getWeek() + " ");
        this.revalidate();
        this.repaint();
    }

    /**
     * Updates the table.
     *
     * @param e the update event
     */
    public void updateTable(UpdateEvent e) {
        scheduleTable.updateTableData(e.getTableData(), e.getWeek());
        weekLabel.setText(WEEK_LABEL_PREFIX + scheduleTable.getWeek() + " ");
        this.revalidate();
        this.repaint();
        scheduleTable.revalidate();
        scheduleTable.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    /**
     * Checks if buttons are out of bounds.
     */
    public void checkButtons() {
        previousWeekButton.setEnabled(!scheduleTable.getWeek().equals(weekList.get(0)));
        nextWeekButton.setEnabled(!scheduleTable.getWeek().equals(weekList.get(weekList.size() - 1)));
    }

    /**
     * Updates the week list.
     *
     * @param weekList the week list
     */
    public void updateWeekList(List<Integer> weekList) {
        this.weekList = weekList;
    }

    /**
     * Gets the current week.
     *
     * @return the current week
     */
    public int getCurrentWeek() {
        return scheduleTable.getWeek();
    }

}
