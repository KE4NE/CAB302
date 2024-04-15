package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

public class MainController {

    public static final int WIDTH = 845;

    public static final int HEIGHT = 560;

    @FXML
    private Menu logout_menu;

    @FXML
    private Text title;

    @FXML
    protected void logoutClicked() throws IOException {
        // Will need to change the page here NOT IMPLEMENTED
        Stage stage = (Stage) title.getScene().getWindow();
        setScene(stage, "startup_view.fxml", HelloApplication.WIDTH, HelloApplication.HEIGHT);
    }

}
