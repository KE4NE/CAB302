package com.example.assessment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.assessment.SceneHelper.setScene;

public class HelloApplication extends Application {

    public static final int HEIGHT = 400;
    public static final int WIDTH = 350;

    @Override
    public void start(Stage stage) throws IOException {

        setScene(stage, "startup_view.fxml", WIDTH, HEIGHT);
        stage.setTitle("Sign In");
        stage.show();
    }

    // TODO: this is for committing lol just to commit
    public static void main(String[] args) {
        launch();
    }
}