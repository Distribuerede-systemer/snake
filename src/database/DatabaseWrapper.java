package database;

import com.sun.rowset.CachedRowSetImpl;
import model.User;

import java.sql.*;

public class DatabaseWrapper {


    private ResultSet resultSet = null;
    private Connection connection;
    DatabaseDriver dbDriver = new DatabaseDriver();


    public DatabaseWrapper() {

        connection = dbDriver.getConnection();
    }


    public User getUser(int id) {
        User user = null;
        ResultSet resultSet = null;
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(dbDriver.getSqlUser());
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setUserName(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setCreated(resultSet.getDate(6));
                user.setStatus(resultSet.getString(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                dbDriver.close();
            }
        }
        return user;
    }



}
