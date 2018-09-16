package pl.coderslab.controller;

import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "AdminEditGroup", urlPatterns = "/adminEditGroup")
public class AdminEditGroup extends HttpServlet {
    int group_id;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String className = request.getParameter("className");
        PrintWriter writer = response.getWriter();
        if (className != null) {
            Group group = new Group();
            group.setClassName(className);
            group.setId(group_id);
            try {
                group.saveToDB();
                response.sendRedirect("/adminAllGroups");
            } catch (SQLException e) {
                e.printStackTrace();
                writer.append("edycja nie powiodła się");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("id");
        if (id != null) {
            try {
                group_id = Integer.parseInt(id);
                getServletContext().getRequestDispatcher("/WEB-INF/view/adminEditGroup.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                writer.append("Podane id nie jest liczbą.");
            }
        } else {
            writer.append("Nie przekazano id grupy.");
        }

    }

//    public void init() {
//
//    }
}
