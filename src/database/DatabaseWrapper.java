package database;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DatabaseWrapper {

    private static String sqlUrl = "jdbc:mysql://localhost:3306/testdb";
    private static String sqlUser = "root";
    private static String sqlPassword = "root";

    private Connection connection = null;

    private ResultSet resultSet = null;

    DatabaseDriver dbDriver = new DatabaseDriver();

    /**
     * Bruges til at oprette forbindelse til databasen og indeholder preparedStatements.
     */
    public DatabaseWrapper()
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


    public CachedRowSetImpl readDatabase(String table)
    {
        ResultSet resultSet = null;
        CachedRowSetImpl cachedResult = null;
        PreparedStatement ps;

        try{
            ps = connection.prepareStatement(dbDriver.read(table));
            cachedResult = new CachedRowSetImpl();
            resultSet = ps.executeQuery();
//            while(resultSet.next()){
//
//            }

            cachedResult.populate(resultSet);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                resultSet.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                close();
            }
        }
        return cachedResult;
    }

    
}
