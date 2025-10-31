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
    private static Connection instance;
    static {
        try {
            initializeConnection();

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    private static void initializeConnection() throws SQLException, IOException {
        Properties properties = new Properties();

        try (InputStream in = DataBaseConnection.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (in == null) {
                throw new IOException("application.properties not found in classpath");
            }

            properties.load(in);
        }
        String url = properties.getProperty("db.url") ;
        String name = properties.getProperty("db.name");
        String password = properties.getProperty("db.password");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found", e);
        }

        instance = DriverManager.getConnection(url, name, password);
    }
    public static Connection get() {
        return instance;
    }
}



