package pl.coderslab.model;

import pl.coderslab.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
    private int id;
    private String className;

    public Group(String name) {
        this.className = name;
    }

    public Group() {}

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setId(int id) {this.id = id; }

    public int getId() {
        return id;
    }

    public void saveToDB() throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            if (this.id == 0) {
                String sql = "INSERT INTO user_groups(name) VALUES (?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, this.className);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            } else {
                String sql = "UPDATE user_groups SET name=? where id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, this.className);
                preparedStatement.setInt(2, this.id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public Group loadClassById(int id) throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            String sql = "SELECT * FROM user_groups where id=?";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Group loadedClass = new Group();
                loadedClass.id = resultSet.getInt("id");
                loadedClass.className = resultSet.getString("name");
                return loadedClass;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public ArrayList<Group> loadAllClasses() {
        try {
            Connection connection = DbUtil.getConn();
            ArrayList<Group> classes = new ArrayList<Group>();
            String sql = "SELECT * FROM user_groups";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group loadedClass = new Group();
                loadedClass.id = resultSet.getInt("id");
                loadedClass.className = resultSet.getString("name");
                classes.add(loadedClass);
            }
            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete() throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            if (this.id != 0) {
                String sql = "DELETE FROM user_groups WHERE id=?";
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

}
