package pl.coderslab.controller;

import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomepageController extends HttpServlet {
    int numberSolutions;

    public void init() {
        numberSolutions = Integer.parseInt(getInitParameter("number-solutions"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Solution> solutions = Solution.loadAllSolutions(numberSolutions);
        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
    }
}