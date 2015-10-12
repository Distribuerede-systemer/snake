package model;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by Oscar on 12-10-2015.
 */
public class Config {

    public Config(){

        Gson gson = new Gson();
    }

    @GET
    @Path("/snake/src/config.json.dist")
    @Produces("application/json")
    public Response getJson(String json) {



        JsonConfig test = new Gson().fromJson(json, JsonConfig.class);




        return null;
    }


}

// Create inner class Json
class JsonConfig{

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
