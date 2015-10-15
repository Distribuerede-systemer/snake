package api;

import java.io.IOException;
import java.sql.*;
//import Config.Config;
//import logic.Tui;

/**
 * Created by Tobias on 15/10/15.
 */

public class Main {

    private static String sqlUrl = "jdbc:mysql://localhost:10098"; //+ Config.getHost() + ":" + Config.getPort();
    private static String sqlUser = "root";//Config.getUsername();
    private static String sqlPassword = "";//Config.getPassword();
    private static String dbName = "";//Config.getDbname();
    private static Connection connection = null;

    /**
     *Use a preparedstatment to run SQL on the database
     * @param sql
     */

    public static void doQuery(String sql) {

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This methode check connection if there is a connection to the database
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {

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
     *
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

    /**
     * Create a object of tui class.
     * This method starts the program
     */

    //new tui();
}

