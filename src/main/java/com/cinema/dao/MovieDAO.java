package com.cinema.dao;

import com.cinema.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class MovieDAO {
    Connection connection = DataBaseConnection.getConnection();

    public MovieDAO() throws SQLException {

    }
}
