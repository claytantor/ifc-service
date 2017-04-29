package com.crowcompass.takehome.model;

/**
 * Created by claytongraham on 4/28/17.
 */
public class IFCDate {

    private int year;
    private int month;
    private int day;
    private boolean isLeapDay;
    private boolean isYearDay;


    public IFCDate(int year, int month, int day, boolean isLeapDay, boolean isYearDay) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.isLeapDay = isLeapDay;
        this.isYearDay = isYearDay;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public boolean isLeapDay() {
        return isLeapDay;
    }

    public boolean isYearDay() {
        return isYearDay;
    }
}
