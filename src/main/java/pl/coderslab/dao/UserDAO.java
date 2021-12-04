package pl.coderslab.dao;


import pl.coderslab.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.Arrays;

public class UserDAO {

    private static final String DELETE_QUERY = "delete from users where id = ?";
    private static final String INSERT_QUERY = "insert into users (email,username,password) values (?,?,?);";
    private static final String SHOW_QUERY = "select * from users where id = ?;";
    private static final String UPDATE_QUERY = "update users set email = ?,username = ?, password = ? where id = ?;";
    private static final String SHOWALL_QUERY = "SELECT * from users";

    public static User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, BCrypt.hashpw(User.getPassword(), BCrypt.gensalt()));
            statement.executeUpdate();
            System.out.println("User created");
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                long rob = resultSet.getLong(1);
                System.out.println("Inserted ID: " + rob);

            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String columnName : columnNames) {
                    System.out.println(resultSet.getString(columnName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(int id) {
        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User with ID " + id + " successfully deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static User showUser(int id) {
        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                int tempId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String pw = resultSet.getString("password");
                System.out.println("User ID: " + tempId);
                System.out.println("Email: " + email);
                System.out.println("Username: " + username);
                System.out.println("Password: " + pw);
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void update(User user) {
        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setInt(4, user.getId());
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

    public static User[] showUsers(){
    try(Connection connection = DBUtil.getConnection()){
        User[] users = new User[0];
        PreparedStatement preparedStatement = connection.prepareStatement(SHOWALL_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            users = addToArray(user,users);
            System.out.println(String.valueOf(user.getId())+" "+ user.getEmail()+" "+ user.getUserName()+" "+user.getPassword());
        }
        return users;

    }catch (SQLException e){
        e.printStackTrace();
        return null;
    }

    }

}

