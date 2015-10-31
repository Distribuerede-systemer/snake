package controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import com.sun.xml.bind.v2.TODO;
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

        ArrayList<User> users = db.getUsers();

        return users;


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
    public static int deleteUser(int id) {

        return db.deleteUser(id);

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
        User user = db.getUser(userId);

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
    public static int [] userLogin(String username, String password) {
        User user;
        int [] result = new int [2];
        user = db.authenticatedUser(username);
        if (user == null) {
            // User does not exists.
            result [0] = 0;
        } else {
            if (password.equals(user.getPassword())) {
                // Return 2 if user exists and password is correct. Success.
                result [0] = 2;
                result [1] = user.getId();

            } else {
                //Return 1 if user exists but password is wrong.
                result [0] = 1;
            }
        }
        return result;
    }

    /**
     * Get all highscores from the game
     *
     * @return ArrayList of highscores
     */
    public static ArrayList<Score> getHighscore() {
        //TODO: Get all highscores
        //ArrayList<Score> highScores = db.getHighscore();

        return db.getHighscore();
    }


    /**
     * Get all games
     *
     * @return ArrayList of games
     */
    public static ArrayList<Score> getScoresByUserID(int id) {
        
        ArrayList<Score> scores = db.getScoresByUserID(id);
        return scores;
    }

    //Used for showing a user's games
    public static ArrayList<Game> getGamesByID(int id) {

        ArrayList<Game> games = db.getGames(db.GAMES_BY_ID, id);
        return games;
    }

    //Used for showing all pending games the user has as host or opponent
    public static ArrayList<Game> getPendingGamesByID(int id) {

        ArrayList<Game> games = db.getGames(db.PENDING_BY_ID, id);
        return games;
    }

    //Used for showing all pending games the user has been invited to play
    public static ArrayList<Game> getGamesInvitedByID(int id) {

        ArrayList<Game> games = db.getGames(db.PENDING_INVITED_BY_ID, id);
        return games;
    }

    //Used for showing all pending games hosted by the user
    public static ArrayList<Game> getGamesHostedByID(int id){
        ArrayList<Game> games = db.getGames(db.PENDING_HOSTED_BY_ID, id);
        return games;
    }

    //Used for showing the open games created by the user
    public static ArrayList<Game> getOpenGamesByID(int id) {

        ArrayList<Game> games = db.getGames(db.OPEN_BY_ID, id);
        return games;
    }

    //Shows all completed games for the user
    public static ArrayList<Game> getCompletedGamesByID(int id){
        ArrayList<Game> games = db.getGames(db.COMPLETED_BY_ID, id);
        return games;
    }

    //Used for showing all open games, when a user wants to join a game
    public static ArrayList<Game> getOpenGames() {

        //Setting id to 0 because this method doesn't return games by user ID
        ArrayList<Game> games = db.getGames(db.OPEN_GAMES, 0);
        return games;
    }



    /***
     * Get specific game
     *
     * @param gameId
     * @return Game object
     */
    public static Game getGame(int gameId) {

        return db.getGame(gameId);
    }


    /**
     * Makes another user join an existing game
     *
     * @param gameId
     * @param opponent
     * @param controls
     * @return true if success, false if failure
     */
    public static Game joinGame(int gameId, Gamer opponent, String controls) {

        game = db.getGame(gameId);
        game.setOpponent(opponent);
        game.setOpponentControls(controls);
        //TODO: Find game and Add opponent, with provided controls

        return game;

    }

    /**
     * Starts a game
     *
     * @param gameId
     * @return returns game results
     */
    public static Map startGame(int gameId) {

        Game game = getGame(gameId);

        //TODO:
        /*Gamer host = game.getHost();
        Gamer opponent = game.getOpponent();*/
        Gamer host = new Gamer();
        Gamer opponent = new Gamer();

        host.setId(game.getHost().getId());
        opponent.setId(game.getOpponent().getId());
        host.setControls(game.getHostControls());
        opponent.setControls(game.getOpponentControls());

        return GameEngine.playGame(game.getMapSize(), host, opponent);

    }

    /**
     * Create a game
     *
     * @return returns inriched game object
     */
    public static Game createGame(Game game) {

        if(game.getOpponent()!=null)
            game.setStatus("pending");
        else
            game.setStatus("open");

        db.createGame(game);

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
