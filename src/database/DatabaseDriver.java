package database;

import com.sun.rowset.CachedRowSetImpl;

import javax.xml.transform.Result;
import java.sql.*;

public class DatabaseDriver {

    private static String sqlUrl = "jdbc:mysql://localhost:3306/dbcon";
    private static String sqlUser = "dataguy";
    private static String sqlPassword = "QjcUayHA3axeGLns";

    private Connection connection = null;

    /**
     * Bruges til at oprette forbindelse til databasen og indeholder preparedStatements.
     */
//
    public DatabaseDriver()
    {
        try
        {
            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Bruges til at lukke forbindelsen til databasen.
     */

    public void close()
    {
        try{
            connection.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    //    public String create() {
//
//    }

    public String getSqlRecord(String table) {

        return "select * from " + table + " WHERE id = ?";
    }

    public String getSqlRecords(String table) {

        return "select * from " + table;
    }
}
