package com.cinema.dao;


import com.cinema.models.Movie;
import com.cinema.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {
    static Connection connection;
    public MovieDaoImpl(Connection connection){

    }
    static {
        try {
            connection = DataBaseConnection.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<Movie> findAll() throws SQLException {
        String command = "SELECT * FROM movie";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(command);
        return getListFromResultSet(rs);
    }

    @Override
    public void save(Movie movie) throws SQLException {
        String command = "INSERT INTO movie values(?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setString(1, movie.getTitle());
        ps.setString(2, movie.getDirector());
        ps.setString(3, movie.getGenre());
        ps.setInt(4, movie.getYear());
        ps.setInt(5, movie.getDuration());
        ps.setInt(6, movie.getRating());
        ps.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        String command = "delete from movie where id = ?";
        PreparedStatement ps = connection.prepareStatement(command);
        ps.setInt(1,id);
        ps.execute();
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
            int rating = rs.getInt("rating");
            res.add(new Movie(id,title,director,genre,year,duration,rating));
        }
        return res;
    }

     @Override
     public Movie findById(int id) throws SQLException {
        Movie m;
        String command = "select * from movie where id = ?";
         try (PreparedStatement ps = connection.prepareStatement(command);
         ps.setInt(1, id);
         ResultSet rs = ps.executeQuery();)   {
            m = (Movie)rs.getObject(1)     ;
         }catch (SQLException ex) {
             return Optional.empty();
         }
          return m;
     }

}
