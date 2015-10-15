package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


import javafx.util.Pair;
import model.Config;
import model.Game;
// imports information about password and username from the User class in the model package
import model.Gamer;
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
	private ArrayList<Game> gameList;
	private Game game;


	// Creates constructor, initializes new instanceo of Config class.
	//Gets connection from database, with parameters from config instance.
	public Logic(){

		Config config = new Config();

		try {
			connection = DriverManager.getConnection(config.getDbname(), config.getUsername(), config.getPassword());
		}
		catch (Exception e){
			tui.miscOut("Error.");
		}

		//Uses statements received from db-wrapper.
		users = DB.getRecords('user');
		games = DB.getRecords('games');
		createUser = DB.getRecords('createUser');
		deleteUser = DB.getRecords('deleteUser');

		//Initialize tui class
		//Initialize ArrayLists of type User and Game
		//Set isAuthenticated false.
		tui = new Tui();
		userList = new ArrayList<User>();
		gameList = new ArrayList<Game>();
		isAuthenticated = false;

	}


	public void start(){

		//infinite while-loop if login-method returns 1.
		//Run userMenu method, if isAuthenticated is true.
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

			//Instantiate userMenuScreen method from tui as menu.
			int menu = tui.userMenuScreen();

			//Run switch, read input from tui.
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

		//Try as long as resultSet returns data.
		try(ResultSet resultSet = users.executeQuery()) {

			//Instantiate ArrayList of type User as uj.
			uj = new ArrayList<User>();

			//While-loop to continue as long as resultSet contains data.
			while (resultSet.next()){

				//Add results from resultSet to a new object in ArrayList.
				uj.add(new User(resultSet.getInt("ID"),
							resultSet.getString("Firstname"),
							resultSet.getString("Lastname"),
							resultSet.getString("Email"),
							resultSet.getString("Username"),
							resultSet.getString("Password"),
							resultSet.getDate("Created"),
							resultSet.getString("Status"),
							resultSet.getString("Type")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		// Return Users
		return uj;
	}
	//CreateUser-method.
	public void createUser(String firstname, String lastname, String email, String username, String password){

		firstname = tui.enterFirstName();
		lastname = tui.enterLastName();
		email = tui.enterUsername();
		username = tui.enterUsername();
		password = tui.enterPassword();

		//addUser(firstname, lastname,email ,username,password);


		try {

			createUser.setString(1, firstname);
			createUser.setString(2, lastname);
			createUser.setString(3, email);
			createUser.setString(4, username);
			createUser.setString(5, password);

			createUser.executeUpdate();

		}catch(Exception e){
			System.out.print(e.getStackTrace());
			tui.miscOut("Failed.");
		}
	}

	/*
	//DeleteUser-method.
	public boolean deleteUser(){

		//Reads input from tui.
		String username = tui.deleteUserScreen();

		//If-statement uses remove-method.
		if(removeUser(getUserFromUsername(username))) {

			//Prints out to user which user was deleted.
			tui.miscOut(username + " was deleted.");

			//if user was deleted, run start-method.
			if(username.equals(usr.getUserName()))
				start();
			else
				return true;
		}
		//Else print out that user was not found to the admin.
		else
			tui.miscOut(username + " was not found.");
		return false;
	}
*/

	public void deleteUser(String username){

		username = tui.deleteUserScreen();

		try{

			deleteUser.setString(1, username);

			deleteUser.executeUpdate();

		}
		catch (Exception e){
			e.printStackTrace();
			tui.miscOut("User doesn't exist");
		}
	}
	//Add user to the ArrayList of type user. take parameters.
	public void addUser(String firstName, String lastName, String email, String username, String password){

		//Adds a new user to ArrayList.
		userList.add(new User(firstName, lastName, email, username, password));
	}

	//Remove user from ArrayList type User. Takes one parameter of user.
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
	//Same method as getUser method.
	public ArrayList<Game> getGames(){

		ArrayList <Game> games = null;


		try(ResultSet resultSet = games.executeQuery()) {

			games = new ArrayList<Game>();

			while (resultSet.next()){

				games.add(new Game(resultSet.getInt("ID"),
							resultSet.getInt("Result"),
							resultSet.getInt("NewGame"),
							resultSet.getInt("EndGame"),
							resultSet.getString("Host"),
							resultSet.getString("Opponent"),
							resultSet.getString("Status"),
							resultSet.getString("HostControls"),
							resultSet.getString("OpponentControls")));
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
	//TODO: Finish createGame method.
	public Game createGame(String gameName) {

		//int gameId, int result, String controls, int newGame,
		// int endGame, String host, String opponent, String status
		//Game game = new Game(1, 1, "ASD", 1, 1, "localhost", "abcd", "HEJ");


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
			User user = getUserFromUsername(username);

			password = tui.enterPassword();

				if (user.getUserName().equals(username))
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

		} catch (NullPointerException n) {
			tui.miscOut("Invalid login");
		}
		return 2;
	}

	public int login(String _username, String _password){

		try {

			String username = _username;
			User user = getUserFromUsername(_username);

			String password = _password;

			if (user.getUserName().equals(username))
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

		} catch (NullPointerException n) {
			tui.miscOut("Invalid login");
		}
		return 2;
	}


	public User getUserFromLogin(String username, String password) {

		User user = null;
		try {

			for (User usr : userList) {
				if (usr.getUserName().equals(username) && usr.getPassword().equals(password))
					user = usr;
			}
		}catch(NullPointerException e) {
			System.out.println("User not found");
		}
		return user;
	}


	public User getUserFromUsername(String username){

		for (User usr : userList) {
			if (usr.getUserName().equals(username)){
				return usr;
			}
		}
		return null;
	}

	public Game getGameFromGameId(int gameId){

		for (Game game : gameList) {
			if (game.getGameId() == gameId){
				return game;
			}
		}
		return null;
	}

	public int getGameResult(int gameId){

		return getGameFromGameId(gameId).getResult();

	}
	//Deletes a game from the database
	public boolean deleteGame(){

		return false;
	}

	//Deletes a user from the database
	//TODO: Create function.



}
