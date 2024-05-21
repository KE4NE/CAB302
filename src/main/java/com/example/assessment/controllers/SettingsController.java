package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import com.example.assessment.PopUp;
import com.example.assessment.SqliteUserDAO;
import com.example.assessment.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

public class SettingsController {

    public static final int WIDTH = 845;

    public static final int HEIGHT = 560;

    @FXML
    private Button logout_btn;

    @FXML
    private HBox calendar_hbox;

    @FXML
    private HBox timer_hbox;

    @FXML
    private HBox settings_hbox;

    @FXML
    private HBox logout_hbox;

    @FXML
    private Button calendar_btn;

    @FXML
    private Button settings_btn;

    @FXML
    private Button timer_btn;

    @FXML
    private TimerController timerController;

    @FXML
    private CalendarFXController calendarfxController;

    @FXML
    private boolean timerBtnBool;

    @FXML
    private TextField Username;
    @FXML
    private PasswordField Current_Password;
    @FXML
    private PasswordField Password;

    @FXML
    private PasswordField Password_confirm;
    private SqliteUserDAO userDAO = new SqliteUserDAO();

    private boolean calendarBtnBool;
    public static UserAccount authenticatedUser;

    public void initialize() {
        calendarSelected();
    }

    private void applyHoverStyle(HBox hbox, Button btn, boolean hover) {
        String bgColor = hover ? "#74A7BB" : "#C7D4D9";
        String borderStyle = hover ? "1 0 1 0" : "0 0 1 0";
        hbox.setStyle(String.format("-fx-background-color:%s; -fx-border-color: black;", bgColor));
        btn.setStyle(String.format("-fx-background-color:%s;", bgColor));
    }

    @FXML
    protected void logoutClicked() throws IOException {
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
        setScene(stage, "user_settings.fxml",SettingsController.WIDTH, SettingsController.HEIGHT);
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

    @FXML
    protected void change_password_clicked() throws IOException {
        String usernameText = Username.getText();
        String currentpassText = Current_Password.getText();
        String newpassText = Password.getText();
        String confirmPassText = Password_confirm.getText();
        Stage stage = (Stage) settings_btn.getScene().getWindow();
        if (usernameText.isEmpty() || newpassText.isEmpty()) {
            new PopUp("Error: Password or Username cannot be empty", stage);
            return;
        }
        if (confirmPassText.equals(newpassText)) {
            authenticatedUser = userDAO.verifyUser(usernameText, currentpassText);
            if (authenticatedUser.valid) {
                boolean userChanged = userDAO.changeUser(usernameText, newpassText, currentpassText);
                if (!userChanged) {
                    new PopUp("Error: Unable to change Password.", stage);
                } else {
                    new PopUp("Password changed successfully.", stage);
                }
            } else {
                new PopUp("Error: Current details incorrect.",
                        stage);
            }
        } else {
            new PopUp("Error: Provided passwords differ.",
                    stage);
        }
    }
}
