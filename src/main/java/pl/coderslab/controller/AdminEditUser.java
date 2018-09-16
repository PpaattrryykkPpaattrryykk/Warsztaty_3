package pl.coderslab.controller;

import pl.coderslab.model.Group;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminEditUser", urlPatterns = "/adminEditUser")
public class AdminEditUser extends HttpServlet {
    int userId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String classId = request.getParameter("userGroupId");
        try {
            User user = User.loadUserById(userId);
            user.setId(userId);
            if (userName != null && !userName.equals(user.getUserName())) {
                user.setUserName(userName);
            }
            if (password != null && !user.checkNewPassword(password)) {
                user.setPassword(password);
            }
            if (email != null && !email.equals(user.getEmail())) {
                user.setEmail(email);
            }
            try {
                int groupId = Integer.parseInt(classId);
                if (groupId != user.getUserGroupId()) {
                    user.setUserGroupId(groupId);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                writer.append("ID grupy nie jest liczbą");
            }
            user.saveToDB();
            response.sendRedirect("/adminAllUsers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("id");
        if (id != null) {
            try {
                userId = Integer.parseInt(id);
                List<Group> groups = Group.loadAllClasses();
                request.setAttribute("groups", groups);
                getServletContext().getRequestDispatcher("/WEB-INF/view/adminEditUser.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                writer.append("Podane id nie jest liczbą.");
            }
        } else {
            writer.append("Nie przekazano id grupy.");
        }
    }
}
