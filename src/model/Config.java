package model;

/**
 * Created by Oscar on 12-10-2015.
 */
public class Config {

    public Config(){


    }


}

// Create inner class Json
class Json{

    //Create variables for each of the JSON objects.
    private String host;
    private String port;
    private String username;
    private String password;
    private String dbname;

    //Created getters and setters for each of the variables.
    public String getDbname() {
        return dbname;
    }
    public String getHost() {
        return host;
    }
    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }
    public String getUsername() {
        return username;
    }
}
