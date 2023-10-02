package com.mathyan.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainPanel extends JPanel{
    JScrollPane scrollPane;
    ScheduleTable scheduleTable;
    /**
     * Constructs a MainPanel object.
     */
    public MainPanel() {
        initialize();
    }

    /**
     * Initializes the main panel.
     */
    private void initialize() {
        this.setSize(1200, 800);
        this.setVisible(true);
        ScheduleTable scheduleTable = new ScheduleTable(new String[7][7], 1);
        scrollPane = new JScrollPane(scheduleTable);
        this.add(scrollPane);
    }

}
