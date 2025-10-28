package com.cinema.service;

/**
 * Hello world!
 *
 */
import com.cinema.dao.*;
import com.cinema.models.Director;
import com.cinema.models.Genre;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {

      //MovieDAO.delete("The green elephant");
      //MovieDAO.save("The green elephant", "Svetlana Baskova", "trash", 1999, 120, 10);
      // MovieDAO.updateRating(9,10);
      // System.out.println(MovieDAO.findById(9));
       // System.out.println(MovieDAO.findAll());
      /* System.out.println(DirectorDAO.findALL());
        DirectorDAO.save(new Director(2, "Alex", "Balabanov"));
        System.out.println(DirectorDAO.findALL());*/
        System.out.println(GenreDAO.findAll());
        GenreDAO.save(new Genre(4, "comedy"));
        System.out.println(GenreDAO.findAll());
    }
}
