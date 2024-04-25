package com.example.assessment;

public class UserAccount {
    private String username;
    private String passwordSalt;
    private String securePassword;

    public UserAccount(String username, String password) {
        this.username = username;
        this.passwordSalt = "ABC";
        this.securePassword = password + passwordSalt; // Hashing function needs to be implemented.
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return securePassword;
    }

    public static boolean verifyAccount(String username, String password) {
        // Check if username is in database.
        // Retrieve hashed password and salt from database
        // Verify whether hash(password + salt) = stored password in database
        return true;
    }
}
