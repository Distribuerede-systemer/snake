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



	public Logic(){

		Config config = new Config();
		connection = DriverManager.getConnection(config.getDbname(), config.getUsername(), config.getPassword());

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
	public ArrayList<Game> getGames(String type){

		ArrayList <Game> games = null;


		try(ResultSet resultset = games.executeQuery()) {

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
				if (usr.getUsername().equals(username))
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
	/*
	public User getUserLogin(String username, String password){

		for (User usr : userList) {
			if (usr.getUsername().equals(username) && usr.getPassword().equals(password))
			{
				return usr;
			}
		}
		return null;
	}
	*/
	public User getUserFromUsername(String username){

		for (User usr : userList) {
			if (usr.getUsername().equals(username)){
				return usr;
			}
		}
		return null;
	}

	//Deletes a game from the database
	public boolean deleteGame(){
	}


	
	//Deletes a user from the database


}
