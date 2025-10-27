package com.cinema.dao;

import com.cinema.models.Movie;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
    Optional<Movie> findById(int id) throws SQLException;
    List<Movie> findAll() throws SQLException;
    void save(Movie movie) throws SQLException;
    void delete(int id) throws SQLException;
}
