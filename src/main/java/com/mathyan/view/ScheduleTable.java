package com.mathyan.view;

import javax.swing.JTable;

/**
 * This class represents the schedule table of the application.
 * <p>
 * The schedule table contains the schedule of the user
 * with headers for the days of the week.
 */
public class ScheduleTable extends JTable{
    private static String[] columnNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private String[][] tableData;
    private Integer week;

    /**
     * Constructs a ScheduleTable object with the given table data and week.
     *
     * @param tableData the table data
     * @param week      the week number
     */
    public ScheduleTable(String[][] tableData, Integer week) {
        super(tableData, columnNames);
        this.tableData = tableData;
        this.week = week;
    }

    /**
     * Updates the table data.
     */
    public void updateTableData(String[][] tableData, Integer week) {
        this.tableData = tableData;
        this.week = week;
        this.repaint();
    }


}
