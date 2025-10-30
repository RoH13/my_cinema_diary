package com.cinema.dao;


import com.cinema.models.Director;
import com.cinema.models.Genre;
import com.cinema.models.Movie;
import com.cinema.util.DataBaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    Connection connection;
    DirectorDAO directorDao;
    GenreDAO genreDao;
    public MovieDAO() throws SQLException {
        try {
            this.connection = DataBaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Error with database connection", e);
        }
        this.directorDao = new DirectorDAO();
        this.genreDao = new GenreDAO();
    }

    public MovieDAO(Connection connection){
        this.connection = connection;
        this.directorDao = new DirectorDAO();
        this.genreDao = new GenreDAO();
    }




    private static final String FIND_ALL_SQL = "SELECT * FROM movie";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM movie WHERE id = ?";
    private static final String FIND_BY_TITLE_SQL = "SELECT * FROM movie WHERE title = ?";
    private static final String INSERT_SQL = "INSERT INTO movie (title, director, genre, year, duration, rating) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM movie WHERE id = ?";
    private static final String DELETE_BY_TITLE_SQL = "DELETE FROM movie WHERE title = ?";
    private static final String UPDATE_RATING_SQL = "UPDATE movie SET rating = ? WHERE id = ?";




   public List<Movie> findAll() throws SQLException {
        try(Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(FIND_ALL_SQL)){
            return getListFromResultSet(rs);
        }
    }

    public Movie findById(int id) throws SQLException {
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

    public Movie findById(String title) throws SQLException {

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



    public void save(Movie movie) throws SQLException {

        try(PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getDirector().getId());
            ps.setInt(3, movie.getGenre().getId());
            ps.setInt(4, movie.getYear());
            ps.setInt(5, movie.getDuration());
            ps.setInt(6, movie.getRating());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving movie", e);
        }
    }




    public void delete(int id) throws SQLException {
       try(PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID_SQL)) {
           ps.setInt(1, id);
           ps.execute();
       }
    }

    public void deleteByTitle(String title) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_TITLE_SQL)) {
            ps.setString(1, title);
            ps.execute();
        }
    }

   public void updateRating(int id, int rating) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(UPDATE_RATING_SQL)) {
            ps.setInt(1, rating);
            ps.setInt(2, id);
            ps.execute();
        }
    }
    private List<Movie> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Movie> res = new ArrayList<>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int directorId = rs.getInt("director");
            int genreId = rs.getInt("genre");
            int year = rs.getInt("year");
            int duration = rs.getInt("duration");
            int rating = rs.getInt("rating");
            Director director = directorDao.getDirectorById(directorId);
            Genre genre = genreDao.getGenreById(genreId);
            res.add(new Movie(id,title,director,genre,year,duration,rating));
        }
        return res;
    }
    private Movie getMovieFromResultSet(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setTitle(rs.getString("title"));


        int directorId = rs.getInt("director");
        int genreId = rs.getInt("genre");

        Director director = directorDao.getDirectorById(directorId);
        Genre genre = genreDao.getGenreById(genreId);

        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setYear(rs.getInt("year"));
        movie.setDuration(rs.getInt("duration"));
        movie.setRating(rs.getInt("rating"));


        return movie;
    }


}
