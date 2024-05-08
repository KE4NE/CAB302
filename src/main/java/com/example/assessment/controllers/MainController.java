package com.example.assessment.controllers;

import com.calendarfx.view.*;
import com.example.assessment.HelloApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.example.assessment.SceneHelper.setScene;

public class MainController {

    public static final int WIDTH = 845;
    public static final int HEIGHT = 560;

    @FXML
    private Button logout_btn, timer_btn, calendar_btn, settings_btn;
    @FXML
    private HBox calendar_hbox, timer_hbox, settings_hbox, logout_hbox;
    @FXML
    private StackPane main_pane;
    @FXML
    private CalendarFXController calendarfxController;
    @FXML
    private TimerController timerController;

    private boolean calendarBtnBool;
    private boolean timerBtnBool;

    public void initialize() {
        calendarSelected();
    }

    private void applyHoverStyle(HBox hbox, Button btn, boolean hover) {
        String bgColor = hover ? "#74A7BB" : "#C7D4D9";
        String borderStyle = hover ? "1 0 1 0" : "0 0 1 0";
        hbox.setStyle(String.format("-fx-background-color:%s; -fx-border-color: black; -fx-border-width:%s;", bgColor, borderStyle));
        btn.setStyle(String.format("-fx-background-color:%s;", bgColor));
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
        timerBtnBool = false;
        calendar_hbox.setStyle("-fx-background-color:#5F7882; -fx-border-color: black; -fx-border-width:1 0 1 0");
        calendar_btn.setStyle("-fx-background-color:#5F7882;");

        timer_hbox.setStyle("-fx-background-color:#C7D4D9;");
        timer_btn.setStyle("-fx-background-color: #C7D4D9");

        if (calendarfxController != null) {
            Node calendarPane = calendarfxController.getCalendarPane();
            if (calendarPane != null) {
                calendarPane.toFront();
            }
        }
    }

    @FXML
    protected void timerSelected() {
        timerBtnBool = true;
        calendarBtnBool = false;
        timer_hbox.setStyle("-fx-background-color:#5F7882; -fx-border-color: black; -fx-border-width:0 0 1 0");
        timer_btn.setStyle("-fx-background-color:#5F7882;");

        calendar_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:1 0 1 0");
        calendar_btn.setStyle("-fx-background-color:#C7D4D9;");

        if (timerController != null) {
            Node timerPane = timerController.getTimerPane();
            if (timerPane != null) {
                timerPane.toFront();
            }
        }
    }

    @FXML
    protected void hoveredCalendarBtn() {
        if (!calendarBtnBool) {
            applyHoverStyle(calendar_hbox, calendar_btn, true);
        }
    }

    @FXML
    protected void exitedCalendarBtn() {
        if (!calendarBtnBool) {
            applyHoverStyle(calendar_hbox, calendar_btn, false);
        }
    }

    @FXML
    protected void hoveredTimerBtn() {
        if (calendarBtnBool) {
            applyHoverStyle(timer_hbox, timer_btn, true);
        }
    }

    @FXML
    protected void exitedTimerBtn() {
        if (calendarBtnBool) {
            applyHoverStyle(timer_hbox, timer_btn, false);
        }
    }

    @FXML
    protected void hoveredLogoutBtn() {
        applyHoverStyle(logout_hbox, logout_btn, true);
    }

    @FXML
    protected void exitedLogoutBtn() {
        applyHoverStyle(logout_hbox, logout_btn, false);
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
