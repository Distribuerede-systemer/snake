package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import model.Config;
import model.Game;
// imports information about password and username from the User class in the model package
import model.User;
import tui.Tui;

/**
 * This class contains all methods that interact between the TUI / API and the data-layer in the Model package of the application.
 *
 * @author Henrik Thorn
 */
public class Logic {

    private static boolean isAuthenticated = false;


    //Gets a list of all active users and return these as a ArrayList of User objects
    public static ArrayList<User> getUsers() {

        // Define ArrayList to be used to add users and return them.
        ArrayList<User> uj = null;
        //TODO: Get all users from DB-wrapper

        return uj;


    }

    public boolean isUserAuthenticated() {
        return isAuthenticated;
    }

    public static boolean createUser(User user) {

        //TODO: Create user with DB-wrapper. If creation succeeded return true, if not return false

        return true;

    }

    public static boolean deleteUser(int id) {

        //TODO: Delete user via DB-wrapper
        return false;

    }

    public static void addUser(User user) {

        //TODO: Add user to DB via DB-wrapper

    }


    //Gets a list of all games and return these as an ArrayList of Game objects
    public static ArrayList<Game> getGames() {

        //TODO: Get ALL games via DB-wrapper

        ArrayList<Game> games = null;
        return games;

    }

    public static ArrayList<Game> getGames(int userId) {

        //TODO: Get ALL games createdBy by specified userId, via DB-wrapper

        ArrayList<Game> games = null;
        return games;

    }


    //Returns object of game
    public static Game getGame(int gameId) {

        //TODO: Get specific game via DB-wrapper

        return null;
    }

    //Return an istance of a game
    public static Game createGame(String gameName, User host) {

        //int gameId, int result, String controls, int newGame, int endGame, String host, String opponent, String status
        Game game = new Game();
        game.setName(gameName);
        game.setHost(host);
        game.setStatus(1); //1 is pending, 0 is done

        //TODO: Write game to db, and return game-id and set object before returning

        return game;
    }

    // Authenticates a user and returns a status code according to the result.
    // CODES:
    // 1 || SUCCESS
    // 2 || USER DOES NOT EXIST
    // 3 || WRONG PASSWORD
    public static int login(String username, String password) {

        ArrayList<User> allUsers = getUsers();
        for (User user : allUsers){
            if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                return 1;
            }
        }

        return 0;

    }

    public User getUser(int userId) {

        //TODO: Get specific user from DB via DB-wrapper
        User user = new User();
        return user;

    }

    //Deletes a game from the database
    public boolean deleteGame(int gameId) {

        //TODO: Delete specific game from DB via DB-wrapper;

        return false;
    }

}
