package com.example.assessment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class for managing the database connection and initializing the database schema.
 */
public class DatabaseConnection {
    private static Connection instance = null;

    /**
     * Initializes the database by establishing a connection and creating necessary tables.
     */
    public static void InitialiseDB() {
        String url = "jdbc:sqlite:database.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
        createTables();
    }

    /**
     * Returns the singleton instance of the database connection.
     * If the instance is not yet created, it initializes the database connection.
     *
     * @return The singleton instance of the database connection.
     */
    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Creates the necessary tables in the database if they do not already exist.
     */
    private static void createTables() {
        try {
            Statement statement = instance.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS UserAccounts(id integer primary key autoincrement, username text, hashedPassword text, salt text)");
            statement.execute("CREATE TABLE UserEntries(username text, entryID text, title text, startDate text, endDate text, startTime text, endTime text, hidden int, PRIMARY KEY(username, entryID), UNIQUE(username, entryID))");
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}