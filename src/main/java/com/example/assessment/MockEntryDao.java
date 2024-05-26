package com.example.assessment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the EntryDAOInterface for SQLite database.
 * Manages entry-related database operations such as adding entries and retrieving entries.
 */
public class MockEntryDao implements EntryDAOInterface {

    public ArrayList<ArrayList<String>> Entries = new ArrayList<ArrayList<String>>();

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

    @Override
    public void removeEntry(String username, String id) {
        for (List<String> entry : Entries) {
            if (Objects.equals(entry.get(0), username) & (Objects.equals(entry.get(1), id))) {
                entry.set(7, "hidden");
            }
        }
    }

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
