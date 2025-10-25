package com.cinema.dao;

import com.cinema.model.Movie;
import com.cinema.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAO {
    Connection connection = DataBaseConnection.getConnection();
    String nextId = "select coalesce(max(id), 0) from movie;";
    String indertMpvie = "insert into "
    public MovieDAO() throws SQLException {

    }
    public int insertMovie(String title, String name, String genre, int year, int duration, int raiting) throws SQLException {
        int id = getNextId();
        Movie m = new Movie(id, title, name, genre, year, duration, raiting);
    }
    public int getNextId() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(nextId);
        ResultSet rs = ps.executeQuery();
        return rs.getInt(1);
    };
}
