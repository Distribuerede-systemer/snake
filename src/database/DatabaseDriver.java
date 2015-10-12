package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDriver {

    private static String sqlUrl = "jdbc:mysql://localhost:3306/mydb";
    private static String sqlUser = "root";
    private static String sqlPassword = "";

    private Connection connection = null;

    private PreparedStatement createUser = null;
    private PreparedStatement selectAllUsers = null;


    /**
     * Bruges til at oprette forbindelse til databasen og indeholder preparedStatements.
     */
    public DatabaseDriver()
    {
        try
        {
            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);

            createUser = connection.prepareStatement("INSERT INTO User(Firstname, Lastname) VALUES (?, ?)");

            //General prepared statements
            selectAllUsers = connection.prepareStatement("SELECT * FROM Users");



        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
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

    public PreparedStatement getCreateUser() {
        return createUser;
    }

    public static void main(String[] args) {


        Database database = new Database();

        database.createUser();

    }
}
