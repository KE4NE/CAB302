package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import com.example.assessment.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

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

    //function to load text file containing Ts&Cs, and append each line to ScrollPane
    public void initialize(){
        try{
            BufferedReader in = new BufferedReader(new FileReader("Terms_and_Conditions.txt"));
            String str;
            while ((str = in.readLine()) != null){
                //System.out.println(str);
                content.appendText(str);
            }
            in.close();
        }
        catch (IOException ioe) {}

    }

    //Open view of log in when accepted button pressed
    @FXML
    protected void acceptClicked() throws IOException {
        Stage stage = (Stage) accept_btn.getScene().getWindow();
        setScene(stage, "startup_view.fxml", SettingsController.WIDTH, SettingsController.HEIGHT);
        stage.centerOnScreen();
    }

    //If decline button pressed, show pop up that it needs to be accepted
    @FXML
    protected void declineClicked() throws IOException {
        Stage stage = (Stage) decline_btn.getScene().getWindow();
        new PopUp("Please accept Terms and Conditions to create account", stage);

    }

}
