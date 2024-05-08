package com.example.assessment.controllers;

import com.calendarfx.view.*;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class CalendarFXController {

    @FXML
    private StackPane calendarPane;

    private CalendarView calendarView;

    @FXML
    public void initialize() {
        CalendarView calendarView = new CalendarView(CalendarView.Page.DAY, CalendarView.Page.WEEK);
        calendarView.showWeekPage();
        calendarView.setEnableTimeZoneSupport(false);
        calendarView.setCreateEntryClickCount(1);

        DetailedWeekView detailedWeekView = calendarView.getWeekPage().getDetailedWeekView();
        WeekView weekView = detailedWeekView.getWeekView();
        DayView dayView = calendarView.getDayPage().getDetailedDayView().getDayView();

        detailedWeekView.setShowToday(false);
        detailedWeekView.setEarlyLateHoursStrategy(DayViewBase.EarlyLateHoursStrategy.HIDE);

        calendarPane.getChildren().add(calendarView);
    }

    public StackPane getCalendarPane() {
        return calendarPane;
    }
}
