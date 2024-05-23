package com.example.assessment;

import java.util.ArrayList;

/**
 * Interface for managing entry-related operations.
 */
public interface EntryDAOInterface{
    /**
     * Adds a new entry with the specified details.
     *
     * @param username The username of the user adding the entry.
     * @param entryID The ID of the entry.
     * @param title The title of the entry.
     * @param startDate The start date of the entry.
     * @param endDate The end date of the entry.
     * @param startTime The start time of the entry.
     * @param endTime The end time of the entry.
     */
    public void addEntry(String username, String entryID, String title, String startDate, String endDate, String startTime, String endTime);

    /**
     * Removes the entry with the specified ID for the given username.
     *
     * @param username The username of the user removing the entry.
     * @param entryID The ID of the entry to be removed.
     */
    public void removeEntry(String username, String entryID);

    /**
     * Retrieves all entries associated with the specified username.
     *
     * @param username The username for which to retrieve entries.
     * @return An ArrayList containing ArrayLists of entry details.
     *         Each inner ArrayList represents an entry and contains:
     *         [entryID, title, startDate, endDate, startTime, endTime]
     */
    public ArrayList<ArrayList<String>> retrieveEntries(String username);

}
