package com.mathyan.model.week_data;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * This class represents the data for a single day in a week.
 */
public class DayData implements Serializable {
    private Integer dayNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration durationOfWork;

    /**
     * Constructs a DayData object with the given day number, start time and end time.
     *
     * @param dayNumber the day number of the week (1-7)
     * @param startTime the start time of the day
     * @param endTime the end time of the day
     */
    public DayData(Integer dayNumber, LocalDateTime startTime, LocalDateTime endTime) {
        this.dayNumber = dayNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationOfWork = calculateDurationOfWork(startTime, endTime);
    }

    private Duration calculateDurationOfWork(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Duration getDurationOfWork() {
        return durationOfWork;
    }

    @Override
    public String toString() {
        return "DayData{" +
                "dayNumber=" + dayNumber +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", durationOfWork=" + durationOfWork +
                '}';
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        if (endTime != null) {
            this.durationOfWork = calculateDurationOfWork(startTime, endTime);
        }
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        if (startTime != null) {
            this.durationOfWork = calculateDurationOfWork(startTime, endTime);
        }
    }

    public void setDurationOfWork(Duration durationOfWork) {
        this.durationOfWork = durationOfWork;
        if (startTime != null) {
            this.endTime = startTime.plus(durationOfWork);
        } else if (endTime != null) {
            this.startTime = endTime.minus(durationOfWork);
        }
    }



}
