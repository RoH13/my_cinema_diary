package com.cinema.dao;

import com.cinema.models.Genre;
import com.cinema.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
  static Connection connection;
    static {
        try {
            connection = DataBaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    private static final String FIND_ALL_SQL = "SELECT id, name FROM genre";
    private static final String FIND_BY_ID_SQL = "SELECT id, name FROM genre WHERE id = ?";
    private static final String INSERT_SQL = "INSERT INTO genre (name) VALUES (?) ON CONFLICT(name) DO NOTHING";


    public static List<Genre> findAll() throws SQLException {
       try (PreparedStatement s = connection.prepareStatement(FIND_ALL_SQL);
        ResultSet rs = s.executeQuery();) {
           return getListFromResultSet(rs);
       }
    }
    private static List<Genre> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Genre> res = new ArrayList<Genre>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            res.add(new Genre(id, name));
        }
        return res;
     }

     public static void save(Genre genre) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setString(1, genre.getName());
            ps.execute();
        }
     }

     public static Genre getGenreById(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                List<Genre> genres = getListFromResultSet(rs);
                if (genres.isEmpty()) {
                    throw new SQLException("Genre not found with id: " + id);
                }
                return genres.get(0);
            }
        }
     }

}
