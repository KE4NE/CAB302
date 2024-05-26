package com.example.assessment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mock implementation of the EntryDAOInterface.
 * This class simulates database operations related to entries, such as adding, removing, and retrieving entries.
 */
public class MockEntryDao implements EntryDAOInterface {

    public ArrayList<ArrayList<String>> Entries = new ArrayList<ArrayList<String>>();

    /**
     * Adds a new entry to the mock database.
     *
     * @param username the username associated with the entry.
     * @param entryID the ID of the entry.
     * @param title the title of the entry.
     * @param startDate the start date of the entry.
     * @param endDate the end date of the entry.
     * @param startTime the start time of the entry.
     * @param endTime the end time of the entry.
     */
    @Override
    public void addEntry(String username, String entryID, String title, String startDate, String endDate, String startTime, String endTime) {
        ArrayList<String> entry = new ArrayList<>();
        entry.add(username);
        entry.add(entryID);
        entry.add(title);
        entry.add(startDate);
        entry.add(endDate);
        entry.add(startTime);
        entry.add(endTime);
        entry.add("visible"); // Set hidden value;
        Entries.add(entry);
    }

    /**
     * Marks an entry as hidden in the mock database.
     *
     * @param username the username associated with the entry.
     * @param id the ID of the entry.
     */
    @Override
    public void removeEntry(String username, String id) {
        for (List<String> entry : Entries) {
            if (Objects.equals(entry.get(0), username) & (Objects.equals(entry.get(1), id))) {
                entry.set(7, "hidden");
            }
        }
    }

    /**
     * Retrieves all visible entries associated with the given username from the mock database.
     *
     * @param username the username whose entries are to be retrieved.
     * @return a list of entries associated with the given username.
     */
    @Override
    public ArrayList<ArrayList<String>> retrieveEntries(String username) {
        ArrayList<ArrayList<String>> userEntries = new ArrayList<ArrayList<String>>();
        for (ArrayList<String> entry : Entries) {
            if (Objects.equals(entry.get(0), username) & Objects.equals(entry.get(7), "visible")) {
                userEntries.add(entry);
            }
        }
        return userEntries;
    }


}
