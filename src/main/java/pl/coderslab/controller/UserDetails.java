package pl.coderslab.controller;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserDetails", urlPatterns = "/userDetails")
public class UserDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        User user = new User();
        List<Solution> solutions = new ArrayList<>();
        PrintWriter writer = response.getWriter();
        if (userId != null) {
            try {
                int id = Integer.parseInt(userId);
                try {
                    user = User.loadUserById(id);
                    solutions = Solution.loadAllByUserId(id);
                    request.setAttribute("solutions", solutions);
                    request.setAttribute("user", user);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/view/studentDetails.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                writer.append("Podany parametr nie jest liczbą!");
                e.printStackTrace();
            }
        } else {
            writer.append("Brak parametru id.");
        }
    }
}
