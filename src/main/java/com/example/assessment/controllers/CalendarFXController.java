package com.example.assessment.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.*;
import com.example.assessment.HelloApplication;
import com.example.assessment.SqliteEntryDAO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarFXController {

    @FXML
    private StackPane calendarPane;

    private static SqliteEntryDAO calendarDAO = new SqliteEntryDAO();

    private CalendarView calendarView;

    private Object addCalendarEntry(Object evt) {
        return evt;
    }
    @FXML
    public void initialize() {
//        Calendar user = new Calendar("Default");



//        EventHandler<CalendarEvent> handler = evt -> System.out.println(evt.hashCode());
//        user.addEventHandler(handler);
//
//
//        CalendarSource myCalendarSource = new CalendarSource("My Calendars"); // (4)
//        myCalendarSource.getCalendars().addAll(user);
//
//        CalendarView calendarView = new CalendarView(CalendarView.Page.DAY, CalendarView.Page.WEEK);

//        calendarView.setC
//        calendarView.setCa(user);

        // create the calendar and listen to all changes
        Calendar calendar = new Calendar("Default");


        EventHandler<CalendarEvent> handler = evt -> storeCalendarEvent(evt);


        calendar.addEventHandler(handler);

        // create the calendar source and attach the calendar
        CalendarSource source = new CalendarSource("Default");
        source.getCalendars().add(calendar);

        // attach the source to the date control / calendar view.
        CalendarView calendarView= new CalendarView(CalendarView.Page.DAY, CalendarView.Page.WEEK);
        calendarView.getCalendarSources().add(source);


        calendarView.showWeekPage();
        calendarView.setEnableTimeZoneSupport(false);
        calendarView.setCreateEntryClickCount(1);

        DetailedWeekView detailedWeekView = calendarView.getWeekPage().getDetailedWeekView();
        WeekView weekView = detailedWeekView.getWeekView();
        DayView dayView = calendarView.getDayPage().getDetailedDayView().getDayView();

        detailedWeekView.setShowToday(false);
        detailedWeekView.setEarlyLateHoursStrategy(DayViewBase.EarlyLateHoursStrategy.HIDE);

        loadDBEntries(calendar);
        calendarPane.getChildren().add(calendarView);
    }

    public StackPane getCalendarPane() {
        return calendarPane;
    }

    private void storeCalendarEvent(CalendarEvent e) {
        Entry newEntry = e.getEntry();
        String currUser = HelloController.authenticatedUser.getUsername();
        String title = newEntry.getTitle();
        String id = newEntry.getId();
        String startDate = String.valueOf(newEntry.getStartDate());
        String endDate = String.valueOf(newEntry.getEndDate());
        String startTime = String.valueOf(newEntry.getStartTime());
        String endTime = String.valueOf(newEntry.getEndTime());
        calendarDAO.addEntry(currUser, id, title, startDate, endDate, startTime, endTime);
    }


    public void loadDBEntries(Calendar calendar) {
        String currUser = HelloController.authenticatedUser.getUsername();
        ArrayList<ArrayList<String>> DBEntries = calendarDAO.retrieveEntries(currUser);
//        String entryID, title, startDate, endDate, startTime, endTime;
        System.out.println(DBEntries);
        for (ArrayList<String> entry : DBEntries) {
            String entryID = entry.get(0);
            String title = entry.get(1);
            LocalDate startDate = LocalDate.parse(entry.get(2));
            LocalDate endDate = LocalDate.parse(entry.get(3));
            LocalTime startTime = LocalTime.parse(entry.get(4));
            LocalTime endTime = LocalTime.parse(entry.get(5));
            Entry loadedEntry = new Entry();
            loadedEntry.setId(entryID);
            loadedEntry.setTitle(title);
            loadedEntry.setInterval(startDate, startTime, endDate, endTime);
            calendar.addEntry(loadedEntry);
        }
    }



    public void retrieveCalendarEntries() {
//        Entry<String> entry = new Entry<>("Hello");
//        entry.setInterval(LocalDate.now());
//        entry.changeStartDate(LocalDate.now());
//        entry.changeEndDate(LocalDate.now());
//        entry.changeStartTime(LocalTime.of(12,30));
//        entry.changeEndTime(LocalTime.of(13,30));
//        calendar.addEntry(entry);
        return;
    }


    public void loadDBEntries() {
        return;
    }
}
