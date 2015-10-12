package controller;

import java.util.ArrayList;
import model.Game;

/**
 * This class contains all methods that interact between the TUI / API and the data-layer in the Model package of the application. 
 * @author Henrik Thorn
 */
public class Logic {

	//TODO: Delete this main method. 
	public static void main(String[] args) {
		System.out.println("Hello World!"); // Display the string.
	}

	//Gets a list of all active users and return these as a ArrayList of User objects 
	public ArrayList getUsers(){

		// Define ArrayList to be used to add users and return them. 
		ArrayList users = new ArrayList();	
		
		// Return Users
		return users;
	}
	
	//Gets a list of all games and return these as a ArrayList of Game objects 
	public ArrayList getGames(){

		// Define ArrayList to be used to add games and return them. 
		ArrayList games = new ArrayList();	
		
		// Return Games
		return games;
	}

	//Return an istance of a game
	public Game getGame(String gameName) {

		//int gameId, int result, String controls, int newGame, int endGame, String host, String opponent, String status
		Game game = new Game(1, 1, "ASD", 1, 1, "localhost", "abcd", "HEJ");

		return game;
	}

	// Authenticates a user and returns a status code according to the result. 
	// CODES:
	// 1 || SUCCESS
	// 2 || USER DOES NOT EXIST
	// 3 || WRONG PASSWORD
	public int userLogin(String username, String password){
		int code = 0;

		//authenticate user with some function

		//Return status code for further use
		return code;
	}	
}
