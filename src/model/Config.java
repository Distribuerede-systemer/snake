package model;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Create inner class Json
public class Config{

    //Create variables for each of the JSON objects.
    private static String host;
    private static String port;
    private static String username;
    private static String password;
    private static String dbname;

    // Create init-method to read from the config.json.dist file
    // and parse it to the variables in the class.
    public static void init() throws IOException {
	//Read config.json with all the settings
        BufferedReader br = new BufferedReader(new FileReader("src/config.json"));

        JSONParser jsonParser = new JSONParser();

        try {
            FileReader json = new FileReader("src/config.json");

            Object obj = jsonParser.parse(json);

            JSONObject jsonObject = (JSONObject) obj;

            setHost((String) jsonObject.get("host"));
            setPort((String) jsonObject.get("port"));
            setUsername((String) jsonObject.get("username"));
            setDbname((String) jsonObject.get("dbname"));
            setPassword((String) jsonObject.get("password"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

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

    public static void setDbname(String dbname) {
        Config.dbname = dbname;
    }

    public static void setHost(String host) {
        Config.host = host;
    }

    public static void setPassword(String password) {
        Config.password = password;
    }

    public static void setPort(String port) {
        Config.port = port;
    }

    public static void setUsername(String username) {
        Config.username = username;
    }
}
