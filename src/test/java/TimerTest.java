import com.example.assessment.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
class TimerTest {

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
}
