package com.example.assessment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TermsConditionsController {

    @FXML
    private Button accept_btn;

    @FXML
    private Button decline_btn;

    @FXML
    private ScrollPane terms_conditions_display;

    @FXML
    private Text title;

    @FXML
    private TextArea content;

    //function to main menu, open view of main menu when accepted button pressed

    //function to take back to log in, if decline button pressed, open start up view

    //function to load text file containing Ts&Cs, and append each line to ScrollPane
    public void initialize(){
        try{
            BufferedReader in = new BufferedReader(new FileReader("Terms_and_Conditions.txt"));
            String str;
            while ((str = in.readLine()) != null){
                System.out.println(str);
                content.appendText(str);
            }
            in.close();
        }
        catch (IOException ioe) {}

    }

}
