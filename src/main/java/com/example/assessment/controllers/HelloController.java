package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import com.example.assessment.PopUp;
import com.example.assessment.SqliteUserDAO;
import com.example.assessment.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login_btn;

    @FXML
    private Button signup_btn;

    private SqliteUserDAO userDAO = new SqliteUserDAO();

    public UserAccount authenticatedUser;

    @FXML
    protected void signupClicked() throws IOException {
        // Will need to change the page here NOT IMPLEMENTED
        Stage stage = (Stage) signup_btn.getScene().getWindow();
        setScene(stage, "signup_view.fxml", HelloApplication.WIDTH, 475);
        stage.centerOnScreen();
    }

    @FXML
    protected void loginClicked() throws IOException {
        String usernameText = username.getText();
        String passwordText = password.getText();
        Stage stage = (Stage) login_btn.getScene().getWindow();
        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            new PopUp("Username or Password cannot be blank", stage);
            return;
        }
        authenticatedUser = userDAO.VerifyUser(usernameText, passwordText);
        if (authenticatedUser.valid) {
            // Authenticate user and change to main page. NOT IMPLEMENTED
            stage.setTitle("Main page");
            setScene(stage, "main_view.fxml", MainController.WIDTH, MainController.HEIGHT);
            stage.centerOnScreen();
        } else {
            new PopUp("Error: Incorrect Details", stage);
        }
    }
}