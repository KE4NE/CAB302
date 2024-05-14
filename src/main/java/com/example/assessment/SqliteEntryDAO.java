package com.example.assessment;

import java.sql.*;

public class SqliteEntryDAO implements EntryDAOInterface {

    Connection connection;

    public SqliteEntryDAO () {
        this.connection = DatabaseConnection.getInstance();
    }

    @Override
    public void addEntry(String username, String entryID, String title, String startDate, String endDate, String startTime, String endTime) {
        System.out.println(startDate);
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO UserEntries(username, entryID, title, startDate, endDate, startTime, endTime)" +
                            " VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, username);
            statement.setString(2, entryID);
            statement.setString(3, title);
            statement.setString(4, startDate);
            statement.setString(5, endDate);
            statement.setString(6, startTime);
            statement.setString(7, endTime);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeEntry(String username, String id) {

    }

}
