package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


import model.Config;
import model.Game;
// imports information about password and username from the User class in the model package
import model.User;
import tui.Tui;

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

	private Tui tui;
	private User usr;
	private ArrayList<User> userList;
	private boolean isAuthenticated;
	private Game gme;



	public Logic(){

		Config config = new Config();

		try {
			connection = DriverManager.getConnection(config.getDbname(), config.getUsername(), config.getPassword());
		}
		catch (Exception e){
			tui.miscOut("Error.");
		}

		users = DB.getRecords('user');
		games = DB.getRecords('games');

		tui = new Tui();
		userList = new ArrayList<User>();
		isAuthenticated = false;

	}

	public void start(){

		while (true) {
			if(login() == 1)
				isAuthenticated = true;

			if(isAuthenticated){
				userMenu();
			}
		}
	}

	public void userMenu(){

		while(isAuthenticated) {

			int menu = tui.userMenuScreen();

			switch (menu) {

				case 1:
					// listUsers();
					tui.miscOut("Game List: ");
					break;
				case 2:
					tui.miscOut("User List: ");
					tui.listUsers(userList);
					break;
				case 3:
					tui.miscOut("Create User: ");
					createUser();
					break;
				case 4:
					tui.miscOut("Delete User: ");
					deleteUser();
					break;
				case 5:
					tui.miscOut("You Logged Out.");
					isAuthenticated = false;
					break;
				default:
					tui.miscOut("Unassigned key.");
					break;

			}
		}
	}




	//Gets a list of all active users and return these as a ArrayList of User objects 
	public ArrayList<User> getUsers(){

		// Define ArrayList to be used to add users and return them. 
		ArrayList <User> uj = null;

		try(ResultSet resultSet = users.executeQuery()) {

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

	public void createUser(){

		addUser(tui.enterFirstName(), tui.enterLastName(),tui.enterUsername(), tui.enterPassword());
	}

	public boolean deleteUser(){

		String username = tui.deleteUserScreen();

		if(removeUser(getUserFromUsername(username))) {
			tui.miscOut(username + " was deleted.");
			if(username.equals(usr.getUsername()))
				start();
			else
				return true;
		}
		else
			tui.miscOut(username + " was not found.");
		return false;

	}

	public void addUser(String firstName, String lastName, String username, String password){

		userList.add(new User(firstName, lastName, username, password));
	}

	public boolean removeUser(User u){
		try {
			if (userList.remove(u))
				return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}


	//Gets a list of all games and return these as an ArrayList of Game objects
	public ArrayList<Game> getGames(){

		ArrayList <Game> games = null;


		try(ResultSet resultSet = games.executeQuery()) {

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
	public int login() {

		String username;
		String password;
		try {

			username =  tui.enterUsername();
			password = tui.enterPassword();

			for (User usr : userList) {
				if (usr.getUserName().equals(username))
				{
					if(usr.getPassword().equals(password)) {
						tui.miscOut("Success.");
						return 1;
					}
					else {
						tui.miscOut("Wrong password.");
						return 3;
					}
				}
				else {
					tui.miscOut("User does not exist.");
					return 2;
				}
			}
		} catch (NullPointerException n) {
			tui.miscOut("Invalid login");
		}
		return 2;
	}

	public User getUserFromUsername(String username){

		for (User usr : userList) {
			if (usr.getUserName().equals(username)){
				return usr;
			}
		}
		return null;
	}

	//Deletes a game from the database
	public boolean deleteGame(){
		return false;
	}

	//Deletes a user from the database
	//TODO: Create function.

	public Array playGame(int size, String hostControls, String opponentControls){
		// Dummy variables that should be replaced with data from the host:
		String hostControls = "ddssaawwdddddd";
		String opponentControls = "ddwwaass";
		String turn = Math.round(Math.random() * 1) == 0 ? "host" : "opponent";

		// Hosting party:
		System.out.println("\n### GAME: host is " + turn + "! ###\n");

		// Split each controls string into a character array:
		char[] hostControlCharacters = hostControls.toCharArray();
		char[] opponentControlCharacters = opponentControls.toCharArray();

		// The game variables for the host:
		boolean hostDidCrash = false;
		int hostScore = 0;
		int hostKills = 0;
		int hostX = 0;
		int hostY = 1;

		// The game variables for the opponent:
		boolean opponentDidCrash = false;
		int opponentScore = 0;
		int opponentX = 0;
		int opponentY = -1;
		int opponentKills = 0;

		// Playing field options:
		int boundary = 4;

		// Total amount of moves:
		int movesCount = hostControls.length() < opponentControls.length() ? opponentControls.length() : hostControls.length();

		// Arrays of host and opponent moves:
		int[][] hostMoves = new int[movesCount][];
		int[][] opponentMoves = new int[movesCount][];

		// Loop through the host (leader) moves:
		System.out.println("Looping through host moves:\n");

		for (int i = 0; i < hostControls.length(); i++) {
			char move = hostControlCharacters[i];

			if (move == 'a') {
				hostX--;
			} else if (move == 'd') {
				hostX++;
			} else if (move == 'w') {
				hostY++;
			} else if (move == 's') {
				hostY--;
			}

			hostMoves[i] = new int[]{hostX, hostY};

			System.out.println(Arrays.toString(hostMoves[i]));
		}

		// Loop through the follower (opponent) moves:
		System.out.println("\nLooping through opponent moves:\n");

		for (int i = 0; i < opponentControls.length(); i++) {
			char move = opponentControlCharacters[i];

			if (move == 'a') {
				opponentX--;
			} else if (move == 'd') {
				opponentX++;
			} else if (move == 'w') {
				opponentY++;
			} else if (move == 's') {
				opponentY--;
			}

			opponentMoves[i] = new int[]{opponentX, opponentY};

			System.out.println(Arrays.toString(opponentMoves[i]));
		}

		// Loop through both moves to find collisions:
		System.out.println("\nDetecting collisions in favor of " + turn + ":\n");

		for (int i = 0; i < movesCount; i++) {
			int[] hostMove = hostMoves[i];
			int[] opponentMove = opponentMoves[i];

			// Kills:
			if (hostMove != null && opponentMove != null && hostMove[0] == opponentMove[0] && hostMove[1] == opponentMove[1]) {
				if (turn.equals("host")) {
					hostKills++;

					opponentDidCrash = true;
				} else {
					opponentKills++;

					hostDidCrash = true;
				}

				System.out.println("Collision at [" + hostMove[0] + ", " + hostMove[1] + "]!\tHost " + hostKills + " - Opponent " + opponentKills);
			}

			// Host move:
			if (hostMove != null) {

				// Check if the host move results in a wall crash:
				if (hostMove[0] > boundary || hostMove[1] > boundary) {
					hostDidCrash = true;
				}

				// Check if the host move is not null and if the host did not previously crash:
				if (!hostDidCrash) {
					hostScore++;
				}

			}

			// Opponent move:
			if (opponentMove != null) {

				// Check if the opponent move results in a wall crash:
				if (opponentMove[0] > boundary || opponentMove[1] > boundary) {
					opponentDidCrash = true;
				}

				// Check if the opponent move is not null and if the opponent did not previously crash:
				if (!opponentDidCrash) {
					opponentScore++;
				}

			}

		}

		System.out.println("hostScore: " + hostScore);
		System.out.println("opponentScore: " + opponentScore);

	}
}
}
