package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

/**
 * Controller class for handling user interactions on the main application screen.
 */
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

    @FXML
    private SettingsController settingsController;

    private boolean calendarBtnBool;
    private boolean timerBtnBool;

    private boolean settingsBtnBool;

    /**
     * Initializes the main controller by selecting the calendar view by default.
     */
    public void initialize() {
        calendarSelected();
    }

    /**
     * Applies hover style to the given HBox and Button.
     *
     * @param hbox  The HBox to apply the style to.
     * @param btn   The Button to apply the style to.
     * @param hover Whether to apply the hover style or not.
     */
    private void applyHoverStyle(HBox hbox, Button btn, String border, boolean hover) {
        String bgColor = hover ? "#74A7BB" : "#C7D4D9";
        hbox.setStyle(String.format("-fx-background-color:%s; -fx-border-color:black; -fx-border-width:%s;", bgColor, border));
        btn.setStyle(String.format("-fx-background-color:%s;", bgColor));
    }

    /**
     * Handles the logout button click event.
     * Logs out the user and returns to the startup view.
     *
     * @throws IOException if there is an error loading the startup view.
     */
    @FXML
    protected void logoutClicked() throws IOException {
        HelloController.authenticatedUser = null;
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        setScene(stage, "startup_view.fxml", HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.centerOnScreen();
    }

    /**
     * Handles the selection of the calendar view.
     * Updates the UI to reflect the selected view.
     */
    @FXML
    protected void calendarSelected() {
        calendarBtnBool = true;
        timerBtnBool = false;
        settingsBtnBool = false;
        calendar_hbox.setStyle("-fx-background-color:#5F7882; -fx-border-color:black; -fx-border-width: 1 0 1 0");
        calendar_btn.setStyle("-fx-background-color:#5F7882;");

        timer_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color:black; -fx-border-width: 0 0 1 0");
        timer_btn.setStyle("-fx-background-color: #C7D4D9");

        if (calendarfxController != null) {
            Node calendarPane = calendarfxController.getCalendarPane();
            if (calendarPane != null) {
                calendarPane.toFront();
            }
        }
    }

    /**
     * Handles the selection of the timer view.
     * Updates the UI to reflect the selected view.
     */
    @FXML
    protected void timerSelected() {
        timerBtnBool = true;
        calendarBtnBool = false;
        settingsBtnBool = false;

        timer_hbox.setStyle("-fx-background-color:#5F7882; -fx-border-color:black; -fx-border-width: 0 0 1 0");
        timer_btn.setStyle("-fx-background-color:#5F7882;");

        calendar_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color:black; -fx-border-width: 1 0 1 0");
        calendar_btn.setStyle("-fx-background-color:#C7D4D9;");

        if (timerController != null) {
            Node timerPane = timerController.getTimerPane();
            if (timerPane != null) {
                timerPane.toFront();
            }
        }
    }

    /**
     * Handles the hover event for the calendar button.
     */
    @FXML
    protected void hoveredCalendarBtn() {
        String border = "1 0 1 0;";
        if (!calendarBtnBool || !settingsBtnBool) {
            applyHoverStyle(calendar_hbox, calendar_btn, border, true);
        }
    }

    /**
     * Handles the exit hover event for the calendar button.
     */
    @FXML
    protected void exitedCalendarBtn() {
        String border = "1 0 1 0;";
        if (!calendarBtnBool) {
            applyHoverStyle(calendar_hbox, calendar_btn, border,  false);
        }
    }

    /**
     * Handles the hover event for the timer button.
     */
    @FXML
    protected void hoveredTimerBtn() {
        String border = "0 0 1 0;";
        if (calendarBtnBool || settingsBtnBool) {
            applyHoverStyle(timer_hbox, timer_btn, border, true);
        }
    }

    /**
     * Handles the exit hover event for the timer button.
     */
    @FXML
    protected void exitedTimerBtn() {
        String border = "0 0 1 0;";
        if (calendarBtnBool || settingsBtnBool) {
            applyHoverStyle(timer_hbox, timer_btn, border, false);
        }
    }

    /**
     * Handles the hover event for the logout button.
     */
    @FXML
    protected void hoveredLogoutBtn() {
        String border = "0 0 1 0;";
        applyHoverStyle(logout_hbox, logout_btn, border, true);
    }

    /**
     * Handles the exit hover event for the logout button.
     */
    @FXML
    protected void exitedLogoutBtn() {
        String border = "0 0 1 0;";
        applyHoverStyle(logout_hbox, logout_btn, border, false);
    }

    /**
     * Handles the settings button click event.
     * Changes the scene to the user settings view.
     *
     * @throws IOException if there is an error loading the user settings view.
     */
    @FXML
    protected void SettingsClicked() throws IOException {
        timerBtnBool = false;
        calendarBtnBool = false;
        settingsBtnBool = true;

        calendar_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:1 0 1 0");
        calendar_btn.setStyle("-fx-background-color:#C7D4D9;");

        timer_hbox.setStyle("-fx-background-color:#C7D4D9; -fx-border-color: black; -fx-border-width:0 0 1 0");
        timer_btn.setStyle("-fx-background-color:#C7D4D9;");

        Node settingsPane = settingsController.getSettingsPane();
        if (settingsController != null) {
            if (settingsPane != null) {
                settingsPane.toFront();
            }
        }
    }

    /**
     * Handles the hover event for the settings button.
     */
    @FXML
    private void hoveredSettingsBtn() {
        settings_hbox.setStyle("-fx-background-color:#74A7BB;");
    }

    /**
     * Handles the exit hover event for the settings button.
     */
    @FXML
    private void exitedSettingsBtn() {
        settings_hbox.setStyle("-fx-background-color:#C7D4D9;");
    }
}
