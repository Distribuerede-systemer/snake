package database;

import model.User;

import java.sql.*;
//
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

//            while (resultSet.next())
//            {
////                user = new User(resultSet.getString("userName"), resultSet.getString("Password"),
////                        resultSet.getBoolean("AdminRights"), resultSet.getDouble("Balance"));
//            }


            while (resultSet.next()) {
                user = new User(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("username")
                );
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
