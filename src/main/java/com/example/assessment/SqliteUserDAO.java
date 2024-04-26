package com.example.assessment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteUserDAO {

    Connection connection;

    public SqliteUserDAO() {
        this.connection = DatabaseConnection.getInstance();
    }

    public void addUser(String username, String password, String salt) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO UserAccounts(username, hashedPassword, salt)" +
                            " VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, salt);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void VerifyUser(String username, String password) {
    }

}
