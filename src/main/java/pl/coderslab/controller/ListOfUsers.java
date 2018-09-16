package pl.coderslab.controller;

import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListOfUsers", urlPatterns = "/groupdetails")
public class ListOfUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupId = request.getParameter("id");
        String className = request.getParameter("className");
        PrintWriter writer = response.getWriter();
        if (groupId != null) {
            try {
                int id = Integer.parseInt(groupId);
                try {
                    List<User> users = User.loadAllByGroupId(id);
                    request.setAttribute("users", users);
                    request.setAttribute("className", className);
                    getServletContext().getRequestDispatcher("/WEB-INF/view/groupStudents.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                writer.append("Podana wartość nie jest liczbą");
                e.printStackTrace();
            }
        } else {
            writer.append("brak parametru");
        }
    }
}
