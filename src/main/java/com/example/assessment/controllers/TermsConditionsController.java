package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import com.example.assessment.PopUp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

/**
 * Controller class for handling user interactions with the Terms and Conditions page.
 */
public class TermsConditionsController {

    @FXML
    private Button accept_btn;

    @FXML
    private Button decline_btn;

    @FXML
    private TextArea content;

    /**
     * Initializes the Terms and Conditions page.
     * Loads the Terms and Conditions text file and displays its content in the TextArea.
     */
    public void initialize(){
        try{
            BufferedReader in = new BufferedReader(new FileReader("Assessment Documents/Terms_and_Conditions.txt"));
            String line;
            while ((line = in.readLine()) != null){
                content.appendText(line + "\n");
            }
            in.close();
        }
        catch (IOException ioe) {}

    }

    /**
     * Handles the accept button click event.
     * Navigates the user back to the login page and shows a success popup.
     *
     * @throws IOException if there is an error loading the login page.
     */
    @FXML
    protected void acceptClicked() throws IOException {
        Stage stage = (Stage) accept_btn.getScene().getWindow();
        setScene(stage, "startup_view.fxml", HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.centerOnScreen();
        new PopUp("Account Created Successfully.", stage);
    }

    /**
     * Handles the decline button click event.
     * Shows a popup indicating that the Terms and Conditions must be accepted to proceed.
     *
     * @throws IOException if there is an error displaying the popup.
     */
    @FXML
    protected void declineClicked() throws IOException {
        Stage stage = (Stage) decline_btn.getScene().getWindow();
        new PopUp("Please accept Terms and Conditions to proceed", stage);

    }

}
