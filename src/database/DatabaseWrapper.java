package database;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.Game;
import model.Gamer;
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

    private Connection connection;
    DatabaseDriver dbDriver = new DatabaseDriver();

    //used for switch in getGames
    public static final int GAMES_BY_ID = 0;
    public static final int PENDING_BY_ID = 1;
    public static final int PENDING_INVITED_BY_ID = 2;
    public static final int PENDING_HOSTED_BY_ID = 3;
    public static final int OPEN_BY_ID = 4;
    public static final int COMPLETED_BY_ID = 5;
    public static final int OPEN_GAMES = 6;
    public static final int ALL_GAMES = 7;
    //used for switch in updateGame
    public static final int JOIN_GAME = 0;
    public static final int FINISH_GAME = 1;
    //used in switch for deleteStatements
    public static final int DELETE_USER = 0;
    public static final int DELETE_GAME = 1;

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
     *
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

                user = new User();

                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setCreated(resultSet.getDate("created"));
                user.setStatus(resultSet.getString("status"));
                user.setType(resultSet.getInt("type"));
                System.out.println(user.getType());
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
                Gamer host = new Gamer();
                host.setId(resultSet.getInt("host"));
                host.setControls(resultSet.getString("host_controls"));

                Gamer opponent = new Gamer();
                opponent.setId(resultSet.getInt("opponent"));
                opponent.setControls(resultSet.getString("opponent_controls"));

                Gamer winner = new Gamer();
                winner.setId(resultSet.getInt("winner"));

                game = new Game();
                game.setGameId(resultSet.getInt("id"));
                game.setWinner(winner);
                game.setCreated(resultSet.getDate("created"));
                game.setName(resultSet.getString("name"));
                game.setStatus(resultSet.getString("status"));
                game.setMapSize(resultSet.getInt("map_size"));

                // Adding Gamer objects (opponent and host) to our game object
                game.setHost(host);
                game.setOpponent(opponent);
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

        return game;
    }

//    public Score getScoresByUserID(int id) {
//        Score score = null;
//        ResultSet resultSet = null;
//        PreparedStatement ps;
//
//        try {
//            ps = connection.prepareStatement(dbDriver.getScoresByUserID());
//
//            ps.setInt(1, id);
//            resultSet = ps.executeQuery();
//
//            while (resultSet.next()) {
//
//                // Creating Gamer object (user) and setting user_id
//                Gamer user = new Gamer();
//                user.setId(resultSet.getInt("user_id"));
//
//                // Creating Gamer object (opponent) and setting user_id
//                Gamer opponent = new Gamer();
//                opponent.setId(resultSet.getInt("opponent_id"));
//
//                // Creating game object (game) and setting game_id
//                Game game = new Game();
//                game.setGameId(resultSet.getInt("game_id"));
//
//                // Creating score objects and adding user + game object
//                score = new Score();
//                score.setId(resultSet.getInt("id"));
//                score.setScore(resultSet.getInt("score"));
//
//                // Adding objects to our Score object
//                score.setUser(user);
//                score.setGame(game);
//                score.setOpponent(opponent);
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        finally {
//            try {
//                resultSet.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                dbDriver.close();
//            }
//        }
//        return score;
//    }

    /**
     * The following three methods (getUsers(), getGames() & getScores()) return an array of objects of the type User/Game/Score.
     * The returned array contains all entries from the relevant table in the database.
     *
     * @return
     */
    public ArrayList<User> getUsers() {
        ResultSet resultSet = null;
        PreparedStatement ps;
        User user = null;
        ArrayList<User> result = null;

        try {
            ps = connection.prepareStatement(dbDriver.getSqlRecords("users"));
            resultSet = ps.executeQuery();


            result = new ArrayList<>();

            // Indlaeser brugere i arrayListen
            while (resultSet.next()) {

                if (!resultSet.getString("status").equals("deleted") &&
                        resultSet.getType()!=0) {
                    user = new User();

                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setUsername(resultSet.getString("username"));
                    user.setCreated(resultSet.getDate("created"));
                    user.setStatus(resultSet.getString("status"));
                    user.setType(resultSet.getInt("type"));

                    result.add(user);
                }
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

    public ArrayList<Score> getScoresByUserID(int id) {
        ResultSet resultSet = null;
        PreparedStatement ps;
        ArrayList<Score> result = null;

        try {

            ps = connection.prepareStatement(dbDriver.getSQLAllFinishedGamesByUserID());
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            result = new ArrayList<>();

            // Indlaesser brugere i arrayListen

            while (resultSet.next()) {

                // Creating new Gamer object (host)
                Gamer user = new Gamer();
                user.setId(id);

                // Creating new Gamer object (opponent)
                Gamer opponent = new Gamer();
                opponent.setId(resultSet.getInt("opponent_id"));
                opponent.setUsername(resultSet.getString("opponent_name"));
                opponent.setFirstName(resultSet.getString("opponent_first_name"));
                opponent.setLastName(resultSet.getString("opponent_last_name"));

                // Creating new Gamer object (winner)
                Gamer winner = new Gamer();
                winner.setId(resultSet.getInt("winner"));

                // Initiating new Game object (game)
                Game game = new Game();
                game.setGameId(resultSet.getInt("id"));
                game.setName(resultSet.getString("name"));
                game.setWinner(winner);

                // Setting our host, opponent and winner objects into our game object
                Score score = new Score();
                score.setOpponent(opponent);
                score.setUser(user);
                score.setGame(game);
                score.setScore(resultSet.getInt("score"));

                result.add(score);
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

    public ArrayList<Score> getHighscore() {
        ResultSet resultSet = null;
        PreparedStatement ps;
        ArrayList<Score> result = null;

        try {
            ps = connection.prepareStatement(dbDriver.getHighScore());
            resultSet = ps.executeQuery();
            result = new ArrayList<>();

            while (resultSet.next()) {
                // Adding host object
                Gamer host = new Gamer();
                host.setUsername(resultSet.getString("username"));
                host.setFirstName(resultSet.getString("first_name"));
                host.setLastName(resultSet.getString("last_name"));
                host.setId(resultSet.getInt("user_id"));

                // Adding Opponent object
                Gamer opponent = new Gamer();
                opponent.setId(resultSet.getInt("opponent"));

                // Adding Game object
                Game game = new Game();
                game.setName(resultSet.getString("game_name"));
                game.setGameId(resultSet.getInt("game_id"));
                game.setCreated(resultSet.getDate("created"));

                Score score = new Score();
                score.setId(resultSet.getInt("score_id"));
                score.setUser(host);
                score.setGame(game);
                score.setOpponent(opponent);

                // Since Gson returns 0 for all unset int variables we are adding the opponent user_id.
                score.setScore(resultSet.getInt("highscore"));

                result.add(score);


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
     *
     * @param user
     */
    public void updateUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(dbDriver.updateSqlUser());

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getStatus());
            ps.setInt(6, user.getType());
            ps.setInt(7, user.getId());

            ps.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            dbDriver.close();
        }
    }


    public int updateGame(Game game, int type) {
        try {
            PreparedStatement ps;
            String status = getGame(game.getGameId()).getStatus();

            switch (type) {

                case JOIN_GAME:
                    if (status.equals("open")) {
                        ps = connection.prepareStatement(dbDriver.updateSqlGame(DatabaseDriver.JOIN));
                        ps.setString(1, "pending");
                        ps.setInt(2, game.getOpponent().getId());
                        ps.setInt(3, game.getGameId());
                        ps.executeUpdate();
                        return 1;
                    }
                    else
                        return 2;

                case FINISH_GAME:
                    if(!status.equals("deleted")){
                        ps = connection.prepareStatement(dbDriver.updateSqlGame(DatabaseDriver.FINISHED));
                        ps.setString(1, game.getStatus());
                        ps.setInt(2, game.getWinner().getId());
                        ps.setString(3, game.getOpponent().getControls());
                        ps.setInt(4, game.getGameId());
                        ps.executeUpdate();
                        return 1;
                    }
                    else
                        return 3;

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            dbDriver.close();
        }
        return 0;
    }

    public boolean createUser(User user) {

        try {
            // Prepared statement til at tilfoeje en bruger
            PreparedStatement createUser = connection.prepareStatement(dbDriver.createSqlUser());

            createUser.setString(1, user.getFirstName());
            createUser.setString(2, user.getLastName());
            createUser.setString(3, user.getEmail());
            createUser.setString(4, user.getUsername());
            createUser.setString(5, user.getPassword());
            createUser.setInt(6, user.getType());

            createUser.executeUpdate();
        } catch(MySQLIntegrityConstraintViolationException m){
            m.printStackTrace();
            return false;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            dbDriver.close();
            return false;

        }
        return true;

    }

    public boolean createGame(Game game) {

        try {
            // Prepared statement til at tilfoeje en bruger
            PreparedStatement createGame = connection.prepareStatement(dbDriver.createSqlGame());

            createGame.setInt(1, game.getHost().getId());
            if (game.getOpponent() != null) {
                createGame.setInt(2, game.getOpponent().getId());
                createGame.setString(3, "pending");
            } else {
                createGame.setInt(2, 0);
                createGame.setString(3, "open");
            }
            createGame.setString(4, game.getName());
            createGame.setString(5, game.getHost().getControls());
            createGame.setInt(6, game.getMapSize());

            createGame.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            dbDriver.close();
            return false;
        }
        return true;
    }

    public boolean createScore(Game game) {

        int id = game.getGameId();
        Gamer host = game.getHost();
        Gamer opponent = game.getOpponent();

        try {
            // Prepared statement til at tilfoeje en bruger
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

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            dbDriver.close();
            return false;
        }
        return true;
    }


    public int deleteEntry(int id, int type) {

        PreparedStatement ps = null;
        try {

            switch (type){
                case DELETE_USER:
                    ps = connection.prepareStatement(dbDriver.deleteSql("users"));
                    break;
                case DELETE_GAME:
                    ps = connection.prepareStatement(dbDriver.deleteSql("games"));
                    break;
            }

            ps.setString(1, "deleted");
            ps.setInt(2, id);

            return ps.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            dbDriver.close();
        }
        return 0;
    }

    public ArrayList<Game> getGames(int type, int id) {
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        ArrayList<Game> result = null;

        try {

            switch (type) {
                case GAMES_BY_ID:
                    ps = connection.prepareStatement(dbDriver.getSQLAllGamesByUserID());
                    ps.setInt(1, id);
                    ps.setInt(2, id);
                    break;
                case PENDING_BY_ID:
                    ps = connection.prepareStatement(dbDriver.getSQLGamesByStatusAndUserID());
                    ps.setString(1, "pending");
                    ps.setInt(2, id);
                    ps.setInt(3, id);
                    break;
                case PENDING_INVITED_BY_ID:
                    ps = connection.prepareStatement(dbDriver.getSQLGamesInvitedByUserID());
                    ps.setInt(1, id);
                    break;
                case PENDING_HOSTED_BY_ID:
                    ps = connection.prepareStatement(dbDriver.getSQLGamesHostedByUserID());
                    ps.setInt(1, id);
                    break;
                case OPEN_BY_ID:
                    ps = connection.prepareStatement(dbDriver.getSQLGamesByStatusAndUserID());
                    ps.setString(1, "open");
                    ps.setInt(2, id);
                    ps.setInt(3, id);
                    break;
                case COMPLETED_BY_ID:
                    ps = connection.prepareStatement(dbDriver.getSQLGamesByStatusAndUserID());
                    ps.setString(1, "finished");
                    ps.setInt(2, id);
                    ps.setInt(3, id);
                    break;
                case OPEN_GAMES:
                    ps = connection.prepareStatement(dbDriver.getSQLOpenGames());
                    break;
                case ALL_GAMES:
                    ps = connection.prepareStatement(dbDriver.getSqlRecords("games"));
                    break;

            }

            resultSet = ps.executeQuery();
            result = new ArrayList<>();

            // Running through our resultset and adding to arrayList
            while (resultSet.next()) {

                // Creating Gamer object (host)
                Gamer host = new Gamer();
                host.setId(resultSet.getInt("host"));

                // Creating Gamer object (opponent)
                Gamer opponent = new Gamer();
                opponent.setId(resultSet.getInt("opponent"));

                // Creating Gammer object (winner)
                Gamer winner = new Gamer();
                winner.setId(resultSet.getInt("winner"));

                // Creating Game object (game)
                Game game = new Game();
                game.setGameId(resultSet.getInt("id"));
                // Adding host, opponent, winner objects to our game object
                game.setWinner(winner);
                game.setHost(host);
                game.setOpponent(opponent);
                game.getHost().setControls(resultSet.getString("host_controls"));
                game.setCreated(resultSet.getDate("created"));
                game.setName(resultSet.getString("name"));

                game.setStatus(resultSet.getString("status"));
                game.setMapSize(resultSet.getInt("map_size"));

                result.add(game);
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

    public User getUserByUsername(String username) {
        User user = null;
        ResultSet resultSet = null;
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(dbDriver.authenticatedSql());
            ps.setString(1, username);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setType(resultSet.getInt("type"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public ArrayList<Gamer> getScore() {
        ResultSet resultSet = null;
        PreparedStatement ps;
        ArrayList<Gamer> result = null;


        try {
            ps = connection.prepareStatement(dbDriver.getSqlHighScore());
            resultSet = ps.executeQuery();

            result = new ArrayList<Gamer>();

            // Indlaeser brugere i arrayListen
            while (resultSet.next()) {
                Gamer gamer = new Gamer();
                gamer.setId(resultSet.getInt("id"));
                gamer.setFirstName(resultSet.getString("first_name"));
                gamer.setLastName(resultSet.getString("last_name"));
                gamer.setEmail(resultSet.getString("email"));
                gamer.setUsername(resultSet.getString("username"));
                gamer.setCreated(resultSet.getDate("created"));
                gamer.setStatus(resultSet.getString("status"));
                gamer.setTotalScore(resultSet.getInt("TotalScore"));
                result.add(gamer);
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
}