import com.example.assessment.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UserAccount class using the MockUserDAO.
 * Tests the functionality of creating and validating user accounts.
 */
class UserAccountTest {

    /**
     * Sets up the mock DAO before all tests are run.
     * This method is executed once before any of the test methods.
     */
    @BeforeAll
    static void changeDAO() {
        UserAccount.userDAO = new MockUserDAO();
    }

    /**
     * Tests the creation of a user account with a password that is too short.
     * Verifies that the user account is not valid.
     */
    @Test
    void passwordTooShort() {
        // Password too short.
        UserAccount user = new UserAccount("Test", "pass", false);
        assertFalse(user.valid);
    }

    /**
     * Tests the creation of a user account with no password.
     * Verifies that the user account is not valid.
     */
    @Test
    void noPassword() {
        // No password.
        UserAccount user = new UserAccount("Test", "", false);
        assertFalse(user.valid);
    }

    /**
     * Tests the creation of a user account with no username.
     * Verifies that the user account is not valid.
     */
     @Test
     void noUsername() {
         // No username.
         UserAccount user = new UserAccount("", "password123", false);
         assertFalse(user.valid);
     }

    /**
     * Tests the creation of a user account with no username and no password.
     * Verifies that the user account is not valid.
     */
    @Test
    void noUsernameNoPassword() {
        // No username, no password.
        UserAccount user = new UserAccount("", "", false);
        assertFalse(user.valid);
    }

    /**
     * Tests the creation of a user account with a duplicate username.
     * Verifies that the user account is not valid if the username already exists.
     */
    @Test
    void duplicateAccounts() {
        // Account under 'Keane' already exists in the database
        UserAccount.userDAO.addUser("Keane", "password123");
        UserAccount user2 = new UserAccount("Keane", "pass123", false);
        assertFalse(user2.valid);
    }

    /**
     * Tests the creation of a valid new user account with a unique username and valid password.
     * Verifies that the user account is valid.
     */
    @Test
    void validNewAccount() {
        // Account with unique username and valid password
        UserAccount user = new UserAccount("abcdefgh123", "password123", false);
        assertTrue(user.valid);
    }

    /**
     * Tests the validation of an existing user account with the correct password.
     * Verifies that the user account is valid.
     */
    @Test
    void validExistingAccount() {
        // Account with existing username and correct password
        UserAccount.userDAO.addUser("test", "testpassword123");
        UserAccount testUser = new UserAccount("test", "testpassword123", true);
        assertTrue(testUser.valid);
    }

    /**
     * Tests the validation of an existing user account with an incorrect password.
     * Verifies that the user account is not valid.
     */
    @Test
    void invalidExistingAccount() {
        // Existing account with incorrect password.
        UserAccount.userDAO.addUser("testAccV2", "password123");
        UserAccount testAccV2 = new UserAccount("testAccV2", "unknown123", true);
        assertFalse(testAccV2.valid);
    }
}