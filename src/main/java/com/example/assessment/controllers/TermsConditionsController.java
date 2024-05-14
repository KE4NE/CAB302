package com.example.assessment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

public class TermsConditionsController {

    @FXML
    private Button accept_btn;

    @FXML
    private Button decline_btn;

    @FXML
    private ScrollPane terms_conditions_display;

    @FXML
    private Text title;

    //function to main menu, open view of main menu when accepted button pressed

    //function to take back to log in, if decline button pressed, open start up view

}
