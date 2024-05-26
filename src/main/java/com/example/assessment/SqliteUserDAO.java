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

    /**
     * Adds a new user to the database.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return true if the user is successfully added, false otherwise
     */
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

    /**
     * Verifies if the provided password is correct for the given username.
     *
     * @param username the username of the user
     * @param password the password to verify
     * @return true if the password is correct, false otherwise
     */
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (CryptographyHelper.verifyPassword(retrievedPassword,password,retrievedSalt)) {
            return true;
        }
        return false;
    }

    /**
     * Encrypts the given new password using the existing salt of the user.
     *
     * @param username the username of the user
     * @param password the new password to encrypt
     * @return the encrypted password
     */
    public String new_password(String username, String password) {
        String securePassword, passwordSalt;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT salt FROM UserAccounts" +
                            " WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            passwordSalt = resultSet.getString("salt");
            securePassword = CryptographyHelper.hashPassword(password, passwordSalt);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return securePassword;
    }

    /**
     * Changes the password of an existing user.
     *
     * @param username the username of the user
     * @param password the new password for the user
     * @param currentpassword the current password of the user
     * @return true if the password is successfully changed, false otherwise
     */
    @Override
    public boolean changeUser(String username, String password, String currentpassword) {
        String securePassword;
        UserAccount testUser = new UserAccount(username,currentpassword, true);
        if (!testUser.valid) {
            return false;
        }
       securePassword = new_password(username, password);
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE UserAccounts "+
                            "SET hashedPassword =?" +
                            " WHERE username =?");
            statement.setString(1, securePassword);
            statement.setString(2, username);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Verifies the user's credentials.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return a UserAccount object if the credentials are valid, an empty UserAccount otherwise
     */
    @Override
    public UserAccount verifyUser(String username, String password) {
        String retrievedPassword, retrievedSalt;
        UserAccount testUser = new UserAccount(username,password,  true);
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (CryptographyHelper.verifyPassword(retrievedPassword,password,retrievedSalt)) {
            return new UserAccount(username,password,true);
        }
        return new UserAccount();
    }

    /**
     * Returns the number of accounts with the given username.
     *
     * @param username the username to check
     * @return the number of accounts with the given username
     */
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
