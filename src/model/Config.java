package model;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by Oscar on 12-10-2015.
 */

// Create inner class Json
public class Config{

    public static void init(){


    }

    //Create variables for each of the JSON objects.
    private static String host;
    private static String port;
    private static String username;
    private static String password;
    private static String dbname;

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
