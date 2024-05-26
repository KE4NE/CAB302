package com.example.assessment.controllers;

import com.example.assessment.PopUp;
import com.example.assessment.SqliteUserDAO;
import com.example.assessment.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class SettingsController {

    public static final int WIDTH = 845;

    public static final int HEIGHT = 560;

    @FXML
    private Button settings_btn;

    @FXML
    private TextField Username;
    @FXML
    private PasswordField Current_Password;
    @FXML
    private PasswordField Password;

    @FXML
    private PasswordField Password_confirm;

    @FXML
    private VBox settingsPane;
    private SqliteUserDAO userDAO = new SqliteUserDAO();

    public static UserAccount authenticatedUser;

    /**
     * Handles the event when the change password button is clicked.
     * Verifies the current user details and changes the password if valid.
     *
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Returns the VBox containing the settings pane.
     *
     * @return the settings pane
     */
    public VBox getSettingsPane() {
        return settingsPane;
    }
}
