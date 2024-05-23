package com.example.assessment;

/**
 * Interface for managing user-related operations.
 */
public interface UserDAOInterface{

    /**
     * Adds a new user with the given username and password.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @return True if the user is successfully added, false otherwise.
     */
    public boolean addUser(String username, String password);

    /**
     * Verifies the user credentials for the given username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A UserAccount object if the credentials are valid, or a new UserAccount object if not.
     */
    public boolean changeUser(String username, String password, String currentpass);

    public UserAccount verifyUser(String username, String password);

    /**
     * Retrieves the number of user accounts with the specified username.
     *
     * @param username The username for which to count the accounts.
     * @return The number of user accounts with the specified username.
     */
    public int numberOfAccounts(String username);

    /**
     * Checks if the provided password matches the stored password for the given username.
     *
     * @param username The username of the user.
     * @param password The password to verify.
     * @return True if the password matches the stored password, false otherwise.
     */
    public boolean correctPassword(String username, String password);
}
