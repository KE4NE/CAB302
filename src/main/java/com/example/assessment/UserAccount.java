package com.example.assessment;

/**
 * Represents a user account with a username and password.
 * Provides methods for validating and checking user account credentials.
 */
public class UserAccount {
    private final String username;
    private final String password;

    public boolean valid = false;

    public static UserDAOInterface userDAO = new SqliteUserDAO();

    /**
     * Constructs a UserAccount object with the given username and password.
     * If the user account is existing, it validates the credentials.
     * Otherwise, it checks the uniqueness of the username and the validity of the password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param existing A boolean indicating if the user account already exists.
     */
    public UserAccount(String username, String password, boolean existing) {
        this.username = username;
        this.password = password;
        if (existing) {
            this.valid = userDAO.correctPassword(username, password);
        } else {
            boolean usernameUnique = this.checkUsername(username);
            boolean validPassword = this.checkPassword(password);
            this.valid = usernameUnique && validPassword;
        }
    }

    /**
     * Default constructor for UserAccount. Initializes an account with null username and password.
     */
    public UserAccount() {
        this.username = null;
        this.password = null;
    }

    /**
     * Gets the username of the user account.
     *
     * @return The username of the user account.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password of the user account.
     *
     * @return The password of the user account.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Checks if the given username is unique and not empty.
     *
     * @param username The username to check.
     * @return True if the username is unique and not empty, false otherwise.
     */
    private boolean checkUsername(String username) {
        return userDAO.numberOfAccounts(username) == 0 && !username.isEmpty();
    }

    /**
     * Checks if the given password meets the validity criteria (minimum length of 5 characters).
     *
     * @param password The password to check.
     * @return True if the password is valid, false otherwise.
     */
    private boolean checkPassword(String password) {
        return password.length() >= 5;
    }
}
