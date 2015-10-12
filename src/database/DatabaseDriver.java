package database;

import com.sun.rowset.CachedRowSetImpl;

import javax.xml.transform.Result;
import java.sql.*;

public class DatabaseDriver {

    private static String sqlUrl = "jdbc:mysql://localhost:3306/testdb";
    private static String sqlUser = "root";
    private static String sqlPassword = "root";

    private Connection connection = null;

    private PreparedStatement createUser = null;
    private ResultSet resultSet = null;

    /**
     * Bruges til at oprette forbindelse til databasen og indeholder preparedStatements.
     */
    public DatabaseDriver()
    {
        try
        {
            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);

            createUser = connection.prepareStatement("INSERT INTO Users(Firstname, Lastname) VALUES (?, ?)");




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

    public CachedRowSetImpl getRecord(String table){

        CachedRowSetImpl cachedResultSet = null;
        try {
            PreparedStatement getRecord = connection.prepareStatement("Select * from " + table);

            resultSet = getRecord.executeQuery();
            cachedResultSet = (CachedRowSetImpl) resultSet;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return cachedResultSet;
    }







//    public User getUser(String userid)
//    {
//        User user = null;
//        ResultSet resultSet = null;
//
//        try{
//            db.getSelectUserById().setString(1, userid);
//            resultSet = db.getSelectUserById().executeQuery();
//
//            while(resultSet.next())
//            {
//                user = new User(
//                        resultSet.getString("Name"),
//                        resultSet.getString("UserID"),
//                        resultSet.getString("Password"),
//                        resultSet.getDouble("Balance"));
//            }
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//        finally{
//            try{
//                resultSet.close();
//            }
//            catch(SQLException ex)
//            {
//                ex.printStackTrace();
//                db.close();
//            }
//        }
//        return user;
//    }


}
