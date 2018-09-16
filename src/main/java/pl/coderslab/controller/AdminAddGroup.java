package pl.coderslab.controller;

import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet(name = "adminAddGroup", urlPatterns = "/adminAddGroup")
public class AdminAddGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer = response.getWriter();
        Group group = new Group();
        String className = request.getParameter("className");
        group.setClassName(className);
        try {
            group.saveToDB();
//            writer.append("Dodano nową grupę: " + className);
        } catch (SQLException e) {
            e.printStackTrace();
            writer.append("Nie zapisano grupy, spróbuj ponownie");
        }
        response.sendRedirect("/adminAddedGroup");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/adminAddGroup.jsp").forward(request, response);
    }
}
