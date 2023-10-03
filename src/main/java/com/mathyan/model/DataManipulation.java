package com.mathyan.model;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mathyan.controller.FileEvent;
import com.mathyan.model.week_data.*;

/**
 * This is a tools class that contains methods to manipulate the data of the
 * application.
 * <p>
 * It is used to sort the data and to calculate the total duration of work.
 * <p>
 * It has methods for getting the data of a single or all persons or weeks.
 * <p>
 * It has methods for adding a new person or week.
 */

public class DataManipulation {
    private DataManipulation() {
        // private constructor to hide the implicit public one
    }

    /**
     * Sorts the list of persons by name.
     * <p>
     * 
     * @param persons the list of persons to be sorted
     */
    public static void sortPersonsByName(List<Person> persons) {
        persons.sort(Comparator.comparing(Person::getName));
    }

    /**
     * Sorts the list of persons by surname.
     * <p>
     * 
     * @param persons the list of persons to be sorted
     */
    public static void sortPersonsBySurname(List<Person> persons) {
        persons.sort(Comparator.comparing(Person::getSurname));
    }

    /**
     * Calculates the total duration of work of a person in a week for a person.
     * <p>
     * When a method is called on a person, the total duration of work is calculated
     * for the person's week data.
     * <p>
     * When a method is called on a list of persons, the total duration of work is
     * calculated for each person's week data and then summed.
     * <p>
     * 
     * @param person     the person whose total duration of work is to be calculated
     * @param weekNumber the week number of the week for which the total duration of
     *                   work is to be calculated
     * @return the total duration of work of the person in the week
     */
    public static Duration calculateTotalDurationOfWork(Person person, Integer weekNumber) {
        Duration totalDurationOfWork = Duration.ZERO;
        for (DayData dayData : person.getWeekData().get(weekNumber)) {
            totalDurationOfWork = totalDurationOfWork.plus(dayData.getDurationOfWork());
        }
        return totalDurationOfWork;
    }

    /**
     * Calculates the total duration of work of all persons in a week.
     * <p>
     * 
     * @see #calculateTotalDurationOfWork(Person, Integer)
     *      <p>
     * @param persons    the list of persons whose total duration of work is to be
     *                   calculated
     * @param weekNumber the week number of the week for which the total duration of
     *                   work is to be calculated
     * @return the total duration of work of all persons in the week
     */
    public static Duration calculateTotalDurationOfWork(List<Person> persons, Integer weekNumber) {
        Duration totalDurationOfWork = Duration.ZERO;
        for (Person person : persons) {
            totalDurationOfWork = totalDurationOfWork.plus(calculateTotalDurationOfWork(person, weekNumber));
        }
        return totalDurationOfWork;
    }

    /**
     * Gets the data of a person in a week.
     * <p>
     * 
     * @param person     the person whose data is to be retrieved
     * @param weekNumber the week number of the week whose data is to be retrieved
     * @return the data of the person in the week
     */
    public static List<DayData> getPersonWeekData(Person person, Integer weekNumber) {
        List<DayData> personWeekData = new ArrayList<>();
        if (person.getWeekData().containsKey(weekNumber)) {
            personWeekData = person.getWeekData().get(weekNumber);
        }
        return personWeekData;
    }
    /**
     * Gets the data of all persons in a week.
     * <p>
     * 
     * @see #getPersonWeekData(Person, Integer)
     *      <p>
     * @param persons    the list of persons whose data is to be retrieved
     * @param weekNumber the week number of the week whose data is to be retrieved
     * @return the data of all persons in the week
     */
    public static List<List<DayData>> getAllPersonsWeekData(List<Person> persons, Integer weekNumber) {
        List<List<DayData>> allPersonsWeekData = new ArrayList<>();
        for (Person person : persons) {
            allPersonsWeekData.add(getPersonWeekData(person, weekNumber));
        }
        return allPersonsWeekData;
    }

    /**
     * Gets data of all persons in a week in a table format.
     * <p>
     * 
     * @param persons    the list of persons whose data is to be retrieved
     * @param weekNumber the week number of the week whose data is to be retrieved
     * @return the data of all persons in the week in a table format
     */
    public static String[][] getAllPersonsWeekDataInTableFormat(List<Person> persons, Integer weekNumber) {
        List<List<DayData>> allPersonsWeekData = getAllPersonsWeekData(persons, weekNumber);
        String[][] allPersonsWeekDataInTableFormat = new String[allPersonsWeekData.size()][8];
        if (!allPersonsWeekData.isEmpty()) { // add null check here
            for (int i = 0; i < allPersonsWeekData.size(); i++) {
                allPersonsWeekDataInTableFormat[i][0] = persons.get(i).getName() + " " + persons.get(i).getSurname();
                for (int j = 1; j < 8; j++) {
                    allPersonsWeekDataInTableFormat[i][j] = allPersonsWeekData.get(i).get(j - 1).toTableString();
                }
            }
        }
        return allPersonsWeekDataInTableFormat;
    }

    /**
     * Converts list of persons to JSON string.
     * <p>
     * Uses the GSON library to convert the list of persons to JSON string.
     * 
     * @param persons the list of persons to be converted
     * @return the JSON string
     */
    public static String convertDataToJson(List<Person> persons) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter());
        builder.registerTypeAdapter(Duration.class, new DurationAdapter());
        Gson gson = builder.create();
        return gson.toJson(persons);
    }

    /**
     * Converts JSON string to list of persons.
     * <p>
     * Uses the GSON library to convert the JSON string to list of persons.
     * 
     * @param json the JSON string to be converted
     * @return the list of persons
     */
    public static List<Person> convertJsonToData(String json) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter());
        builder.registerTypeAdapter(Duration.class, new DurationAdapter());
        Gson gson = builder.create();
        Type personListType = new TypeToken<ArrayList<Person>>() {}.getType();
        List<Person> persons = gson.fromJson(json, personListType);
        persons.forEach(DataManipulation::removeNullDayData);
        return persons;
    }

    /**
     * Removes null day data from the list of persons.
     * 
     * @param person the person whose null day data is to be removed
     */
    private static void removeNullDayData(Person person) {
        person.getWeekData().values().forEach(dayDataList -> {
            if (dayDataList != null) {
                dayDataList.removeIf(Objects::isNull);
            }
        });
    }

    /**
     * Converts list of persons into a JSON strings.
     * And binds the list to the event.
     * <p>
     * 
     * @param e       the file event
     * @param persons the list of persons to be converted
     */
    public static void saveFile(FileEvent e, List<Person> persons) {
        String json = convertDataToJson(persons);
        e.setJson(json);
    }

    /**
     * Converts JSON string to list of persons.
     * <p>
     * Uses the GSON library to convert the JSON string to list of persons.
     * 
     * @param json the JSON string to be converted
     * @return the list of persons
     */
    public static List<Person> openFile(FileEvent e) {
        String json = e.getJson();
        return convertJsonToData(json);
    }

    /**
     * Gets the minimum week number from the list of persons.
     * @param persons
     * @return the minimum week number
     */
    public static int getMinWeek(List<Person> persons) {
        int minWeek = 0;
        if (!persons.isEmpty()) {
            minWeek = Collections.min(persons.get(0).getWeekData().keySet());
            for (Person person : persons) {
                for (Integer weekNumber : person.getWeekData().keySet()) {
                    if (weekNumber < minWeek) {
                        minWeek = weekNumber;
                    }
                }
            }
        }
        return minWeek;
    }

}
