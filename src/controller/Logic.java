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

/**
 * This class contains all methods that interact between the TUI / API and the data-layer in the Model package of the application. 
 * @author Henrik Thorn
 */
public class Logic {

	private Connection connection = null;
	private PreparedStatement users = null;
	private PreparedStatement games = null;
	private PreparedStatement createUser = null;
	private PreparedStatement deleteUser = null;
	private PreparedStatement createGame = null;
	private PreparedStatement delteGame = null;


	public Logic(){

		Config config = new Config();
		connection = DriverManager.getConnection(config.getDbname(), config.getUsername(), config.getPassword());

		users = DB.getRecords('user');
		games = DB.getRecords('games');

	}

	//TODO: Delete this main method. 
	public static void main(String[] args) {
		System.out.println("Hello World!"); // Display the string.
	}

	//Gets a list of all active users and return these as a ArrayList of User objects 
	public ArrayList getUsers(){

		// Define ArrayList to be used to add users and return them. 
		ArrayList <User> uj = null;

		ResultSet resultSet = null;

		try {
			resultSet = users.executeQuery();

			uj = new ArrayList<User>();

			while (resultSet.next()){

				uj.add(new User(resultSet.getInt("ID"),
						resultSet.getString("Firstname"),
						resultSet.getString("Lastname"),
						 resultSet.getString("Username"),
						resultSet.getString("Password"),
						resultSet.getString("Created"),
						resultSet.getString("Status")));
			}

		}catch(Exception e){
			e.printStackTrace();}
		// Return Users

		return uj;


	}

	//return object of User.
	public User getUser(User userName)
	{

		return userName;
	}

	
	//Gets a list of all games and return these as an ArrayList of Game objects
	public ArrayList getGames(String type){

		ArrayList <Game> games = null;

		ResultSet resultSet = null;

		try {
			resultSet = users.executeQuery();

			games = new ArrayList<Game>();

			while (resultSet.next()){

				games.add(new Game(resultSet.getInt("ID"),
						resultSet.getInt("Result"),
						resultSet.getString("Controls"),
						resultSet.getInt("NewGame"),
						resultSet.getInt("EndGame"),
						resultSet.getString("Host"),
						resultSet.getString("Opponent"),
						resultSet.getString("Status")));
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return games;

	}

	//Returns object of game
	public Game getGame(Game gameName){

		return gameName;
	}

	//Return an istance of a game
	public Game createGame(String gameName) {

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


		for (User user : getUser()) {

			switch (code){
				case 1:
					user.getUserName().equals(username);
					user.getPassword().equals(password);
					System.out.println("Succes!");
					break;
				case 2:
					user.getPassword().!equals(password);
					System.out.println("Wrong password");
					break;
				default:
					System.out.println("User doesn't exist");
					break;
			}

			if (user.getPassword.equals(password) && user.getUserName().equals(username)) {
				System.out.println("Login Succeeded");
			} else if (user.getPassword != (password) && user.getUserName().equals(username)) {
				System.out.println("Wrong password");
			} else {
				System.out.print("User doesn't exist");
			}
		}

		//Return status code for further use
		return code;
	}

	//Deletes a game from the database
	public boolean deleteGame(int Id){
		boolean status = false;

		return status;
	}


	
	//Deletes a user from the database
	public boolean deleteUser(int Id){
		boolean status = false;

		return status;
	}

	public void createUser(int id, String firstName, String lastName, String userName, String password,
						   String created, String status){
		User user = new User(id, firstName, lastName, userName, password, created, status);


		String firstName = input.nextLine();
	}

}
