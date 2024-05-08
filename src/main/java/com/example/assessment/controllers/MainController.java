package com.example.assessment.controllers;

import com.calendarfx.view.*;
import com.example.assessment.HelloApplication;
import fr.brouillard.oss.cssfx.CSSFX;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

public class MainController {

    public static final int WIDTH = 845;

    public static final int HEIGHT = 560;

    @FXML
    private Button logout_btn;

    @FXML
    private HBox calendar_hbox;

    @FXML
    private HBox stats_hbox;

    @FXML
    private HBox settings_hbox;

    @FXML
    private Button stats_btn;

    @FXML
    private HBox logout_hbox;

    @FXML
    private Button calendar_btn;

    @FXML
    private Button settings_btn;

    @FXML
    private StackPane main_pane;

    @FXML
    private Pane calendarfx_pane;

    @FXML
    private Pane stats_pane;
    private boolean calendarBtnBool;

    public void initialize() {
        calendarSelected();
        CalendarView calendarView = new CalendarView(CalendarView.Page.DAY, CalendarView.Page.WEEK);
        calendarView.showWeekPage();
        calendarView.setEnableTimeZoneSupport(false);
        calendarView.setCreateEntryClickCount(1);

        DetailedWeekView detailedWeekView = calendarView.getWeekPage().getDetailedWeekView();
        WeekView weekView = detailedWeekView.getWeekView();
        DayView dayView = calendarView.getDayPage().getDetailedDayView().getDayView();

        detailedWeekView.setShowToday(false);
        detailedWeekView.setEarlyLateHoursStrategy(DayViewBase.EarlyLateHoursStrategy.HIDE);

        calendarfx_pane.getChildren().addAll(calendarView); // introPane);
        calendarfx_pane.toFront();
    }

    @FXML
    protected void logoutClicked() throws IOException {
        HelloController.authenticatedUser = null;
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        setScene(stage, "startup_view.fxml", HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.centerOnScreen();
    }

    @FXML
    protected void calendarSelected() {
        calendarBtnBool = true;
        calendar_hbox.setStyle("-fx-background-color:#5F7882; -fx-border-color: black; -fx-border-width:1 0 1 0");
        calendar_btn.setStyle("-fx-background-color:#5F7882;");

        stats_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:0 0 1 0");
        stats_btn.setStyle("-fx-background-color: #C7D4D9");
    }

    @FXML
    protected void statisticsSelected() {
        calendarBtnBool = false;
        stats_hbox.setStyle("-fx-background-color:#5F7882; -fx-border-color: black; -fx-border-width:0 0 1 0");
        stats_btn.setStyle("-fx-background-color:#5F7882;");

        calendar_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:1 0 1 0");
        calendar_btn.setStyle("-fx-background-color:#C7D4D9;");
    }

    @FXML
    protected void hoveredCalendarBtn() {
        if (!calendarBtnBool) {
            calendar_hbox.setStyle("-fx-background-color:#74A7BB; -fx-border-color: black; -fx-border-width:1 0 1 0");
            calendar_btn.setStyle("-fx-background-color:#74A7BB;");
        }

    }

    @FXML
    protected void exitedCalendarBtn() {
        if (!calendarBtnBool) {
            calendar_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:1 0 1 0");
            calendar_btn.setStyle("-fx-background-color:#C7D4D9;");
        }
    }

    @FXML
    protected void hoveredStatsBtn() {
        if (calendarBtnBool) {
            stats_hbox.setStyle("-fx-background-color:#74A7BB; -fx-border-color: black; -fx-border-width:0 0 1 0");
            stats_btn.setStyle("-fx-background-color:#74A7BB;");
        }

        stats_pane.toFront();



    }

    @FXML
    protected void exitedStatsBtn() {
        if (calendarBtnBool) {
            stats_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:0 0 1 0");
            stats_btn.setStyle("-fx-background-color:#C7D4D9;");
        }
    }
    @FXML
    protected void hoveredLogoutBtn() {
        logout_hbox.setStyle("-fx-background-color:#74A7BB; -fx-border-color: black; -fx-border-width:0 0 1 0");
        logout_btn.setStyle("-fx-background-color:#74A7BB;");
    }

    @FXML
    protected void exitedLogoutBtn() {
        logout_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:0 0 1 0");
        logout_btn.setStyle("-fx-background-color:#C7D4D9;");
    }
    @FXML
    protected void SettingsClicked() throws IOException {
        Stage stage = (Stage) settings_btn.getScene().getWindow();
        stage.setTitle("User settings");
        setScene(stage, "user_settings.fxml", SettingsController.WIDTH, SettingsController.HEIGHT);
        stage.centerOnScreen();
    }

    @FXML
    protected void hoveredSettingsBtn() {
        settings_hbox.setStyle("-fx-background-color:#74A7BB; -fx-border-color: black; -fx-border-width:0 0 1 0");
    }

    @FXML
    protected void exitedSettingsBtn() {
        settings_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:0 0 1 0");

    }
}
