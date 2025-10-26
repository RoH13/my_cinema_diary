package com.cinema.dao;

import com.cinema.model.*;
import com.cinema.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    final String nextId = "select coalesce(max(id), 0) from movie;";
    final String insertMovie = "insert into movie values(?,?,?)";
    final static String selectAllMovies = "select * from Movie";
    public MovieDAO() throws SQLException {

    }


    public static void selectAllMovies() throws SQLException {
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(selectAllMovies);
        List<Movie> res = new ArrayList<Movie>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String director = rs.getString("director");
            String genre = rs.getString("genre");
            int year = rs.getInt("date_year");
            int duration = rs.getInt("duration");
            int raiting = rs.getInt("rating");
            System.out.println(new Movie(id,title,director,genre,year,duration,raiting));
        }
    }
    public Movie insertMovie(String title, String name, String genre, int year, int duration, int raiting) throws SQLException {
        int id = getNextId();
        PreparedStatement ps = connection.prepareStatement(insertMovie);
        return new Movie(id, title, name, genre, year, duration, raiting);
    }
    public int getNextId() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(nextId);
        ResultSet rs = ps.executeQuery();
        return rs.getInt(1);
    };
}
