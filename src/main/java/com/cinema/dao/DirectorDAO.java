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
    public static List<Director> findALL() throws SQLException {
        final String command = "select * from director";
        PreparedStatement ps = connection.prepareStatement(command);
        ResultSet rs = ps.executeQuery();
        return getListFromResultSet(rs);
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
        final String command = "insert into director (first_name, second_name) " +
       " values (?, ?)" +
       " on conflict (first_name,second_name) do nothing;";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setString(1, director.getFirstName());
        ps.setString(2, director.getSecondName());
        ps.execute();
    }
    public static void delete(int id) throws SQLException {
        final String command = "delete from director when id = ?";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setInt(1, id);
        ps.execute();
    }

    public static Director getDirectorById(int id) throws SQLException {
        final String command = "select from director where id = ?";
        PreparedStatement ps = connection.prepareStatement(command);
        ResultSet rs = ps.executeQuery();
        return getListFromResultSet(rs).get(0);
    }

}
