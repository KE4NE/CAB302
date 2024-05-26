package com.example.assessment;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of the UserDAOInterface for testing purposes.
 * Manages user-related operations using in-memory lists instead of a database.
 */
public class MockUserDAO implements UserDAOInterface{

    // List to store user accounts
    public List<UserAccount> Users = new ArrayList<>();

    // List to store hashed passwords
    public List<String> hashedPasswords = new ArrayList<>();

    // List to store salts used for hashing passwords
    public List<String> Salts = new ArrayList<>();

    /**
     * Adds a new user to the mock database.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return true if the user is successfully added, false if the username already exists
     */
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

    /**
     * Changes the password of an existing user.
     *
     * @param username the username of the user
     * @param password the new password for the user
     * @param currentpass the current password of the user
     * @return true if the password is successfully changed
     */
    @Override
    public boolean changeUser(String username, String password, String currentpass){
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
        if (numberOfAccounts(username) == 1) {
            if (this.correctPassword(username, password)) {
                return new UserAccount(username, password, true);
            }
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
        int NoAccounts = 0;
        for (UserAccount user : Users) {
            if ((user.getUsername()).equals(username)) {
                NoAccounts++;
            }
        }
        return NoAccounts;
    }

    /**
     * Checks if the provided password is correct for the given username.
     *
     * @param username the username of the user
     * @param password the password to verify
     * @return true if the password is correct, false otherwise
     */
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
