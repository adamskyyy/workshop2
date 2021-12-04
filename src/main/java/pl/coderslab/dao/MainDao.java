package pl.coderslab.dao;

import pl.coderslab.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class MainDao {

    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection()) {
            User userUpdate = new User();
            userUpdate = UserDAO.showUser(1);
            userUpdate.setUsername("test2");
            userUpdate.setEmail("test2@test.pl");
            userUpdate.setPassword("test2");
            UserDAO.update(userUpdate);

//            UserDAO.create(user);
//            UserDAO.showUsers();
//            UserDAO.remove(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}




