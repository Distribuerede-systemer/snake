package tui;

/**
 * Created by Jakob Frederik Frank on 12-10-2015.
 */
public class User {

    private String username;
    private String password;

    public User(String usrname, String pass){
        username = usrname;
        password = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
