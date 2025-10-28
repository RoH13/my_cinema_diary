package com.cinema.dao;

import com.cinema.models.Director;
import com.cinema.models.Movie;
import com.cinema.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAO {
    static Connection connection;
    static {
        try {
            connection = DataBaseConnection.getConnection();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static final String FIND_ALL_SQL = "SELECT * FROM director";
    private static final String INSERT_SQL =
            "INSERT INTO director (first_name, second_name) VALUES (?, ?) " +
                    "ON CONFLICT (first_name, second_name) DO NOTHING";
    private static final String DELETE_SQL = "DELETE FROM director WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM director WHERE id = ?";


    public static List<Director> findALL() throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet rs = ps.executeQuery();
            return getListFromResultSet(rs);
        }
    }

    public static List<Director> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Director> res = new ArrayList<Director>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String secondName = rs.getString("second_name");
            res.add(new Director(id, firstName, secondName));
        }
        return res;
    }

    public static void save(Director director) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setString(1, director.getFirstName());
            ps.setString(2, director.getSecondName());
            ps.execute();
        }
    }
    public static void delete(int id) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    public static Director getDirectorById(int id) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                List<Director> directors = getListFromResultSet(rs);
                if (directors.isEmpty()){
                    throw new SQLException("Director not found with id: " + id);
                }
                return directors.get(0);
            }
        }
    }

}
