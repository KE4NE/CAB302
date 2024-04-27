package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import com.example.assessment.PopUp;
import com.example.assessment.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button login_btn;

    private SqliteUserDAO userDAO = new SqliteUserDAO();

    @FXML
    protected void loginClicked() throws IOException {
        // Will need to change the page here NOT IMPLEMENTED
        Stage stage = (Stage) login_btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startup_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

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
                new PopUp("Error: Username already exists.", stage);
            }
            else {
                new PopUp("Account Created Successfully.", stage);
            }
        } else {
            new PopUp("Error: Provided passwords differ.",
                    stage);
        }
    }

}
