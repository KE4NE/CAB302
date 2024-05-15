import com.example.assessment.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {

    //public UserDAOInterface userDAO = new MockUserDAO();

    @BeforeAll
    static void changeDAO() {
        UserAccount.userDAO = new MockUserDAO();
    }

    // This is a placeholder test. It needs to be implemented.
    @Test
    void CreateCalendarEvent() {
        // Password too short.
        UserAccount user = new UserAccount("Test", "pass", false);
        assertFalse(user.valid);
    }
}