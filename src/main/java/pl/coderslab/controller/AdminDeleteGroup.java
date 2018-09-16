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

@WebServlet(name = "AdminDeleteGroup", urlPatterns = "/adminDeleteGroup")
public class AdminDeleteGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupId = request.getParameter("id");
        PrintWriter writer = response.getWriter();
        if (groupId != null) {
            try {
                int id = Integer.parseInt(groupId);
                Group group = new Group();
                group.setId(id);
                try {
                    group.delete();
                    response.sendRedirect("/adminAllGroups");
                } catch (SQLException e) {
                    e.printStackTrace();
                    writer.append("Operacja usunięcia nie powiodła się.");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                writer.append("Podane id nie jest liczbą!");
            }
        } else {
            writer.append("Nie podano id groupy, proszę nie mieszać w adresie URL");
        }
    }
}
