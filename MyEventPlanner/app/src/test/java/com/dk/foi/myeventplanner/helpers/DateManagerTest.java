package com.dk.foi.myeventplanner.helpers;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public class DateManagerTest {

    private SimpleDateFormat dateFormat;
    private Calendar cal;

    private String dateString;
    private Date actualDate;
    private Date expectedDate;
    private String currentDate;

    @Before
    public void setUp() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        cal = Calendar.getInstance();
    }

    @Test
    public void convertStringToDate() throws Exception {
        dateString = "1/1/2012";
        actualDate = DateManager.convertStringToDate(dateString);
        expectedDate = dateFormat.parse(dateString);

        assertEquals("DateManager failed to convert string to date format.",
                actualDate, expectedDate);

        dateString = "31/12/2014";
        actualDate = DateManager.convertStringToDate(dateString);
        expectedDate = dateFormat.parse(dateString);

        assertEquals("DateManager failed to convert string to date format.",
                actualDate, expectedDate);

        dateString = "14/8/2013";
        actualDate = DateManager.convertStringToDate(dateString);
        expectedDate = dateFormat.parse("14/8/2014");

        assertNotEquals("DateManager failed to convert string to date format.",
                actualDate, expectedDate);

        dateString = "";
        actualDate = DateManager.convertStringToDate(dateString);
        assertNull("DateManager failed to convert string to date format.", actualDate);
    }

    @Test
    public void getTodayDate() throws Exception {
        currentDate = cal.get(Calendar.DAY_OF_MONTH)
                + "/" + (cal.get(Calendar.MONTH) + 1)
                + "/" + cal.get(Calendar.YEAR);
        actualDate = dateFormat.parse(currentDate);
        expectedDate = dateFormat.parse(DateManager.getTodayDate());

        assertEquals("DateManager failed to return correct today's date.", actualDate, expectedDate);
    }
}
