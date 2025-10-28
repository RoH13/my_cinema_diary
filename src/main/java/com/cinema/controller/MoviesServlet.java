package com.cinema.controller;

import com.cinema.dao.MovieDAO;
import com.cinema.models.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/movies")
public class MoviesServlet extends HttpServlet {
   // @Override
   /* protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        List<Movie> movies;
        try {
            movies = MovieDAO.findAll();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("movies.jsp").forward(req, resp);
    }*/
}
