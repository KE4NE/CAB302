import com.example.assessment.*;
import com.example.assessment.controllers.CalendarFXController;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CalendarFXController using the MockEntryDao.
 * Tests the functionality of adding, removing, and retrieving calendar entries.
 */
class CalendarTest {

    /**
     * Sets up the mock DAO before all tests are run.
     * This method is executed once before any of the test methods.
     */
    @BeforeAll
    static void changeDAO() {
        CalendarFXController.calendarDAO = new MockEntryDao();
    }

    /**
     * Tests the addition of a single calendar event.
     * Verifies that the size of the retrieved entries list is 1.
     */
    @Test
    void SingularCalendarEventCreated() {
        String username;
        username = "John";
        CalendarFXController.calendarDAO.addEntry(username,"1809-0293-0293","Booking No 1","24-05-24","25-05-24","16:00", "17:00");
        ArrayList<ArrayList<String>> retrievedEntries = CalendarFXController.calendarDAO.retrieveEntries(username);
        assertEquals(retrievedEntries.size(), 1);
    }

    /**
     * Tests the creation and subsequent hiding of a single calendar event.
     * Verifies that the event is not retrieved after being hidden.
     */
    @Test
    void SingularEventCreatedAndHidden() {
        String username;
        username = "Peter";
        CalendarFXController.calendarDAO.addEntry(username,"1609-0293-0293","Booking No 1","24-05-24","25-05-24","16:00", "17:00");
        CalendarFXController.calendarDAO.removeEntry(username,"1609-0293-0293");
        ArrayList<ArrayList<String>> retrievedEntries = CalendarFXController.calendarDAO.retrieveEntries(username);
        assertEquals(retrievedEntries.size(), 0);
    }

    /**
     * Tests the removal of a non-existent calendar event.
     * Verifies that the size of the retrieved entries list remains 0.
     */
    @Test
    void RemovingEmptyCalendarEvent() {
        String username = "Daniel";
        CalendarFXController.calendarDAO.removeEntry(username,"1819-0293-0293");
        ArrayList<ArrayList<String>> retrievedEntries = CalendarFXController.calendarDAO.retrieveEntries(username);
        assertEquals(retrievedEntries.size(), 0);
    }

    /**
     * Tests the addition of multiple calendar events.
     * Verifies that the size of the retrieved entries list is 4.
     */
    @Test
    void MultipleCalendarEventsCreated() {
        String username = "Jack";
        CalendarFXController.calendarDAO.addEntry(username,"1819-0293-0293","Booking No 1", "24-05-24","25-05-24","14:00","15:00");
        CalendarFXController.calendarDAO.addEntry(username,"1810-0293-0293","Booking No 2", "26-05-24","27-05-24","15:00","16:00");
        CalendarFXController.calendarDAO.addEntry(username,"1811-0293-0293","Booking No 3", "28-05-24","28-05-24","10:00","11:00");
        CalendarFXController.calendarDAO.addEntry(username,"1812-0293-0293","Booking No 4", "12-05-24","12-05-24","12:00","13:00");
        ArrayList<ArrayList<String>> retrievedEntries = CalendarFXController.calendarDAO.retrieveEntries(username);
        assertEquals(retrievedEntries.size(), 4);
    }

    /**
     * Tests marking two calendar events as hidden.
     * Verifies that the size of the retrieved entries list is 0 after hiding the entries.
     */
    @Test
    void TwoHiddenCalendarEvents() {
        String username = "Jono";
        CalendarFXController.calendarDAO.addEntry(username,"1909-0293-0293","Booking No 1", "24-05-24","25-05-24","14:00","15:00");
        CalendarFXController.calendarDAO.addEntry(username,"1910-0293-0293","Booking No 2", "26-05-24","27-05-24","15:00","16:00");
        CalendarFXController.calendarDAO.removeEntry(username, "1909-0293-0293");
        CalendarFXController.calendarDAO.removeEntry(username, "1910-0293-0293");
        ArrayList<ArrayList<String>> retrievedEntries = CalendarFXController.calendarDAO.retrieveEntries(username);
        assertEquals(retrievedEntries.size(), 0);
    }

    /**
     * Tests marking two out of three calendar events as hidden.
     * Verifies that the size of the retrieved entries list is 1 after hiding two entries.
     */
    @Test
    void OneCalendarEventAvailable() {
        String username = "Jackson";
        CalendarFXController.calendarDAO.addEntry(username,"2909-0293-0293","Booking No 1", "24-05-24","25-05-24","14:00","15:00");
        CalendarFXController.calendarDAO.addEntry(username,"2910-0293-0293","Booking No 2", "26-05-24","27-05-24","15:00","16:00");
        CalendarFXController.calendarDAO.addEntry(username,"2811-0293-0293","Booking No 3", "28-05-24","28-05-24","10:00","11:00");
        CalendarFXController.calendarDAO.removeEntry(username, "2909-0293-0293");
        CalendarFXController.calendarDAO.removeEntry(username, "2910-0293-0293");
        ArrayList<ArrayList<String>> retrievedEntries = CalendarFXController.calendarDAO.retrieveEntries(username);
        assertEquals(retrievedEntries.size(), 1);
    }
}