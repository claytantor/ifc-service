package com.crowcompass.takehome.service;

/**
 * Created by claytongraham on 4/28/17.
 */


import com.crowcompass.takehome.model.IFCDate;
import com.crowcompass.takehome.util.InternationalFixedCalendar;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by claytongraham on 10/25/15.
 */
@Service
public class InternationalFixedCalendarService {
    public Map<String,Object> getCalendarDate(int year, int month, int date, String tz_string){

        //"America/Los_Angeles"
        TimeZone tz = TimeZone.getTimeZone(tz_string);
        InternationalFixedCalendar gcal = new InternationalFixedCalendar(tz);

        gcal.set(year,month,date);
        IFCDate ifcLeapDate = gcal.getIFCDate();

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("message", "success");
        model.put("year", ifcLeapDate.getYear());
        model.put("month", ifcLeapDate.getYear());
        model.put("day", ifcLeapDate.getDay());
        model.put("isLeapDay", ifcLeapDate.isLeapDay());
        model.put("isYearDay", ifcLeapDate.isYearDay());

        return model;
    }
}
