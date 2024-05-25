package com.example.assessment.controllers;

import com.example.assessment.HelloApplication;
import com.example.assessment.PopUp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

/**
<<<<<<< HEAD
 * Controller class for handling user interactions with the Terms and Conditions page.
=======
 * Controller to provide functionality to the Terms and Conditions page
>>>>>>> 9302deabc01b3844999721782974a1f380a4eeb5
 */
public class TermsConditionsController {

    /**
     * Accept button to accept terms and conditions
     */
    @FXML
    private Button accept_btn;

    /**
     * Decline button to reject terms and conditions
     */
    @FXML
    private Button decline_btn;

    /**
     * Title and header of the page, displays 'Terms and Conditions'
     */
    @FXML
    private Text title;

    /**
     * Text area where the contents of the terms and conditions text file is displayed
     */
    @FXML
    private TextArea content;

    /**
     * Load terms and conditions in a text file into the text area
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
     * Open view of the login page when the Accept button is pressed
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
     * Displays pop up that Accept button needs to be pressed, if Decline button is pressed
     *
     * @throws IOException if there is an error displaying the popup.
     */
    @FXML
    protected void declineClicked() throws IOException {
        Stage stage = (Stage) decline_btn.getScene().getWindow();
        new PopUp("Please accept Terms and Conditions to proceed", stage);

    }

}
