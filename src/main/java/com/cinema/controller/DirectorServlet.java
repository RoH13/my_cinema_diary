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

@WebServlet("/director")
public class DirectorServlet extends HttpServlet {
    private DirectorDAO directorDao;


    @Override
    public void init() throws ServletException {
        this.directorDao = new DirectorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("showAddForm".equals(action)) {
            showAddForm(req, resp);
        } else if ("list".equals(action)) {
            listOfDirectors(req, resp);
        }
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("adddirector.jsp").forward(req, resp);
    }

    private void listOfDirectors(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            List<Director> directors = directorDao.findALL();
            req.setAttribute("directors", directors);
            req.getRequestDispatcher("listdirectors.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading directors list", e);
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstname = req.getParameter("firstname");
        String secondname = req.getParameter("secondname");
        try {
            directorDao.save(firstname, secondname);
            resp.sendRedirect("director?action=list");
        } catch (SQLException e) {
            throw new ServletException("Error saving movie: " + e.getMessage(), e);
        }
    }

}
