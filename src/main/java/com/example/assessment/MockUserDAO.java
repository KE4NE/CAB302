package com.example.assessment;

import java.util.ArrayList;
import java.util.List;

public class MockUserDAO implements UserDAOInterface{

    public List<UserAccount> Users = new ArrayList<>();
    public List<String> hashedPasswords = new ArrayList<>();
    public List<String> Salts = new ArrayList<>();



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
    public boolean changeUser(String username, String password, String currentpass){
        return true;
    }

    @Override
    public UserAccount verifyUser(String username, String password) {
        if (numberOfAccounts(username) == 1) {
            if (this.correctPassword(username, password)) {
                return new UserAccount(username, password, true);
            }
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
        List<String> Usernames = new ArrayList<>();
        for (UserAccount user : Users) {
            Usernames.add(user.getUsername());
        }
        int userIndex = Usernames.indexOf(username);
        String hashedPassword = hashedPasswords.get(userIndex);
        String salt = Salts.get(userIndex);
        return CryptographyHelper.verifyPassword(hashedPassword, password, salt);
    }
}
