package pl.coderslab.entity;

public class User {
    private int id = 0;
    private String email = null;
    private String username = null;
    private String password = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

