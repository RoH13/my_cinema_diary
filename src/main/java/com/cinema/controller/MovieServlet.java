package com.cinema.controller;

import com.cinema.dao.DirectorDAO;
import com.cinema.dao.GenreDAO;
import com.cinema.dao.MovieDAO;
import com.cinema.models.Director;
import com.cinema.models.Genre;
import com.cinema.models.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/movie")
public class MovieServlet extends HttpServlet {
    private DirectorDAO directorDao;
    private GenreDAO genreDao;
    private MovieDAO movieDao;

    @Override
    public void init() throws ServletException {
        try {
            this.directorDao = new DirectorDAO();
            this.genreDao = new GenreDAO();
            this.movieDao = new MovieDAO();
        } catch (SQLException e) {
            throw new ServletException("Error initializing DAOs", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("showAddForm".equals(action)) {
            showAddForm(req, resp);
        } else if ("list".equals(action)) {
            listOfFilms(req, resp);
        }
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Director> directors = directorDao.findALL();
            List<Genre> genres = genreDao.findAll();

            req.setAttribute("directors", directors);
            req.setAttribute("genres", genres);
            req.getRequestDispatcher("addmovie.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading form data", e);
        }
    }

    private void listOfFilms(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Movie> movies = movieDao.findAll();
            req.setAttribute("movies", movies);
            req.getRequestDispatcher("listmovies.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading movies list", e);
        }
    }
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");


        int directorId = Integer.parseInt(req.getParameter("directorId"));
        int genreId = Integer.parseInt(req.getParameter("genreId"));

        int year = Integer.parseInt(req.getParameter("year"));
        int duration = Integer.parseInt(req.getParameter("duration"));
        int rating = Integer.parseInt(req.getParameter("rating"));

        try {
            Director director = directorDao.getDirectorById(directorId);
            Genre genre = genreDao.getGenreById(genreId);

            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setDirector(director);
            movie.setGenre(genre);
            movie.setYear(year);
            movie.setDuration(duration);
            movie.setRating(rating);

            movieDao.save(movie);

            resp.sendRedirect("movie?action=list");

        } catch (SQLException e) {
            throw new ServletException("Error saving movie: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format in parameters", e);
        }
    }
}