package com.example.assessment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class for managing scene transitions in a JavaFX application.
 */
public class SceneHelper {

    /**
     * Sets a new scene on the given stage using the specified FXML file and dimensions.
     *
     * @param stage The stage on which the scene will be set.
     * @param fxml_name The name of the FXML file to load.
     * @param width The width of the new scene.
     * @param height The height of the new scene.
     * @throws IOException If an I/O error occurs during loading of the FXML file.
     */
    public static void setScene(Stage stage, String fxml_name, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml_name));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setScene(scene);
    }
}
