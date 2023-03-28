package me.aaron;

import java.sql.*;


public class SQLConnect {

    String username = "root";
    String password = "";
    String host = "localhost";
    String database = "oosdproject";
    Connection connection = null;
    String port = "3306";


    public void connect() {
        String jdbc = "jdbc:mysql://" + host + ":" + port + "/" + database;
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(jdbc, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String s) {
        try {
            return connection.prepareStatement(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
