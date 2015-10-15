package database;

import com.sun.rowset.CachedRowSetImpl;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * @author Team Depardieu
 *This class connects to the database - It also includes different prepared statements
 */
public class DatabaseDriver {
    /**
     * Specifies the connection to the server - Url, User and password needs to be adjusted to the individual database.
     */
    private static String sqlUrl = "jdbc:mysql://localhost:3306/dbcon";
    private static String sqlUser = "root";
    private static String sqlPassword = "root";

    private Connection connection = null;


    /**
     * Connects to the database with the specified Url, User and Password.
     */
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
     * Method used to close to DB connection
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

    /**
     * Querybuilder with two parameters, which, when specified will get a single record from a specific table.
     * @param table
     * @return SqlStatement
     */

    public String getSqlRecord(String table) {

        return "select * from " + table + " WHERE id = ?";
    }

    /**
     * Querybuilder with a single parameter, which, when specified will get a table.
     * @param table
     * @return SqlStatement
     */

    public String getSqlRecords(String table) {

        return "select * from " + table;
    }

    /**
     * Querybuilder with seven parameters, which, when specified will update the value of the shown columns in the 'users' table
     * @return SqlStatement
     */
    public String updateSqlUser(){
        return "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, password = ?, " +
                "status = ?, type = ? WHERE id = ?";
    }
 //
    /**
     * Querybuilder with seven parameters, which, when specified will update the value of the shown columns in the 'games' table
     * @return SqlStatement
     */
    public String updateSqlGame(){
        return "UPDATE Games SET game_name = ?, status = ?, result = ?, hostcontrols = ?, " +
                "endgame = ?, opponentcontrols = ? WHERE id = ?";
    }

    public String authenticatedSql() {
        return "Select * from users where username = ?";
    }

    public String deleteGame() {
        return"UPDATE games SET status = Deleted WHERE id = ?";
    }
}
