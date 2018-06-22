package com.example.catchman.studlife.models;

import io.realm.RealmObject;

/**
 * Created by catchman on 6/18/18.
 */

public class DayLessons extends RealmObject {

    private String first;
    private String second;
    private String third;
    private String fourth;
    private String fifth;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }
}
