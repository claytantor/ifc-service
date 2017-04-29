package com.crowcompass.takehome.util;

import com.crowcompass.takehome.model.IFCDate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by claytongraham on 4/28/17.
 */
public class InternationalFixedCalendar extends GregorianCalendar {

    private static final int COMMON_MONTH_DAYS     = 28;


    static final int MONTH_LENGTH[][]
            = new int[][]{
            {0,28,0,0},
            {1,28,0,0},
            {2,28,0,0},
            {3,28,0,0},
            {4,28,0,0},
            {5,28,0,0},
            {6,28,0,0},
            {7,28,0,0},
            {8,28,0,0},
            {9,28,0,0},
            {10,28,0,0},
            {11,28,0,0},
            {12,29,0,1}};

    static final int LEAP_MONTH_LENGTH[][]
            = new int[][]{
            {0,28,0,0},
            {1,28,0,0},
            {2,28,0,0},
            {3,28,0,0},
            {4,28,0,0},
            {5,29,1,0},
            {6,28,0,0},
            {7,28,0,0},
            {8,28,0,0},
            {9,28,0,0},
            {10,28,0,0},
            {11,28,0,0},
            {12,29,0,1}};


    public InternationalFixedCalendar(TimeZone zone) {
        super(zone);
    }

    /**
     * whatever the calendar has been set to will return an IFC type date, we dont
     * really need to manage exceptions because we know this is a real date by this
     * point
     *
     *
     * @return
     */
    public IFCDate getIFCDate() {

        int yearOfCal = this.get(Calendar.YEAR);
        int dayOfYear = this.get(Calendar.DAY_OF_YEAR);

        if (isLeapYear(this.get(Calendar.YEAR)))
            return makeIFCDateForYear(LEAP_MONTH_LENGTH, dayOfYear, yearOfCal);
        else
            return makeIFCDateForYear(MONTH_LENGTH, dayOfYear, yearOfCal);
    }



    public IFCDate makeIFCDateForYear(int[][] calEntries, int dayOfYear, int yearOfCal){

        int daysum = 0;
        for (int i = 0; i < calEntries.length; i++) {

            //we passed it
            if (calEntries[i][1] + daysum > dayOfYear) {
                // use previous month we went too far
                int dayOfMonth = dayOfYear - daysum;
                return new IFCDate(yearOfCal, calEntries[i][0], dayOfMonth, false, false);

            }
            else if(calEntries[i][1] + daysum == dayOfYear){ // on the money

                // use previous month we went too far
                int dayOfMonth = dayOfYear - daysum;
                boolean isLeapDay = (dayOfMonth == calEntries[i][1] && calEntries[i][2] == 1);
                boolean isYearDay = (dayOfMonth == calEntries[i][1] && calEntries[i][3] == 1);

                return new IFCDate(yearOfCal, calEntries[i][0], dayOfMonth, isLeapDay, isYearDay);

            }
            else { //not there yet
                daysum += calEntries[i][1];
            }
        }
        throw new RuntimeException("no date found.");
    }
}


