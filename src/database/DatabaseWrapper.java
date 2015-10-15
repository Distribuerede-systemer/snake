package database;

import com.sun.rowset.CachedRowSetImpl;
import model.Game;
import model.Score;
import model.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class contains methods which use prepared statements from the DatabaseDriver class to retrieve data from the database.
 * The methods convert the data and return an object.
 */
//
public class DatabaseWrapper {


    private ResultSet resultSet = null;
    private Connection connection;
    DatabaseDriver dbDriver = new DatabaseDriver();

    /**
     * The connection from DatabaseDriver is initialized in the class
     */
    public DatabaseWrapper() {

        connection = dbDriver.getConnection();
    }

    /**
     * The following three methods(getUser(), getGame() & getScore()) return a specific user/score/game as an object of the type User/Score/Game.
     * Which entry from the database is returned is defined by the parameter id.
     * * @param id
     * @return
     */
    public User getUser(int id) {
        User user = null;
        ResultSet resultSet = null;
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(dbDriver.getSqlRecord("users"));
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getDate("created"),
                        resultSet.getString("status"),
                        resultSet.getString("type")
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

    public Game getGame(int id) {
        Game game = null;
        ResultSet resultSet = null;
        PreparedStatement ps;


        try {
            ps = connection.prepareStatement(dbDriver.getSqlRecord("games"));

        ps.setInt(1, id);
            resultSet = ps.executeQuery();


            while (resultSet.next()) {
                game  = new Game(
                        resultSet.getInt("id"),
                        resultSet.getInt("result"),
                        resultSet.getString("hostControls"),
                        resultSet.getDate("created"),
                        resultSet.getString("game_name"),
                        resultSet.getInt("newgame"),
                        resultSet.getInt("endgame"),
                        resultSet.getString("host"),
                        resultSet.getString("opponent"),
                        resultSet.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                dbDriver.close();
            }
        }

        return game;
    }


    public Score getScore(int id) {
        Score score = null;
        ResultSet resultSet = null;
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(dbDriver.getSqlRecord("scores"));

            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                score = new Score(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("game_id"),
                        resultSet.getInt("host_id"),
                        resultSet.getInt("score")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                dbDriver.close();
            }
        }
        return score;
    }

    /**
     * The following three methods (getUsers(), getGames() & getScores()) return an array of objects of the type User/Game/Score.
     * The returned array contains all entries from the relevant table in the database.
     * @return
     */
    public ArrayList<User> getUsers() {
        ResultSet resultSet = null;
        PreparedStatement ps;
        ArrayList<User> result = null;

        try {
            ps = connection.prepareStatement(dbDriver.getSqlRecords("users"));
            resultSet = ps.executeQuery();


            result = new ArrayList<User>();

            // Indlaesser brugere i arrayListen
            while (resultSet.next())
            {
                result.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getDate("created"),
                        resultSet.getString("status"),
                        resultSet.getString("type")
                ));
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

        return result;
    }

    public ArrayList<Game> getGames() {
        ResultSet resultSet = null;
        PreparedStatement ps;
        ArrayList<Game> result = null;

        try {
            ps = connection.prepareStatement(dbDriver.getSqlRecords("games"));
            resultSet = ps.executeQuery();


            result = new ArrayList<Game>();

            // Indlaesser brugere i arrayListen
            while (resultSet.next())
            {
                result.add(new Game(
                        resultSet.getInt("id"),
                        resultSet.getInt("result"),
                        resultSet.getString("hostControls"),
                        resultSet.getDate("created"),
                        resultSet.getString("game_name"),
                        resultSet.getInt("newgame"),
                        resultSet.getInt("endgame"),
                        resultSet.getString("host"),
                        resultSet.getString("opponent"),
                        resultSet.getString("status")
                ));
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

        return result;
    }

    public ArrayList<Score> getScores() {
        ResultSet resultSet = null;
        PreparedStatement ps;
        ArrayList<Score> result = null;

        try {
            ps = connection.prepareStatement(dbDriver.getSqlRecords("scores"));
            resultSet = ps.executeQuery();
            result = new ArrayList<Score>();

            while (resultSet.next())
            {
                result.add(new Score(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("game_id"),
                        resultSet.getInt("host_id"),
                        resultSet.getInt("score")
                ));
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

        return result;
    }

    /**
     * The following two methods update the values of a given record in the User/Game table of the database. All entries of the record will be updated with the latest value which is given as a method parameter.
     * The record to be updated is identified by the method parameter: id.
     * @param user
     */
    public void updateUser(User user)
    {
        try
        {
            PreparedStatement ps = connection.prepareStatement(dbDriver.updateSqlUser());

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getStatus());
            ps.setString(6, user.getType());
            ps.setInt(7, user.getId());


            ps.executeUpdate();
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
        }
    }

    public void updateGame(Game game)
    {
        try
        {
            PreparedStatement ps = connection.prepareStatement(dbDriver.updateSqlUser());

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getStatus());
            ps.setString(6, user.getType());
            ps.setInt(7, user.getId());


            ps.executeUpdate();
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
        }
    }

}
