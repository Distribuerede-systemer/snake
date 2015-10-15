package api;

import java.io.IOException;
import java.sql.*;

import Config.Config;
import logic.Tui;


/**
 * Created by Tobias on 15/10/15.
 */

public class Main {

    private static String sqlUrl = "jdbc:mysql://" + Config.getHost() + ":" + Config.getPort();
    private static String sqlUser = Config.getUsername();
    private static String sqlPassword = Config.getPassword();
    private static String dbName = Config.getDbname();

    private static Connection connection = null;
    private static PreparedStatement sqlStatement;


    public static void doQuery(String sql) {

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        //Check connection if there is a connection to the database
        try {

            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);

            if (connection.isValid(1000)) {
                System.out.println("You are connected");
            } else {

                System.out.println("Connection lost");
            }

        } catch (SQLException e) {

            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Checks if the databse exists or not
     * @return bool
     * @throws SQLException
     */

    protected static boolean DbExist() throws SQLException {

        ResultSet resultSet = connection.getMetaData().getCatalogs();

        while (resultSet.next()) {

            String databaseName = resultSet.getString(1);

            if (databaseName.equals(dbName)) {

                return true;

            } else {

                doQuery("Indsæt SQL dump");

            }
        }

        return false;
    }

    //Create a object of Start the program
    new tui();
}






/*
//tere

        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/helloworld");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

*/




/*    Tui tui = new Tui();
    tui.start();*/


//TEST  (  .  Y   .  )

