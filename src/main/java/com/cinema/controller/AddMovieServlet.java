package com.cinema.controller;

import com.cinema.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/addmovie")
public class AddMovieServlet extends HttpServlet {
  /*  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addmovie.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String director = req.getParameter("director");
        String genre = req.getParameter("director");
        int year = Integer.parseInt(req.getParameter("year"));
        int duration = Integer.parseInt(req.getParameter("duration"));
        int rating = Integer.parseInt(req.getParameter("rating"));
        try {
            MovieDAO.save(title, director, genre, year, duration, rating);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
