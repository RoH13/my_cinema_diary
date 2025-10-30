package com.cinema.dao;

import com.cinema.models.Director;
import com.cinema.models.Movie;
import com.cinema.util.DataBaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAO {
    Connection connection;
    public DirectorDAO() {
        try {
           connection = DataBaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Error with database connection", e);
        }
    }

    private static final String FIND_ALL_SQL = "SELECT * FROM director";
    private static final String INSERT_SQL =
            "INSERT INTO director (first_name, second_name) VALUES (?, ?) " +
                    "ON CONFLICT (first_name, second_name) DO NOTHING";
    private static final String DELETE_SQL = "DELETE FROM director WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM director WHERE id = ?";
    private static final String FIND_ID_SQL = "SELECT id from director where first_name = ? and second_name = ?";

    public List<Director> findALL() throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet rs = ps.executeQuery();
            return getListFromResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Director> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Director> res = new ArrayList<Director>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String secondName = rs.getString("second_name");
            res.add(new Director(id, firstName, secondName));
        }
        return res;
    }

    public void save(Director director) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setString(1, director.getFirstName());
            ps.setString(2, director.getSecondName());
            ps.execute();
        }
    }

    public void save(String firstname, String secondname) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setString(1, firstname);
            ps.setString(2, secondname);
            ps.execute();
        }
    }

    public void delete(int id) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    public Director getDirectorById(int id) throws SQLException {
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


    public int getIdByDirector(Director director) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement(FIND_ID_SQL)) {
            ps.setString(1, director.getFirstName());
            ps.setString(2, director.getSecondName());
            ResultSet rs = ps.executeQuery();
            int res = -1;
            while(rs.next()) {
                res = rs.getInt("id");
            }
            if (res == -1) {
                throw new SQLException("Id not found");
            }
            return res;
        }
    }

}
