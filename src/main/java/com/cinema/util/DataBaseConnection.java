package com.cinema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static Connection getConnection() throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/cinemadiary" ;
        String name = "postgres";
        String password = "postgres";;
        return DriverManager.getConnection(url, name, password);
    }
}

