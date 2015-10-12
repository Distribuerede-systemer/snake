package model;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Oscar on 12-10-2015.
 */

// Create inner class Json
public class Config{


    public static void main(String [] args){

        try {

            Config.init();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Config(){
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("src/config.json.dist"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }


            String everything = sb.toString();
            System.out.println(everything);



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
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

