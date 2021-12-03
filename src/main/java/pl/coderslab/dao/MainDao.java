package pl.coderslab.dao;

import pl.coderslab.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class MainDao {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(UserDAO.showUsers()));


    }
}
