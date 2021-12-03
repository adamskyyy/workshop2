package pl.coderslab.entity;

public class User {
    private static int id = 0;
    private static String email = null;
    private static String username = null;
    private static String password = null;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUserName() {return username;
    }
}

