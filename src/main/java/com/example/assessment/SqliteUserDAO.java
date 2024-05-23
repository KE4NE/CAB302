package com.example.assessment;

import java.sql.*;

/**
 * Implementation of the UserDAOInterface for SQLite database.
 * Manages user-related database operations such as adding users, verifying passwords, and retrieving user accounts.
 */
public class SqliteUserDAO implements UserDAOInterface {

    Connection connection;

    private final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    /**
     * Constructs a SqliteUserDAO object and initializes the database connection.
     */
    public SqliteUserDAO() {
        this.connection = DatabaseConnection.getInstance();
    }

    @Override
    public boolean addUser(String username, String password) {
        String passwordSalt = CryptographyHelper.generateSalt();
        String securePassword = CryptographyHelper.hashPassword(password, passwordSalt);
        UserAccount testUser = new UserAccount(username,password, false);
        if (!testUser.valid) {
            return false;
        }
        try {
            // Check that there are no existing accounts by this user.
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO UserAccounts(username, hashedPassword, salt)" +
                            " VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, securePassword);
            statement.setString(3, passwordSalt);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean correctPassword(String username, String password) {
        String retrievedPassword, retrievedSalt;
        if (numberOfAccounts(username) != 1) {
            return false;
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT hashedPassword, salt FROM UserAccounts" +
                            " WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            retrievedPassword = resultSet.getString("hashedPassword");
            retrievedSalt = resultSet.getString("salt");
            System.out.println(retrievedPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (CryptographyHelper.verifyPassword(retrievedPassword,password,retrievedSalt)) {
            return true;
        }
        return false;
    }

    @Override
    public UserAccount verifyUser(String username, String password) {
        String retrievedPassword, retrievedSalt;
        UserAccount testUser = new UserAccount(username,password,  true);
        System.out.println(testUser.valid);
        if (!testUser.valid) {
            return new UserAccount();
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT hashedPassword, salt FROM UserAccounts" +
                            " WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            retrievedPassword = resultSet.getString("hashedPassword");
            retrievedSalt = resultSet.getString("salt");
            System.out.println(retrievedPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (CryptographyHelper.verifyPassword(retrievedPassword,password,retrievedSalt)) {
            return new UserAccount(username,password,true);
        }
        return new UserAccount();
    }

    @Override
    public int numberOfAccounts(String username) {
        int numberAccounts;
        try {
            // Check that there are no existing accounts by this user.
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT count(username) AS No FROM UserAccounts"
                            + " WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            numberAccounts = resultSet.getInt("No");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberAccounts;
    }
}
