package com.example.assessment;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Random;

public class SqliteUserDAO implements UserDAOInterface {

    Connection connection;

    private final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

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
    public boolean changeUser(String username, String password, String currentpass) {
        String passwordSalt = CryptographyHelper.generateSalt();
        String securePassword = CryptographyHelper.hashPassword(password, passwordSalt);
        UserAccount testUser = new UserAccount(username,currentpass, true);
        if (!testUser.valid) {
            return false;
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE UserAccounts"  +
                            "SET hashedPassword =?" +
                            " WHERE username =?");
            statement.setString(1, securePassword);
            statement.setString(2, username);
       //     statement.setString(3, passwordSalt);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
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
