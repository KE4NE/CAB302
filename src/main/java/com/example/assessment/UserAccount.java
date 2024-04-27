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
    private String password;

    public boolean valid;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.valid = true;
    }

    public UserAccount() {
        this.username = null;
        this.password = null;
        this.valid = false;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
