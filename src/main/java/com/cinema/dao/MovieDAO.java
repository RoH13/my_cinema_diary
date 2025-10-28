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
    public MovieDAO(Connection connection){

    }
    static {
        try {
            connection = DataBaseConnection.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




   public static List<Movie> findAll() throws SQLException {
        String command = "SELECT * FROM movie";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(command);
        return getListFromResultSet(rs);
    }

    public static Movie findById(int id) throws SQLException {
/// используем только когда данный айди есть в базе, на будущее мб переделать с опшнл
        final String command = "select * from movie where id = ?";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return getListFromResultSet(rs).get(0);
    }

    public static Movie findById(String title) throws SQLException {
/* используем только когда данный тайтл есть в базе,
        не работает с одноименными записями
        мб переделать с опшнл
*/
        final String command = "select * from movie where id = ?";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        return getListFromResultSet(rs).get(0);
    }



    public static void save(String title, Director director, Genre genre, int year, int duration, int rating) throws SQLException {
    //сохраняем набор значений а не объект мб это плохо, с объектами проблема в нужном айди
        DirectorDAO.save(director);
        String command = "INSERT INTO movie (title, director, genre, date_year, duration, rating) values(?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setString(1, title);
        ps.setString(2, director);
        ps.setString(3, genre);
        ps.setInt(4, year);
        ps.setInt(5, duration);
        ps.setInt(6, rating);
        ps.execute();
    }


    public static void delete(int id) throws SQLException {
        String command = "delete from movie where id = ?";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setInt(1,id);
        ps.execute();
    }

    public static void delete(String title) throws SQLException {
        final String command = "delete from movie where title = ?";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setString(1,title);
        ps.execute();
    }

   public static void updateRating(int id, int rating) throws SQLException {
        final String command = "update movie set rating = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setInt(1,rating);
        ps.setInt(2,id);
        ps.execute();
    }
    public static List<Movie> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Movie> res = new ArrayList<Movie>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int director = rs.getInt("director");
            int genre = rs.getInt("genre");
            int year = rs.getInt("date_year");
            int duration = rs.getInt("duration");
            int rating = rs.getInt("rating");
            DirectorDAO.getDirectorById(director);
            Director director = new Director();
            res.add(new Movie(id,title,director,genre,year,duration,rating));
        }
        return res;
    }



}
