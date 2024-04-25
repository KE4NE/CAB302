package com.example.assessment;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.assessment.SceneHelper.setScene;

public class HelloApplication extends Application {

    public static final int HEIGHT = 414;
    public static final int WIDTH = 300;

    public static Connection connection;

    @Override
    public void start(Stage stage) throws IOException {

        setScene(stage, "startup_view.fxml", WIDTH, HEIGHT);
        stage.setTitle("Sign In");
        Image icon = new Image(getClass().getResourceAsStream("logo.jpeg"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        connection = DatabaseConnection.getInstance();
        createTables();
        UserAccount testv2 = new UserAccount("Jacob", "testv2");
        launch();
    }

    public static void createTables() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS UserAccounts(id integer primary key autoincrement, username text, hashedPassword text, salt text)");
            connection.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}