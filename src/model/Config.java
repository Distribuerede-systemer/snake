package model;

import com.google.gson.Gson;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ExecutionException;

/**
 * Created by Oscar on 12-10-2015.
 */

// Create inner class Json
public class Config{


    public static void main(String [] args){

        Config.init();
    }


    public Config(){
    }

    public static void init() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(
                    "C:\\Users\\Oscar\\Documents\\GitHub\\snake\\src\\config.json.dist"));

           // File file = new File("sample.txt");
            //System.out.println(file.getAbsolutePath());


            Config con = new Gson().fromJson(br, Config.class);

            System.out.print(con.getDbname());
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

