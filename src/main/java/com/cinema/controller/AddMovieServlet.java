package com.cinema.controller;

import com.cinema.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/addmovie")
public class AddMovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html>" +
                "<head></head>" +
                "<body>" +
                "<form action = 'addmovie' method = 'post'>" +
                "Write the film title <br> <input type = 'text' name = 'title'> <br>" +
                "Write director of the film <br> <input type = 'text' name = 'director'> <br>" +
                "Write genre of the film <br> <input type = 'text' name = 'genre'> <br>" +
                "Write the year of film <br> <input type = 'number' name = 'year' min = '1800' > <br>" +
                "Write the duration of film in minutes <br> <input type = 'number' name = 'duration' min = '0'> <br>" +
                "Write your rating of the film <br> <input type = 'number' name = 'rating' min = '1' max = '10' step = '1' required> <br>" +
                "<input type = 'submit' name = 'submit'>" +
                "</form>"+
                "</body>"
                );
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
            MovieDAO.insertMovie(title, director, genre, year, duration, rating);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
