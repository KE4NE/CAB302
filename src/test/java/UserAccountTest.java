import com.example.assessment.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
class UserAccountTest {

    //public UserDAOInterface userDAO = new MockUserDAO();

    @BeforeAll
    static void changeDAO() {
        UserAccount.userDAO = new MockUserDAO();
    }

    @Test
    void passwordTooShort() {
        // Password too short.
        UserAccount user = new UserAccount("Test", "pass", false);
        assertFalse(user.valid);
    }

    @Test
    void passwordTooShort() {
        // No password.
        UserAccount user = new UserAccount("Test", "", false);
        assertFalse(user.valid);
    }

    @Test
    void noUsername() {
        // No username.
        UserAccount user = new UserAccount("", "password123", false);
        assertFalse(user.valid);
    }

    @Test
    void noUsernameNoPassword() {
        // No username, no password.
        UserAccount user = new UserAccount("", "", false);
        assertFalse(user.valid);
    }

    @Test
    void duplicateAccounts() {
        // Account under 'Keane' already exists in the database
        UserAccount.userDAO.addUser("Keane", "password123");
        UserAccount user2 = new UserAccount("Keane", "pass123", false);
        assertFalse(user2.valid);
    }
    @Test
    void validNewAccount() {
        // Account with unique username and valid password
        UserAccount user = new UserAccount("abcdefgh123", "password123", false);
        assertTrue(user.valid);
    }

    @Test
    void validExistingAccount() {
        // Account with existing username and correct password
        UserAccount.userDAO.addUser("test", "testpassword123");
        UserAccount testUser = new UserAccount("test", "testpassword123", true);
        assertTrue(testUser.valid);
    }

    @Test
    void invalidExistingAccount() {
        // Existing account with incorrect password.
        UserAccount.userDAO.addUser("testAccV2", "password123");
        UserAccount testAccV2 = new UserAccount("testAccV2", "unknown123", true);
        assertFalse(testAccV2.valid);
    }
}
