package com.example.assessment;

public interface UserDAOInterface{
    public boolean addUser(String username, String password);

    public boolean changeUser(String username, String password, String currentpass);

    public UserAccount verifyUser(String username, String password);

    public int numberOfAccounts(String username);

    public boolean correctPassword(String username, String password);
}
