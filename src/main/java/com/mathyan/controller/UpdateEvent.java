package com.mathyan.controller;

import java.util.EventObject;

/**
 * This class represents an update event.
 * <p>
 * The update event is fired when the schedule table needs to be updated.
 */
public class UpdateEvent extends EventObject{
    private Integer week;
    private String[][] tableData;

    /**
     * Constructs an UpdateEvent object with the given source, week and table data.
     *
     * @param source    the source of the event
     * @param week      the week number
     * @param tableData the table data
     */
    public UpdateEvent(Object source, Integer week, String[][] tableData) {
        super(source);
        this.week = week;
        this.tableData = tableData;
    }
}
