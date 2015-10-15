package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import model.Game;
import model.Gamer;
import model.User;

/**
 * This class contains all methods that interact between the TUI / API and the data-layer in the Model package of the application.
 *
 * @author Henrik Thorn
 */
public class Logic {

    private static boolean isAuthenticated = false;


    /**
     * Get all users
     * @return ArrayList of users
     */
    public static ArrayList<User> getUsers() {

        // Define ArrayList to be used to add users and return them.
        ArrayList<User> uj = null;
        //TODO: Get all users from DB-wrapper

        return uj;


    }

    /**
     * Is user authenticated?
     * @return true if yes, false if no
     */
    public boolean isUserAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Create user
     * @param user
     * @return true if success, false if failure
     */
    public static boolean createUser(User user) {

        //TODO: Create user with DB-wrapper. If creation succeeded return true, if not return false

        return true;

    }

    /**
     * Delete user
     * @param id
     * @return true success, false if failure
     */
    public static boolean deleteUser(int id) {

        //TODO: Delete user via DB-wrapper
        return false;

    }

    /**
     * Add user
     * @param user
     */
    public static void addUser(User user) {

        //TODO: Add user to DB via DB-wrapper

    }

    /**
     * Get specific user
     * @param userId
     * @return User object
     */
    public User getUser(int userId) {

        //TODO: Get specific user from DB via DB-wrapper
        User user = new User();
        return user;

    }

    /**
     * Authenticates user
     *
     * @param username
     * @param password
     * @return 1 if auth successful, 0 if failed
     */
    public static int userLogin(String username, String password) {

        ArrayList<User> allUsers = getUsers();
        for (User user : allUsers){
            if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                isAuthenticated = true;
                return 1;
            }
        }

        return 0;

    }

    /**
     * Get all games
     * @return ArrayList of games
     */
    public static ArrayList<Game> getGames() {

        ArrayList<Game> games = null;
        ResultSet resultSet = null;
        try {

            resultSet = selectAllGames.executeQuery();
            getGames() = new ArrayList<Game>();
            
            while (resultSet.next()) {

                getGames().add(new Game (
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

            }

        return games;
    }

    }

    /**
     * Get specific game created by user
     * @param userId
     * @return ArrayList of matched games
     */
    public static ArrayList<Game> getGames(int userId) {

        //TODO: Get ALL games createdBy by specified userId, via DB-wrapper

        ArrayList<Game> games = null;
        return games;

    }

    /***
     * Get specific game
     * @param gameId
     * @return Game object
     */
    public static Game getGame(int gameId) {

        //TODO: Get specific game via DB-wrapper

        return null;
    }


    /**
     * Makes another user join an existing game
     * @param gameId
     * @param opponent
     * @param controls
     * @return true if success, false if failure
     */
    public static boolean joinGame(int gameId, User opponent, String controls){

        //TODO: Find game by id
        //TODO: Add opponent, with provided controls

        return true;

    }

    /**
     * Starts a game
     * @param gameId
     * @return returns game results
     */
    public static Map startGame(int gameId){

        //TODO: Get specific game from DB via DB-wrapper
        //TODO: Get host and opponent associated to game

        Gamer host = null;
        Gamer opponent = null;

        return GameEngine.playGame(10, host, opponent);

    }

    /**
     * Create a game
     * @param gameName
     * @param host
     * @return returns inriched game object
     */
    public static Game createGame(String gameName, User host) {

        //int gameId, int result, String controls, int newGame, int endGame, String host, String opponent, String status
        Game game = new Game();
        game.setName(gameName);
        game.setHost(host);
        game.setStatus(1); //1 is pending, 0 is done

        //TODO: Write game to db, and return game-id and set object before returning

        return game;
    }

    /**
     * Delete game
     * @param gameId
     * @return true if success, false if failure
     */
    public boolean deleteGame(int gameId) {

        //TODO: Delete specific game from DB via DB-wrapper;

        return false;
    }



}
