package com.example.assessment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    @FXML
    protected void signupClicked() throws IOException {
        // Will need to change the page here NOT IMPLEMENTED
        Stage stage = (Stage) signup_btn.getScene().getWindow();
        setScene(stage, "signup_view.fxml", HelloApplication.WIDTH, HelloApplication.HEIGHT);
    }

    @FXML
    protected void loginClicked() throws IOException {
        // Authenticate user and change to main page. NOT IMPLEMENTED
        Stage stage = (Stage) login_btn.getScene().getWindow();
        setScene(stage, "main_view.fxml", MainController.WIDTH, MainController.HEIGHT);
    }
}