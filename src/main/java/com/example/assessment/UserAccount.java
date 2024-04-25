package com.example.assessment;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Random;

import com.google.common.hash.Hashing;

public class UserAccount {
    private String username;
    private String passwordSalt;
    private String securePassword;

    private final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public UserAccount(String username, String password) {
        this.username = username;

        this.passwordSalt = this.generateSalt();

        String combinedSalt = password + passwordSalt;

        this.securePassword = Hashing.sha256().hashString(combinedSalt, StandardCharsets.UTF_8).toString();

        System.out.println(securePassword);
        System.out.println(passwordSalt);

        try {
            PreparedStatement statement = HelloApplication.connection.prepareStatement(
                    "INSERT INTO UserAccounts(username, hashedPassword, salt)" +
                            " VALUES (?,?,?,?)");
            statement.setString(1, username);
            statement.setString(2, securePassword);
            statement.setString(3, passwordSalt);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.securePassword;
    }

    public static boolean verifyAccount(String username, String password) {
        // Check if username is in database.
        // Retrieve hashed password and salt from database
        // Verify whether hash(password + salt) = stored password in database
        return true;
    }
}
