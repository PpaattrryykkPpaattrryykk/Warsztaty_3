package pl.coderslab.controller;

import pl.coderslab.model.Group;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminAddUser", urlPatterns = "/adminAddUser")
public class AdminAddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User(userName, email, password);
        String classId = request.getParameter("userGroupId");
        if (classId != null) {
            try {
                int groupId = Integer.parseInt(classId);
                user.setUserGroupId(groupId);
                try {
                    user.saveToDB();
                    response.sendRedirect("/adminAllUsers");
                } catch (SQLException e) {
                    e.printStackTrace();
                    writer.append("Nie zapisano grupy, spróbuj ponownie");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                writer.append("Podany id grupy nie jest liczbą");
            }
        } else {
            writer.append("Nie podano grupy");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = Group.loadAllClasses();
        request.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/view/adminAddUser.jsp").forward(request, response);
    }
}
