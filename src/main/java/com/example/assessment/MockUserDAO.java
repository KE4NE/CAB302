package com.example.assessment;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MockUserDAO implements UserDAOInterface{

    List<UserAccount> Users = new ArrayList<>();
    List<String> hashedPasswords = new ArrayList<>();
    List<String> Salts = new ArrayList<>();



    @Override
    public boolean addUser(String username, String password) {
        if (numberOfAccounts(username) == 0) {
            Users.add(new UserAccount(username, password, false));
            String salt = CryptographyHelper.generateSalt();
            Salts.add(salt);
            String hashedPassword = CryptographyHelper.hashPassword(password, salt);
            hashedPasswords.add(hashedPassword);
            return true;
        }
        return false;
    }

    @Override
    public UserAccount verifyUser(String username, String password) {
        if (numberOfAccounts(username) == 1) {
            return new UserAccount(username, password, true);
        }
        return new UserAccount();
    }

    @Override
    public int numberOfAccounts(String username) {
        int NoAccounts = 0;
        for (UserAccount user : Users) {
            if ((user.getUsername()).equals(username)) {
                NoAccounts++;
            }
        }
        return NoAccounts;
    }

    @Override
    public boolean correctPassword(String username, String password) {
        return false;
    }

}
