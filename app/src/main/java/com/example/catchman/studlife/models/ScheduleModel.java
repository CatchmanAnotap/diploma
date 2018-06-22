package com.example.catchman.studlife.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by catchman on 6/18/18.
 */

public class ScheduleModel extends RealmObject {

    private RealmList<DayLessons> days;

    public RealmList<DayLessons> getDays() {
        return days;
    }

    public void setDays(RealmList<DayLessons> days) {
        this.days = days;
    }
}
