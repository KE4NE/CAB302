package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import com.example.assessment.PopUp;
import com.example.assessment.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

/**
 * Controller class for handling user interactions on the sign-up page.
 */
public class SignupController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button login_btn;

    private SqliteUserDAO userDAO = new SqliteUserDAO();

    /**
     * Handles the login button click event.
     * Navigates the user back to the login page.
     *
     * @throws IOException if there is an error loading the login page.
     */
    @FXML
    protected void loginClicked() throws IOException {
        // Will need to change the page here NOT IMPLEMENTED
        Stage stage = (Stage) login_btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startup_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    /**
     * Handles the sign-up button click event.
     * Validates the user input and attempts to create a new user account.
     * If successful, navigates the user to the terms and conditions page.
     * Otherwise, displays an error popup.
     *
     * @throws IOException if there is an error loading the next scene.
     */
    @FXML
    protected void signupClicked() throws IOException {
        // Retrieve text from sign-up page.
        String usernameText = username.getText();
        String passwordText = password.getText();
        String confirmPassText = confirmPassword.getText();
        Stage stage = (Stage) login_btn.getScene().getWindow();
        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            new PopUp("Error: Password or Username cannot be empty", stage);
            return;
        }

        if (confirmPassText.equals(passwordText)) {
            boolean userAdded = userDAO.addUser(usernameText, passwordText);
            if (!userAdded) {
                new PopUp("Error: Username already exists or password too short.", stage);
            }
            else {
                //add terms and conditions view here for user to accept here
                setScene(stage, "termsconditions.fxml", MainController.WIDTH, MainController.HEIGHT);
                stage.centerOnScreen();

            }
        } else {
            new PopUp("Error: Provided passwords differ.",
                    stage);
        }
    }

}
