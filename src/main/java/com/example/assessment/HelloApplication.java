package com.example.assessment;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

/**
 * Main application class for launching the JavaFX application.
 * Sets up the initial scene and stage properties.
 */
public class HelloApplication extends Application {

    public static final int HEIGHT = 414;
    public static final int WIDTH = 300;

    /**
     * Starts the JavaFX application by setting the initial scene.
     *
     * @param stage the primary stage for this application
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void start(Stage stage) throws IOException {
        setScene(stage, "startup_view.fxml", WIDTH, HEIGHT);
        stage.setTitle("Sign In");
        Image icon = new Image(getClass().getResourceAsStream("logo.jpeg"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Main method to launch the JavaFX application.
     * Initializes the database connection before launching the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseConnection.InitialiseDB();
        launch();
    }
}