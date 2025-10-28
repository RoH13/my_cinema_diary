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
            throw new RuntimeException(e);
        }
    }
    public static List<Genre> findAll() throws SQLException {
        final String command = "select * from genre";
        PreparedStatement s = connection.prepareStatement(command);
        ResultSet rs = s.executeQuery();
        return getListFromResultSet(rs);
    }
    private static List<Genre> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Genre> res = new ArrayList<Genre>;
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            res.add(new Genre(id, name));
        }
        return res;
     }

     public static void save(Genre genre) {
        final String command = "insert into genre (name) values (?)";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setString(genre.getName());
        ps.execute();
     }


}
