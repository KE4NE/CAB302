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

    //Connection connection;
    public boolean valid = false;

    public static UserDAOInterface userDAO = new SqliteUserDAO();

    public UserAccount(String username, String password, boolean existing) {
        this.username = username;
        this.password = password;
        if (existing) {
            this.valid = userDAO.correctPassword(username, password);
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
        return userDAO.numberOfAccounts(username) == 0 && !username.isEmpty();
    }

    private boolean checkPassword(String password) {
        return password.length() >= 5;
    }
}
