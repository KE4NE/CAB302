package com.example.assessment;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementation of the EntryDAOInterface for SQLite database.
 * Manages entry-related database operations such as adding entries and retrieving entries.
 */
public class SqliteEntryDAO implements EntryDAOInterface {

    Connection connection;

    /**
     * Constructs a SqliteEntryDAO object and initializes the database connection.
     */
    public SqliteEntryDAO () {
        this.connection = DatabaseConnection.getInstance();
    }

    /**
     * Adds a new entry to the database or updates an existing one.
     *
     * @param username  the username associated with the entry
     * @param entryID   the unique ID of the entry
     * @param title     the title of the entry
     * @param startDate the start date of the entry
     * @param endDate   the end date of the entry
     * @param startTime the start time of the entry
     * @param endTime   the end time of the entry
     */
    @Override
    public void addEntry(String username, String entryID, String title, String startDate, String endDate, String startTime, String endTime) {
        System.out.println(startDate);
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO UserEntries(username, entryID, title, startDate, endDate, startTime, endTime, hidden)" +
                            " VALUES (?,?,?,?,?,?,?, 0)");
            // value for 'hidden' is set to 0
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

    /**
     * Marks an entry as hidden in the database.
     *
     * @param username the username associated with the entry
     * @param id       the unique ID of the entry
     */
    @Override
    public void removeEntry(String username, String id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(
                    "UPDATE UserEntries SET hidden=1 WHERE username = ? AND entryID = ?");
            // Update hidden value to 1.
            statement.setString(1, username);
            statement.setString(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        };
    }

    /**
     * Retrieves all visible entries for a specific user from the database.
     *
     * @param username the username for which to retrieve entries
     * @return an ArrayList of ArrayLists, where each inner ArrayList represents an entry's details
     */
    @Override
    public ArrayList<ArrayList<String>> retrieveEntries(String username) {
        String entryID, title, startDate, endDate, startTime, endTime;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM UserEntries" +
                            " WHERE username=? and hidden=0");
            // Only retrieve entries which are not hidden.
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
            while (resultSet.next()) {
                ArrayList<String> entryDetail = new ArrayList<String>();
                entryID = resultSet.getString("entryID");
                title = resultSet.getString("title");
                startDate = resultSet.getString("startDate");
                endDate = resultSet.getString("endDate");
                startTime = resultSet.getString("startTime");
                endTime = resultSet.getString("endTime");
                entryDetail.add(entryID);
                entryDetail.add(title);
                entryDetail.add(startDate);
                entryDetail.add(endDate);
                entryDetail.add(startTime);
                entryDetail.add(endTime);
                resultList.add(entryDetail);
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
