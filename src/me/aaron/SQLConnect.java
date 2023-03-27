package me.aaron;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.SQLException;


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
}
