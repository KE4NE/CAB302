package com.example.assessment;

import com.example.assessment.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHelper {

    public static void setScene(Stage stage, String fxml_name, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml_name));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setScene(scene);
    }
}
