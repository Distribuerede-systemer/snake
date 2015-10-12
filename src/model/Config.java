package model;

import com.google.gson.Gson;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.concurrent.ExecutionException;

// Create inner class Json
public class Config{

    //Create variables for each of the JSON objects.
    private static String host;
    private static String port;
    private static String username;
    private static String password;
    private static String dbname;

    //TODO: Needs comment!
    public static void main(String [] args){

        try {
            Config.init();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() throws IOException {
	//Read config.json with all the settings
        BufferedReader br = new BufferedReader(new FileReader("src/config.json"));

        try {
            //Initialize Java class Stringbuilder as sb
            StringBuilder sb = new StringBuilder();

            // Instantiate String variable line
            //to read the number of lines in the document
            String line = br.readLine();

            // Use while-loop to continue while line does not equal 0.
            while (line != null) {

                //Reads content on current line in the document
                sb.append(line);

                //Changes the line after the last character
                sb.append(System.lineSeparator());

                //Instantiates the line variable with the next line in the document
                //and runs while-loop again.
                line = br.readLine();
            }

            //Instantiates String everything with the contents of the  StringBuilder object
            //and uses toString method to parse into a String.
            String everything = sb.toString();
            System.out.println(everything);

            Config config = new Gson().fromJson(everything, Config.class);

            Config.setDbname(config.getDbname());
            Config.setHost(config.getHost());
            Config.setPort(config.getPort());
            Config.setPassword(config.getPassword());
            Config.setUsername(config.getUsername());

            System.out.print(config.getDbname());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
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
