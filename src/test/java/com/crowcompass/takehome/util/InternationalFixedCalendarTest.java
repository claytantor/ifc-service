package com.crowcompass.takehome.util;

import com.crowcompass.takehome.model.IFCDate;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by claytongraham on 4/28/17.
 */
public class InternationalFixedCalendarTest {

    private static SimpleDateFormat COMMON_DATE_FORMAT  = new SimpleDateFormat("d MMM yyyy");

    @Test
    public void testNumberOfMonthsGregorian() {
        TimeZone tz_pacific = TimeZone.getTimeZone("America/Los_Angeles");
        Calendar gcal = GregorianCalendar.getInstance(tz_pacific);
        gcal.set(2015,12,10); //Wed, 10 Feb 2016 12 IS NOT DECEMBER!!!
        assertEquals("10 Jan 2016", COMMON_DATE_FORMAT.format(gcal.getTime()));

        //this should roll over for this calendar type
        assertEquals(0, gcal.get(Calendar.MONTH));
        assertEquals(2016, gcal.get(Calendar.YEAR));


    }


    /**
     *
     *
     *        GregorianCalendar gregorianDate = new GregorianCalendar(2015, 0, 31);
     *        IFCDate ifcDate = new IFCDate(date); (date? this should be gregorianDate)
     *
     *        ifcDate.month
     *         #=> 2 (making this 1 for sanity)
     *            ifcDate.day
     *         #=> 3
     *
     * the original requirements test, a little weird because it is setting month to 0
     * for gregoring and expecting 2,3 instead of 1,3 which would be the 3rd day
     * of the second month for us nerds. I am going to test and implement the right way
     * for the ifc cal, 0=the first month not 1
     *
     */
    @Test
    public void testBasicIFCDateConversion() {
        TimeZone tz_pacific = TimeZone.getTimeZone("America/Los_Angeles");
        InternationalFixedCalendar gcal = new InternationalFixedCalendar(tz_pacific);
        gcal.set(2015,0,31);
        assertEquals("31 Jan 2015", COMMON_DATE_FORMAT.format(gcal.getTime()));

        IFCDate ifcDate = gcal.getIFCDate();

        // bad requirement, expect 1 not 2
        assertEquals(1, ifcDate.getMonth());
        assertEquals(3, ifcDate.getDay());

    }

    @Test
    /***
     *
     *       GregorianCalendar gregorianDate = new GregorianCalendar(2015,11,31)
     *       IFCDate ifcDate = new IFCDate(date);
     *
     *       ifc_date.month
     *       #=> 13 ( NO!!!!! bad requirement, making this 12 for sanity)
     *       ifc_date.day
     *       #=> 29
     *       ifc_date.year_day?
     *       #=> true
     *
     * This is the year day test, only one day should be year day, the 29th
     * day of December (month 13 [12])
     */
    public void testYearDayIFCDateConversion() {

        TimeZone tz_pacific = TimeZone.getTimeZone("America/Los_Angeles");
        InternationalFixedCalendar gcal = new InternationalFixedCalendar(tz_pacific);

        // bad requirement, expect month 12 not 13
        // YEAR DAY
        gcal.set(2015,11,31);
        assertEquals("31 Dec 2015", COMMON_DATE_FORMAT.format(gcal.getTime()));
        IFCDate ifcDate = gcal.getIFCDate();
        assertEquals(12, ifcDate.getMonth());
        assertEquals(29, ifcDate.getDay());
        assertEquals(true, ifcDate.isYearDay());

        // DAY BEFORE
        gcal.set(2015,11,30);
        assertEquals("30 Dec 2015", COMMON_DATE_FORMAT.format(gcal.getTime()));
        IFCDate ifcDateBefore = gcal.getIFCDate();
        assertEquals(12, ifcDateBefore.getMonth());
        assertEquals(28, ifcDateBefore.getDay());
        assertEquals(false, ifcDateBefore.isYearDay());


    }

    @Test
    /***
     * This
     */
    public void testLeapYearIFCDateConversion() {

        TimeZone tz_pacific = TimeZone.getTimeZone("America/Los_Angeles");
        InternationalFixedCalendar gcal = new InternationalFixedCalendar(tz_pacific);

        // LEAP DAY
        gcal.set(Calendar.DAY_OF_YEAR, 169);
        gcal.set(Calendar.YEAR, 2016);
        assertEquals("17 Jun 2016", COMMON_DATE_FORMAT.format(gcal.getTime()));
        IFCDate ifcLeapDate = gcal.getIFCDate();
        assertEquals(5, ifcLeapDate.getMonth());
        assertEquals(29, ifcLeapDate.getDay());
        assertEquals(true, ifcLeapDate.isLeapDay());

        // YEAR DAY
        gcal.set(2016,11,31);
        assertEquals("31 Dec 2016", COMMON_DATE_FORMAT.format(gcal.getTime()));
        IFCDate ifcDate = gcal.getIFCDate();
        assertEquals(12, ifcDate.getMonth());
        assertEquals(29, ifcDate.getDay());
        assertEquals(true, ifcDate.isYearDay());

        // DAY BEFORE YEAR DAY
        gcal.set(2016,11,30);
        assertEquals("30 Dec 2016", COMMON_DATE_FORMAT.format(gcal.getTime()));
        IFCDate ifcDateBefore = gcal.getIFCDate();
        assertEquals(12, ifcDateBefore.getMonth());
        assertEquals(28, ifcDateBefore.getDay());
        assertEquals(false, ifcDateBefore.isYearDay());

    }


}
