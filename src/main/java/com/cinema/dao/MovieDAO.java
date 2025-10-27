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

    final static String NEXTId = "select coalesce(max(id), 0) from movie;";
    final static String INSERTMOVIE = "INSERT INTO movie (title, director, genre, date_year, duration, rating) VALUES (?,?,?,?,?,?)";
    final static String SELECTALL = "select * from Movie;";
    final static String SORTBYRATING = "select * from movie order by rating ?;";
    final static String SORTBYTITLE = "select * from movie order by title ?;";
    final static String SORTBYDIRECT = "select * from movie order by rating ?;";
    final static String SORTBYYEAR = "select * from movie order by year ?;";



    public MovieDAO() throws SQLException {

    }

    public static List<Movie> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Movie> res = new ArrayList<Movie>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String director = rs.getString("director");
            String genre = rs.getString("genre");
            int year = rs.getInt("date_year");
            int duration = rs.getInt("duration");
            int raiting = rs.getInt("rating");
            res.add(new Movie(id,title,director,genre,year,duration,raiting));
        }
        return res;
    }
    public static List<Movie> selectAllMovies() throws SQLException {
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(SELECTALL);
        return getListFromResultSet(rs);
    }

    public static List<Movie> sortedByRating() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SORTBYRATING);
        ps.setString(1, "DESC");
        ResultSet rs = ps.executeQuery();
        return getListFromResultSet(rs);
    }

    public static List<Movie> sortedByRatingReverce() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SORTBYRATING);
        ps.setString(1, "ASC");
        ResultSet rs = ps.executeQuery();
        return getListFromResultSet(rs);
    }

    public static Movie insertMovie(String title, String name, String genre, int year, int duration, int rating) throws SQLException {
        int id = getNextId();
        PreparedStatement ps = connection.prepareStatement(INSERTMOVIE);
        ps.setString(1, title);
        ps.setString(2, name);
        ps.setString(3, genre);
        ps.setInt(4, year);
        ps.setInt(5, duration);
        ps.setInt(6, rating);
        ps.execute();

        return new Movie(id, title, name, genre, year, duration, rating);
    }
    public static int getNextId() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(NEXTId);
        ResultSet rs = ps.executeQuery();
        int res = rs.getInt(1);
        ps.close();
        rs.close();
        return res;
    };
}
