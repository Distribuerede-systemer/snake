package controller;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * Get all users
     *
     * @return ArrayList of users
     */
    public static ArrayList<User> getUsers() {

        // Define ArrayList to be used to add users and return them.
        return db.getUsers();
    }

    /**
     * Create user
     *
     * @param user
     * @return true if success, false if failure
     */
    public static boolean createUser(User user) {
        user.setPassword(Security.hashing(user.getPassword()));

        if (db.createUser(user))
            return true;
        else {
            return false;
        }
    }

    /**
     * Get specific user
     *
     * @param userId
     * @return User object
     */
    public static User getUser(int userId) {
        return db.getUser(userId);
    }

    /**
     * Delete user
     *
     * @param userId
     * @return rows affected en database
     */
    public static int deleteUser(int userId) {
        return db.deleteEntry(userId, DatabaseWrapper.DELETE_USER);
    }

    /**
     * Authenticates user
     * The method use 2 parameters: username and password which it authenticates as the correct credentials of an existing user.
     * Key 1: User type (0 = admin), (1 = user)
     * Key 2: Error/Succes code (0 = user doesnt exists), (1 = password is wrong), (2 = successful login)
     * Key 3: Contain the authenticated users id
     *
     * @param username
     * @param password
     * @return hashMap with user type, error/succes code, userid
     */
    public static HashMap authenticateUser(String username, String password) {
        User user;
        HashMap <String, Integer> hashMap = new HashMap();
        user = db.getUserByUsername(username);
        if (user == null) {
            // User does not exists.
            hashMap.put("code", 0);
        } else {
            hashMap.put("usertype", user.getType());
            if (password.equals(user.getPassword())) {
                // Return 2 if user exists and password is correct. Success.
                hashMap.put("code", 2);
                hashMap.put("userid", user.getId());

            } else {
                //Return 1 if user exists but password is wrong.
                hashMap.put("code", 1);
            }
        }
        return hashMap;
    }


    /**
     * Get specific game
     *
     * @param gameId
     * @return Game object
     */
    public static Game getGame(int gameId) {
        return db.getGame(gameId);
    }

    /**
     * Getting a speific list of games by type and userId
     *
     * @param type
     * @param userId
     * @return ArrayList<Game>
     */
    public static ArrayList<Game> getGames(int type, int userId) {
        ArrayList<Game> games = null;

        switch (type) {
            case DatabaseWrapper.GAMES_BY_ID:
                //Used for showing a user's games
                games = db.getGames(DatabaseWrapper.GAMES_BY_ID, userId);
                break;
            case DatabaseWrapper.PENDING_BY_ID:
                //Used for showing all pending games the user has as host or opponent
                games = db.getGames(DatabaseWrapper.PENDING_BY_ID, userId);
                break;
            case DatabaseWrapper.PENDING_INVITED_BY_ID:
                //Used for showing all pending games the user has been invited to play
                games = db.getGames(DatabaseWrapper.PENDING_INVITED_BY_ID, userId);
                break;
            case DatabaseWrapper.PENDING_HOSTED_BY_ID:
                //Used for showing the open games created by the user
                games = db.getGames(DatabaseWrapper.PENDING_HOSTED_BY_ID, userId);
                break;
            case DatabaseWrapper.OPEN_BY_ID:
                //Used for showing the open games created by the user
                games = db.getGames(DatabaseWrapper.OPEN_BY_ID, userId);
                break;
            case DatabaseWrapper.COMPLETED_BY_ID:
                //Shows all completed games for the user
                games = db.getGames(DatabaseWrapper.COMPLETED_BY_ID, userId);
                break;
            case DatabaseWrapper.OPEN_GAMES:
                //Used for showing all open games, when a user wants to join a game
                //Is getting set to 0 in API class because this method doesn't return games by user ID
                games = db.getGames(DatabaseWrapper.OPEN_GAMES, userId);
                break;
            case DatabaseWrapper.ALL_GAMES:
                //Used for showing all open games, when a user wants to join a game
                //Is getting set to 0 in TUI class because this method doesn't return games by user ID
                games = db.getGames(DatabaseWrapper.ALL_GAMES, userId);
                break;
        }
        return games;
    }

    /**
     * Create a game
     *
     * @return returns inriched game object
     */
    public static Game createGame(Game game) {

        if (game.getHost() != null) {
            if (db.createGame(game)) {
                if (game.getOpponent() != null) {
                    game.setStatus("pending");
                } else {
                    game.setStatus("open");
                }
                return game;
            }
        }
        return null;
    }

    public static boolean joinGame(Game game) {

        if (db.updateGame(game, DatabaseWrapper.JOIN_GAME) == 1)
            return true;
        else
            return false;
    }


    /**
     * Starting the game
     *
     * @param requestGame
     * @return Object Game
     */
    public static Game startGame(Game requestGame) {

        //gameid, opponentcontrolls
        Game game = db.getGame(requestGame.getGameId());

        if(game.getOpponent() == null)
        {
            game.setOpponent(requestGame.getOpponent());
        }
        else{
            game.getOpponent().setControls(requestGame.getOpponent().getControls());
        }

        Map gamers = GameEngine.playGame(game);

        game = endGame(gamers, game);

        return game;
    }


    //endgame() Called when game is over and pushes score data to the database for future use.
    public static Game endGame(Map gamers, Game game) {

        if (((Gamer) gamers.get('h')).isWinner()) {
            game.setWinner(game.getHost());
        } else if (((Gamer) gamers.get('o')).isWinner()) {
            game.setWinner(game.getOpponent());
        }

        game.setStatus("Finished");
        db.updateGame(game, DatabaseWrapper.FINISH_GAME);
        db.createScore(game);

        return game;
    }

    /**
     * Delete game
     *
     * @param gameId
     * @return rows affected
     */
    public static int deleteGame(int gameId) {
        return db.deleteEntry(gameId, DatabaseWrapper.DELETE_GAME);
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


    public static ArrayList<Score> getScoresByUserID(int userId) {
        return db.getScoresByUserID(userId);
    }
}
