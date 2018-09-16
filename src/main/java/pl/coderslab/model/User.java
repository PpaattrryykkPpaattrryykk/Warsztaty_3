package pl.coderslab.model;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int id;
    private String userName;
    private String password;
    private String email;
    private int userGroupId;

    public User(String username, String email, String password) {
        this.userName = username;
        this.email = email;
        this.setPassword(password);
    }

    public User() { }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkNewPassword(String newPassword) {
        return BCrypt.checkpw(newPassword, password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void saveToDB() throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            if (this.id == 0) {
                String sql = "INSERT INTO users(user_name, email, password, user_group_id) VALUES (?, ?, ?, ?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, this.userName);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                preparedStatement.setInt(4, this.userGroupId);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            } else {
                String sql = "UPDATE users SET user_name=?, email=?, password=?, user_group_id=? where id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, this.userName);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                preparedStatement.setInt(4, this.userGroupId);
                preparedStatement.setInt(5, this.id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public User loadUserById(int id) throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            String sql = "SELECT * FROM users where id=?";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return userDetails(resultSet);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static public User loadUserByEmail(String email) throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            String sql = "SELECT * FROM users where email=?";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return userDetails(resultSet);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User userDetails(ResultSet resultSet) throws SQLException {
        User loadedUser = new User();
        loadedUser.id = resultSet.getInt("id");
        loadedUser.userName = resultSet.getString("user_name");
        loadedUser.password = resultSet.getString("password");
        loadedUser.email = resultSet.getString("email");
        return loadedUser;
    }

    public static ArrayList<User> loadAllUsers() throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            ArrayList<User> users = new ArrayList<User>();
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            userDetailling((ArrayList<User>) users, resultSet);
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<User> loadAllByGroupId(int id) throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            ArrayList<User> usersFromGroup = new ArrayList<User>();
            String sql = "SELECT * FROM users where user_group_id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            userDetailling((ArrayList<User>) usersFromGroup, resultSet);
            return usersFromGroup;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void userDetailling(ArrayList<User> usersFromGroup, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.userName = resultSet.getString("user_name");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            usersFromGroup.add(loadedUser);
        }
    }

    public void delete() throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            if (this.id != 0) {
                String sql = "DELETE FROM users WHERE id=?";
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, this.id);
                preparedStatement.executeUpdate();
                this.id = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    try {
//        connection.closeConnection();
//        } catch (SQLException e) {
//        e.printStackTrace();
//        }

}