package com.cinema.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        InputStream in = DataBaseConnection.class.getClassLoader().getResourceAsStream("application.properties");
        properties.load(in);
        String url = properties.getProperty("db.url") ;
        String name = properties.getProperty("db.name");
        String password = properties.getProperty("db.password");;
        return DriverManager.getConnection(url, name, password);
    }
}

