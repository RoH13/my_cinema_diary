package com;

/**
 * Hello world!
 *
 */
import com.cinema.dao.*;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {

        MovieDAO.selectAllMovies();
    }
}
