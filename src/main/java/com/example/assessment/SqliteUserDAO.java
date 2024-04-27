package com.example.assessment;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Random;

public class SqliteUserDAO {

    Connection connection;

    private final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public SqliteUserDAO() {
        this.connection = DatabaseConnection.getInstance();
    }

    public boolean uniqueUsername(String username) {
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
        return numberAccounts == 0;
    }

    public boolean addUser(String username, String password) {
        String passwordSalt = generateSalt();
        String combinedSalt = password + passwordSalt;
        String securePassword = Hashing.sha256().hashString(combinedSalt, StandardCharsets.UTF_8).toString();
        if (!uniqueUsername(username)) {
            System.out.print("Account already exists");
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

    public UserAccount VerifyUser(String username, String password) {
        String providedPassword, retrievedPassword;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT hashedPassword, salt FROM UserAccounts" +
                            " WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            retrievedPassword = resultSet.getString("hashedPassword");
            String retrievedSalt = resultSet.getString("salt");
            providedPassword = Hashing.sha256().hashString(password+retrievedSalt,
                    StandardCharsets.UTF_8).toString();
            System.out.println(providedPassword);
            System.out.println(retrievedPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (providedPassword.equals(retrievedPassword)) {
            return new UserAccount(username,password);
        }
        return new UserAccount();
    }

    private String generateSalt() {
        StringBuilder salt = new StringBuilder();
        int saltLength = 10;
        int availableChars = SALTCHARS.length();
        Random randVal = new Random();
        for (int i = 0; i < saltLength; i++) {
            int randIndex = randVal.nextInt(availableChars);
            salt.append(SALTCHARS.charAt(randIndex));
        }
        return salt.toString();
    }
}
