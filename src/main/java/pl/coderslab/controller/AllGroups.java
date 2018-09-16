package pl.coderslab.controller;

import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AllGroups", urlPatterns = "/allgroups")
public class AllGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = Group.loadAllClasses();
        request.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/view/groups.jsp").forward(request, response);
    }
}
