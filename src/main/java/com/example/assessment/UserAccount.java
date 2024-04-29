package com.example.assessment;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;

import com.google.common.hash.Hashing;

public class UserAccount {
    private final String username;
    private final String password;

    Connection connection;
    public boolean valid = false;

    public UserAccount(String username, String password, boolean existing) {
        this.username = username;
        this.password = password;
        this.connection = DatabaseConnection.getInstance();
        if (existing) {
            this.valid = this.accountExists(username);
        } else {
            boolean usernameUnique = this.uniqueUsername(username);
            boolean validPassword = this.checkPassword(password);
            this.valid = usernameUnique && validPassword;
        }
    }

    public UserAccount() {
        this.username = null;
        this.password = null;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    private boolean uniqueUsername(String username) {
        return numberOfAccounts(username) == 0;
    }

    private boolean accountExists(String username) {
        return numberOfAccounts(username) == 1;
    }

    private int numberOfAccounts(String username) {
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

    private boolean checkPassword(String password) {
        return password.length() >= 5;
    }
}
