package com.mathyan.model;

import java.util.ArrayList;

import com.mathyan.model.week_data.Person;

/**
 * This class represents the data table of the application.
 * <p>
 * The data table contains the data of the user.
 */
public class DataTable {
    /**
     * This method returns the current table data.
     */
    public String[][] getDataTable(ArrayList<Person> persons, Integer week){
        int i = 0;
        for (Person person : persons) {
            if(person.getWeekData().containsKey(week)){
                i++;
            }
        }
        String[][] tableData = new String[i][8];
        i = 0;
        for(Person person : persons){
            if(person.getWeekData().containsKey(week)){
                String nameSurname = person.getName() + " " + person.getSurname();
                tableData[i][0] = nameSurname;
                for(int j = 1; j < 8; j++){
                    tableData[i][j] = person.getWeekData().get(week).get(j-1).toTableString();
                }
                i++;
            }
        }
        return tableData;
    }
}
