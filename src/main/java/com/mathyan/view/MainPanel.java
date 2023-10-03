package com.mathyan.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class MainPanel extends JPanel {
    JScrollPane scrollPane;
    ScheduleTable scheduleTable;
    JLabel weekLabel;
    JButton previousWeekButton;
    JButton nextWeekButton;
    JButton editScheduleButton;

    public MainPanel() {
        initializeSchedule();
        initializeButtons();
        addItemsToPanel();
        addButtonsToPanel();
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void initializeSchedule() {
        this.setVisible(true);
        scheduleTable = new ScheduleTable(new String[10][8], 00);
        scheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(scheduleTable);
        scrollPane.setPreferredSize(new Dimension(scheduleTable.getPreferredSize().width + 20, 600));
        this.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width + 20, 600));
        scheduleTable.getTableHeader().setReorderingAllowed(false);
    }

    /**
     * Initializes the buttons.
     */
    private void initializeButtons() {
        weekLabel = new JLabel("Week: " + scheduleTable.getWeek() + " ");
        previousWeekButton = new JButton("Previous Week");
        nextWeekButton = new JButton("Next Week");
        editScheduleButton = new JButton("Edit Schedule");
    }

    private void addItemsToPanel() {
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
}
