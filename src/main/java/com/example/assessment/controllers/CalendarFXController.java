package com.example.assessment.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.*;
import com.example.assessment.EntryDAOInterface;
import com.example.assessment.SqliteEntryDAO;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarFXController {

    @FXML
    private StackPane calendarPane;

    public static EntryDAOInterface calendarDAO = new SqliteEntryDAO();

    private CalendarView calendarView;

    private Object addCalendarEntry(Object evt) {
        return evt;
    }

    @FXML
    public void initialize() {
        new Thread(() -> {
            Calendar calendar = new Calendar("Default");

            EventHandler<CalendarEvent> handler = evt -> storeCalendarEvent(evt, calendar);

            calendar.addEventHandler(handler);

//             create the calendar source and attach the calendar
            CalendarSource source = new CalendarSource("Default");
            source.getCalendars().add(calendar);

            // attach the source to the date control / calendar view.
            calendarView = new CalendarView(CalendarView.Page.DAY, CalendarView.Page.WEEK);
            calendarView.getCalendarSources().setAll(source);

            Platform.runLater(() -> {
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
            });
        }).start();
    }

    public StackPane getCalendarPane() {
        return calendarPane;
    }

    private void storeCalendarEvent(CalendarEvent e, Calendar calendar) {
        String currUser = HelloController.authenticatedUser.getUsername();
        Entry newEntry = e.getEntry();
        String title = newEntry.getTitle();
        String id = newEntry.getId();
        if (e.getSource() != calendar) {
            calendarDAO.removeEntry(currUser, id);
        }
        else {
            String startDate = String.valueOf(newEntry.getStartDate());
            String endDate = String.valueOf(newEntry.getEndDate());
            String startTime = String.valueOf(newEntry.getStartTime());
            String endTime = String.valueOf(newEntry.getEndTime());
            calendarDAO.addEntry(currUser, id, title, startDate, endDate, startTime, endTime);
        }
    }

    public void loadDBEntries(Calendar calendar) {
        String currUser = HelloController.authenticatedUser.getUsername();
        ArrayList<ArrayList<String>> DBEntries = calendarDAO.retrieveEntries(currUser);
        //System.out.println(DBEntries);
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
        return;
    }

    public void loadDBEntries() {
        return;
    }
}
