import com.example.assessment.DatabaseConnection;
import com.example.assessment.MockUserDAO;
import com.example.assessment.UserAccount;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
class UserAccountTest {

    @BeforeAll
    static void changeDAO() {
        UserAccount.userDAO = new MockUserDAO();
    }

    @Test
    void passwordTooShort() {
        UserAccount user = new UserAccount("Test", "pass", false);
        assertFalse(user.valid);
    }

    @Test
    void duplicateAccounts() {
        // Account under 'Keane' already exists in the database
        UserAccount user = new UserAccount("Keane", "password123", false);
        assertFalse(user.valid);
    }
    @Test
    void validNewAccount() {
        // Account with unique username and valid password
        UserAccount user = new UserAccount("abcdefgh123", "password123", false);
        assertTrue(user.valid);
    }

    @Test
    void validExistingAccount() {
        // Account with existing username and unverified password
        UserAccount user = new UserAccount("Keane", "incorrectPassword", true);
        assertTrue(user.valid);
        // Should be valid as the user 'Keane' does exist, the password just has not been correctly validated as of yet.
    }
}
