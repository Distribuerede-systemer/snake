package api;

import java.io.IOException;
import java.sql.*;
//import config.config;
//import tui.Logik;
//import database.DatabaseDriver;


/**
 * Created by Tobias on 15/10/15.
 */

public class Main {

    /**
     * Create an object of tui class.
     * This method starts the program
     */

    public static void main(String[] args) {

        new DatabaseDriver.dbExist();

        new DatabaseDriver.checkConnection();

        new Logik.Start();

    }
}



/*

    private static String sqlUrl = "jdbc:mysql://localhost:3306//" + Config.getHost() + ":" + Config.getPort();
    private static String sqlUser = Config.getUsername();
    private static String sqlPassword = Config.getPassword();
    private static String dbName = Config.getDbname();
    private static Connection connection = null;

    */
/**
     * This method checks if there is a connection to the database
     *//*


    public static void checkConnection() {

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
}


        */
/**
         * Checks if the database exists or not
         * @return bool
         * @throws SQLException
         *//*


    public static boolean DbExist() throws SQLException {

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

    */
/**
     * Use a preparedstatment to run SQL on the database
     *
     * @param sql
     *//*


    public static void doQuery(String sql) {

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/



