package com.example.assessment;

import java.sql.Connection;
import java.util.ArrayList;

public interface EntryDAOInterface{
    public void addEntry(String username, String entryID, String title, String startDate, String endDate, String startTime, String endTime);

    public void removeEntry(String username, String entryID);

    public ArrayList<ArrayList<String>> retrieveEntries(String username);

}
