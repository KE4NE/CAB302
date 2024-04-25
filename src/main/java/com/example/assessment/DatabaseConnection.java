package com.example.assessment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection instance = null;

    public static void InitialiseDB() {
        String url = "jdbc:sqlite:database.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
        createTables();
    }

    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }

    private static void createTables() {
        try {
            Statement statement = instance.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS UserAccounts(id integer primary key autoincrement, username text, hashedPassword text, salt text)");
            //connection.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}