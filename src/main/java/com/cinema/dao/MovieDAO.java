package com.cinema.dao;


import com.cinema.models.Director;
import com.cinema.models.Genre;
import com.cinema.models.Movie;
import com.cinema.util.DataBaseConnection;

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
    public MovieDAO(Connection connection){
        this.connection = connection;
    }
    private static final String FIND_ALL_SQL = "SELECT * FROM movie";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM movie WHERE id = ?";
    private static final String FIND_BY_TITLE_SQL = "SELECT * FROM movie WHERE title = ?";
    private static final String INSERT_SQL = "INSERT INTO movie (title, director, genre, date_year, duration, rating) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM movie WHERE id = ?";
    private static final String DELETE_BY_TITLE_SQL = "DELETE FROM movie WHERE title = ?";
    private static final String UPDATE_RATING_SQL = "UPDATE movie SET rating = ? WHERE id = ?";




   public static List<Movie> findAll() throws SQLException {
        try(Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(FIND_ALL_SQL)){
            return getListFromResultSet(rs);
        }
    }

    public static Movie findById(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                List<Movie> movies = getListFromResultSet(rs);
                if (movies.isEmpty()) {
                    throw new SQLException("Movie not found with id: " + id);
                }
                return movies.get(0);
            }
        }
    }

    public static Movie findById(String title) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_TITLE_SQL)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                List<Movie> movies = getListFromResultSet(rs);
                if (movies.isEmpty()) {
                    throw new SQLException("Movie not found with title: " + title);
                }
                return movies.get(0);
            }
        }
    }



    public static void save(String title, Director director, Genre genre, int year, int duration, int rating) throws SQLException {

        DirectorDAO.save(director);
        try(PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setString(1, title);
            ps.setInt(2, director.getId());
            ps.setInt(3, genre.getId());
            ps.setInt(4, year);
            ps.setInt(5, duration);
            ps.setInt(6, rating);
            ps.execute();
        }
    }


    public static void delete(int id) throws SQLException {
       try(PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID_SQL)) {
           ps.setInt(1, id);
           ps.execute();
       }
    }

    public static void deleteByTitle(String title) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_TITLE_SQL)) {
            ps.setString(1, title);
            ps.execute();
        }
    }

   public static void updateRating(int id, int rating) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(UPDATE_RATING_SQL)) {
            ps.setInt(1, rating);
            ps.setInt(2, id);
            ps.execute();
        }
    }
    public static List<Movie> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Movie> res = new ArrayList<>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int director = rs.getInt("director");
            int genre = rs.getInt("genre");
            int year = rs.getInt("year");
            int duration = rs.getInt("duration");
            int rating = rs.getInt("rating");
            Director dir = DirectorDAO.getDirectorById(director);
            Genre ge = GenreDAO.getGenreById(genre);
            res.add(new Movie(id,title,dir,ge,year,duration,rating));
        }
        return res;
    }



}
