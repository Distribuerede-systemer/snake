package database;

import model.Game;
import model.Gamer;
import model.Score;
import model.User;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.ArrayList;

/**
 * This class contains methods which use prepared statements from the DatabaseDriver class to retrieve data from the database.
 * The methods convert the data and return an object.
 */
//

// TODO: dynamicQuery (returning cachedrowset)
// TODO: getHighScore (select users.*, sum(scores.score) as HighScore from users join scores where users.id = scores.user_id group by users.username order by HighScore desc)
// TODO: ???
// TODO: USER objektet giver fejl
// TODO: Create game i logik
// TODO: Rette dbWrapper game-metoder saa de passer til det nye gamer objekt
// TODO: Metode i logik som skal slette pending tid efter x antal dage


public class DatabaseWrapper {

    private ResultSet resultSet = null;
    private Connection connection;
    DatabaseDriver dbDriver = new DatabaseDriver();
    public static final int ALLGAMESBYID = 0;
    public static final int ALLPENDINGGAMESBYID = 1;
    public static final int PENDINGINVITESBYID = 2;
    public static final int COMPLETEDGAMESBYID = 3;


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
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
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
                        getUser(resultSet.getInt("winner")),
                        resultSet.getString("host_controls"),
                        resultSet.getDate("created"),
                        resultSet.getString("name"),
                        getUser(resultSet.getInt("host")),
                        getUser(resultSet.getInt("opponent")),
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
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
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
                        getUser(resultSet.getInt("winner")),
                        resultSet.getString("host_controls"),
                        resultSet.getDate("created"),
                        resultSet.getString("name"),
                        getUser(resultSet.getInt("host")),
                        getUser(resultSet.getInt("opponent")),
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
            PreparedStatement ps = connection.prepareStatement(dbDriver.updateSqlGame());

            ps.setString(1, game.getName());
            ps.setString(2, game.getStatus());
            ps.setInt(3, game.getWinner().getId());
            ps.setString(4, game.getHostControls());
            ps.setString(5, game.getOpponentControls());
            ps.setInt(6, game.getId());

            ps.executeUpdate();
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
        }
    }

    public void createUser(User user){

//        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime()
//                .getTime());

        try
        {
            // Prepared statement til at tilfoeje en bruger
            PreparedStatement createUser = connection.prepareStatement(dbDriver.createSqlUser());

            createUser.setString(1, user.getFirstName());
            createUser.setString(2, user.getLastName());
            createUser.setString(3, user.getEmail());
            createUser.setString(4, user.getUserName());
            createUser.setString(5, user.getPassword());
            createUser.setString(6, user.getStatus());
            createUser.setString(7, user.getType());

            createUser.executeUpdate();
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
        }
    }

    public int createGame(Game game){

        int id = 0;
        try
        {
            // Prepared statement til at tilfoeje en bruger
            PreparedStatement createGame = connection.prepareStatement(dbDriver.createSqlGame(),Statement.RETURN_GENERATED_KEYS );

            createGame.setInt(1, 2);
//            createGame.setInt(1, game.getHost().getId());
            createGame.setInt(2, 3);
//            createGame.setInt(2, game.getOpponent.getId());
            createGame.setString(3, game.getName());
            createGame.setString(4, game.getStatus());
            createGame.setString(5, game.getHostControls());

            createGame.executeUpdate();

            ResultSet rs = createGame.getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);
            }



        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
        }
        return id;
    }

    public void createScore(int id, Gamer host, Gamer opponent){

        try
        {
            // Prepared statement til at tilfoeje en brugera
            PreparedStatement createScore = connection.prepareStatement(dbDriver.createSqlScore());

            createScore.setInt(1, host.getId());
            createScore.setInt(2, id);
            createScore.setInt(3, host.getScore());
            createScore.setInt(4, opponent.getId());

            createScore.executeUpdate();

            createScore.setInt(1, opponent.getId());
            createScore.setInt(2, id);
            createScore.setInt(3, opponent.getScore());
            createScore.setInt(4, host.getId());

            createScore.executeUpdate();

        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
        }
    }


    public void deleteUser(int id)
    {
        try
        {
            PreparedStatement ps = connection.prepareStatement(dbDriver.deleteSqlUser());

            ps.setString(1, "deleted");
            ps.setInt(2, id);


            ps.executeUpdate();
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
        }
    }


    public void deleteGame(int id)
    {
        try
        {
            PreparedStatement ps = connection.prepareStatement(dbDriver.deleteSqlGame());

            ps.setString(1, "deleted");
            ps.setInt(2, id);


            ps.executeUpdate();
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
        }
    }

    public ArrayList<Game> getGamesByUserID(int type, int id) {
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        ArrayList<Game> result = null;

        try {

            switch (type){
                case ALLGAMESBYID:
                    ps = connection.prepareStatement(dbDriver.getSqlAllGamesByUserID());
                    ps.setInt(1, id);
                    ps.setInt(2, id);
                    break;
                case ALLPENDINGGAMESBYID:
                    ps = connection.prepareStatement(dbDriver.getSqlPendingGamesByUserID());
                    ps.setInt(1, id);
                    ps.setInt(2, id);
                    break;
                case PENDINGINVITESBYID:
                    ps = connection.prepareStatement(dbDriver.getSqlCompletedGamesByUserID());
                    ps.setInt(1, id);
                    ps.setInt(2, id);
                    break;
                case COMPLETEDGAMESBYID:
                    ps = connection.prepareStatement(dbDriver.getSqlGameInvitesByUserID());
                    ps.setInt(1, id);
                    break;
            }

            resultSet = ps.executeQuery();

            result = new ArrayList<Game>();

            // Indlaesser brugere i arrayListen
            while (resultSet.next())
            {
                result.add(new Game(
                        resultSet.getInt("id"),
                        getUser(resultSet.getInt("winner")),
                        resultSet.getString("host_controls"),
                        resultSet.getDate("created"),
                        resultSet.getString("name"),
                        getUser(resultSet.getInt("host")),
                        getUser(resultSet.getInt("opponent")),
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






    public User authenticatedUser(String username) {
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