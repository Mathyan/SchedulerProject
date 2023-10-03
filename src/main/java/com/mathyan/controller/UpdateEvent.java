package com.mathyan.controller;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 * This class represents an update event.
 * <p>
 * The update event is fired when the schedule table needs to be updated.
 */
public class UpdateEvent extends EventObject{
    private Integer week;
    private String[][] tableData;
    private ArrayList<Integer> weekList;

    /**
     * Constructs an UpdateEvent object with the given source, week and table data.
     *
     * @param source    the source of the event
     * @param week      the week number
     * @param tableData the table data
     * @param weekList  the week list
     */
    public UpdateEvent(Object source, Integer week, String[][] tableData, List<Integer> weekList) {
        super(source);
        this.weekList = new ArrayList<>(weekList);
        this.week = week;
        this.tableData = tableData;
    }

    /**
     * Gets the week number.
     *
     * @return the week number
     */

    public Integer getWeek() {
        return week;
    }

    /**
     * Gets the table data.
     *
     * @return the table data
     */

    public String[][] getTableData() {
        return tableData;
    }

    /**
     * Gets the week list.
     *
     * @return the week list
     */
    public List<Integer> getWeekList() {
        return weekList;
    }

    /**
     * Sets the week list.
     *
     * @param weekList the week list
     */
    public void setWeekList(List<Integer> weekList) {
        this.weekList = (ArrayList<Integer>) weekList;
    }
}
