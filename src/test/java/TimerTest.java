import com.example.assessment.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
class TimerTest {

    //public UserDAOInterface userDAO = new MockUserDAO();

    @BeforeAll
    static void changeDAO() {
        UserAccount.userDAO = new MockUserDAO();
    }

    // This is a placeholder test. It needs to be implemented.
    @Test
    void StartNewTimer() {
        // Password too short.
        UserAccount user = new UserAccount("Test", "pass", false);
        assertFalse(user.valid);
    }
}