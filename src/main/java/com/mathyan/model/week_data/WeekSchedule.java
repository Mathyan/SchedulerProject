package com.mathyan.model.week_data;

import java.io.Serializable;
import java.util.List;

public class WeekSchedule implements Serializable {
    private WeekSchedule carrier;
    private List<WeekSchedule> personSchedules;

    public WeekSchedule(boolean isCarrier) {
        if (isCarrier) {
            carrier = this;
        }
    }



}
