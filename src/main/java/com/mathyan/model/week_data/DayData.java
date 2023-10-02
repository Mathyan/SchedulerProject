package com.mathyan.model.week_data;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * This class represents the data for a single day in a week.
 * <p>
 * The day number is the number of the day in the week (1-7).
 * The start time and end time are the times at which the user started and
 * stopped working on the day.
 * The duration of work is the amount of time the user worked on the day.
 * The start time and end time are mutually exclusive. If one is set, the other
 * is calculated from it. If both are set, the duration of work is calculated
 * from them.
 * The start time and end time are stored as LocalDateTime objects. The duration
 * of work is stored as a Duration object.
 * This class implements the Serializable interface so that it can be
 * serialized and deserialized.
 */
public class DayData implements Serializable {
    /**
     * The day number of the week (1-7).
     */
    private Integer dayNumber;

    /**
     * The start time of the work day.
     * <p>
     * All points in time are LocalDateTime objects.
     */
    private LocalDateTime startTime;

    /**
     * The end time of the work day.
     * <p>
     * @see #getStartTime()
     */
    private LocalDateTime endTime;

    /**
     * The duration of work on the day.
     * <p>
     * All durations are Duration objects.
     * <p>
     * @see #getStartTime()
     */
    private Duration durationOfWork;

    /**
     * Constructs a DayData object with the given day number, start time and end
     * time.
     *
     * @param dayNumber the day number of the week (1-7)
     * @param startTime the start time of the day
     * @param endTime   the end time of the day
     */
    public DayData(Integer dayNumber, LocalDateTime startTime,
            LocalDateTime endTime) {
        this.dayNumber = dayNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationOfWork = calculateDurationOfWork(startTime, endTime);
    }

    /**
     *Empty constructor for DayData.
     */
    public DayData() {
    }

    /**
     * Gets the day number of the week (1-7).
     * @return the day number of the week (1-7)
     */
    public Integer getDayNumber() {
        return dayNumber;
    }

    /**
     * Gets the start time of the work day.
     * <p>
     * All points in time are LocalDateTime objects.
     * <p>
     * @return the start time of the work day
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @return the end time of the work day
     * @see #getStartTime()
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Gets the duration of work on the day.
     * <p>
     * All durations are Duration objects.
     * <p>
     * @see #getStartTime()
     * @return the duration of work on the day
     */
    public Duration getDurationOfWork() {
        return durationOfWork;
    }

    /**
     * Sets the day number of the week (1-7).
     * <p>
     * @param dayNumber the day number of the week (1-7)
     */
    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    /**
     * Sets the start time of the work day.
     * <p>
     * If the end time is already set, the duration of work is calculated from
     * the start time and end time.
     * <p>
     * @see #getStartTime()
     * @param startTime the start time of the work day
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        if (endTime != null) {
            this.durationOfWork = calculateDurationOfWork(startTime, endTime);
        }
    }

    /**
     * Sets the end time of the work day.
     * <p>
     * If the start time is already set, the duration of work is calculated from
     * the start time and end time.
     * <p>
     * @see #getStartTime()
     * @param endTime the end time of the work day
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        if (startTime != null) {
            this.durationOfWork = calculateDurationOfWork(startTime, endTime);
        }
    }

    /**
     * Sets the duration of work on the day.
     * <p>
     * If the start time is already set, the end time is calculated from the
     * start time and duration of work.
     * <p>
     * If the end time is already set, the start time is calculated from the
     * end time and duration of work.
     * <p>
     * @see #getStartTime()
     * @param durationOfWork the duration of work on the day
     */
    public void setDurationOfWork(Duration durationOfWork) {
        this.durationOfWork = durationOfWork;
        if (startTime != null) {
            this.endTime = startTime.plus(durationOfWork);
        } else if (endTime != null) {
            this.startTime = endTime.minus(durationOfWork);
        }
    }

    /**
     * Simple string representation of the DayData object.
     * @return a string representation of the DayData object
     */
    @Override
    public String toString() {
        return "DayData{"
                + "dayNumber=" + dayNumber + ", startTime=" + startTime +
                ", endTime=" + endTime + ", durationOfWork=" + durationOfWork + '}';
    }

    /**
     * Calculates the duration of work from the start time and end time.
     * <p>
     * @param startTime the start time of the work day
     * @param endTime   the end time of the work day
     * @return the duration of work on the day
     */
    private Duration calculateDurationOfWork(LocalDateTime startTime,
            LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }
}
