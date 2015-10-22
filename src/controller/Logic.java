package controller;

import java.util.ArrayList;
import java.util.Map;
import database.DatabaseWrapper;
import model.Game;
import model.Gamer;
import model.Score;
import model.User;


/**
 * This class contains all methods that interact between the TUI / API and the data-layer in the Model package of the application.
 *
 * @author Henrik Thorn
 */
public class Logic {
    static DatabaseWrapper db = new DatabaseWrapper();
    static Game game = new Game();
    private static boolean isAuthenticated = false;


    /**
     * Get all users
     *
     * @return ArrayList of users
     */
    public static ArrayList<User> getUsers() {

        // Define ArrayList to be used to add users and return them.
        DatabaseWrapper db = new DatabaseWrapper();

        ArrayList<User> Users = db.getUsers();

        return Users;


    }

    /**
     * Is user authenticated?
     *
     * @return true if yes, false if no
     */
    public static boolean isUserAuthenticated() {
        return isAuthenticated;
    }

    public static void setIsUserAuthenticated(boolean auth) {
        isAuthenticated = auth;
    }

    /**
     * Create user
     *
     * @param user
     * @return true if success, false if failure
     */
    public static boolean createUser(User user) {


        if (db.createUser(user))

            return true;

        else {
            return false;
        }}

    /**
     * Delete user
     *
     * @param id
     * @return true success, false if failure
     */
    public static boolean deleteUser(int id) {

        //TODO: Delete user via DB-wrapper
        return false;

    }

    /**
     * Add user
     *
     * @param user
     */
    public static void addUser(User user) {

        //TODO: Add user to DB via DB-wrapper

    }

    /**
     * Get specific user
     *
     * @param userId
     * @return User object
     */
    public static User getUser(int userId) {

        //TODO: Get specific user from DB via DB-wrapper
        User user = new User();
        return user;

    }

    /**
     * Authenticates user
     * The int uses 2 parameters: username and password which it authenticates as the correct credentials of an existing user.
     *
     * @param username
     * @param password
     * @return 2 if auth successful, 1 if user exists but password is incorrect, 0 if failed
     */
    public static int userLogin(String username, String password) {
        User user;
        user = db.authenticatedUser(username);
        if (user == null) {
            // User does not exists.
            return 0;
        } else {
            if (password.equals(user.getPassword())) {
                // Return 2 if user exists and password is correct. Success.
                return 2;

            } else {
                //Return 1 if user exists but password is wrong.
                return 1;
            }
        }
    }

    /**
     * Get all highscores from the game
     *
     * @return ArrayList of highscores
     */
    public static ArrayList<Score> getHighscores() {
        //TODO: Get all highscores
        ArrayList<Score> highScores = null;
        return highScores;
    }

    /**
     * Get a highscore from a specified user
     *
     * @param userId
     * @return Score
     */
    public static Score getHighscore(int userId) {
        //TODO: Get highscore from user

        Score score = db.getScore(userId);
        return score;
    }


    /**
     * Get all games
     *
     * @return ArrayList of games
     */
    public static ArrayList<Game> getGames() {

        //TODO: Get ALL games via DB-wrapper

        ArrayList<Game> games = null;
        return games;

    }

    /**
     * Get specific game created by user
     *
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
     *
     * @param gameId
     * @return Game object
     */
    public static Game getGame(int gameId) {

        //TODO: Get specific game via DB-wrapper

        return null;
    }


    /**
     * Makes another user join an existing game
     *
     * @param gameId
     * @param opponent
     * @param controls
     * @return true if success, false if failure
     */
    public static boolean joinGame(int gameId, User opponent, String controls) {

        //TODO: Find game by id
        //TODO: Add opponent, with provided controls

        return true;

    }

    /**
     * Starts a game
     *
     * @param gameId
     * @return returns game results
     */
    public static Map startGame(int gameId) {

        Game game = getGame(gameId);

        Gamer host = new Gamer();
        Gamer opponent = new Gamer();

        host.setControls(game.getHostControls());
        opponent.setControls(game.getOpponentControls());

        return GameEngine.playGame(game.getMapSize(), host, opponent);

    }

    /**
     * Create a game
     *
     * @param gameName
     * @param host
     * @return returns inriched game object
     */
    public static Game createGame(String gameName, User host) {

        //int gameId, int result, String controls, int newGame, int endGame, String host, String opponent, String status

        game.setName(gameName);
        game.setHost(host);
        game.setStatus("pending");

        //TODO: Write game to db, and return game-id and set object before returning

        return game;
    }

    //endgame() Called when game is over and pushes score data to the database for future use.
    public static Game endGame (int gameId, Gamer host, Gamer opponent) {

        game.setStatus("Finished");
        db.createScore(gameId, host, opponent);
        return game;
    }
    /**
     * Delete game
     *
     * @param gameId
     * @return true if success, false if failure
     */
    public static boolean deleteGame(int gameId) {
        DatabaseWrapper db = new DatabaseWrapper();
        if (db.deleteGame(gameId))
            return true;
        else {
            return false;
        }

    }


}
