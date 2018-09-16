package pl.coderslab.controller;

import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "SolutionDescription", urlPatterns = "/description")
public class SolutionDescription extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String solutionId = request.getParameter("id");
        boolean load = true;
        int id = -1;
        try {
            id = Integer.parseInt(solutionId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            load = false;
        }
        if (load) {
            try {
                Solution solution = Solution.loadSolutionById(id);
                if (solution != null) {
                    request.setAttribute("solution", solution);
                    getServletContext().getRequestDispatcher("/WEB-INF/view/exerciseDescription.jsp").forward(request, response);
                } else {
                    writer.append("Nie znaleziono rozwiązania o podanym id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            writer.append("Nie znaleziono rozwiązania o podanym id");
        }
    }
}
