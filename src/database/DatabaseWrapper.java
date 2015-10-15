package database;

import com.sun.rowset.CachedRowSetImpl;
import model.Game;
import model.Score;
import model.User;

import java.sql.*;
import java.util.ArrayList;

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
                game = new Game(
                        resultSet.getInt("id"),
                        resultSet.getInt("result"),
                        resultSet.getString("hostControls"),
                        resultSet.getDate("created"),
                        resultSet.getString("game_name"),
                        resultSet.getInt("newgame"),
                        resultSet.getInt("endgame"),
                        resultSet.getString("host"),
                        resultSet.getString("opponent"),
                        resultSet.getInt("status")
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
                        resultSet.getInt("status")
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

            // Indlaesser brugere i arrayListen
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

            User user = new User();
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

    public User authenticatedUser (String username) {
        User user = null;
        ResultSet resultset = null;
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(dbDriver.authenticatedSql());
            ps.setString(1, username);
            resultset = ps.executeQuery();

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
        }
    return user;
    }

}
