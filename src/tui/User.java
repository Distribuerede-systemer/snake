package tui;

/**
 * Created by Jakob Frederik Frank on 12-10-2015.
 */
public class User {

    private String _username;
    private String _password;

    public User(String username, String pass){
        _username = username;
        _password = pass;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public void setUsername(String username) {
        _username = username;
    }
}
