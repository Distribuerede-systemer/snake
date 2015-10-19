package controller;

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

<<<<<<< Temporary merge branch 1
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
=======
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

        //TODO: Get ALL games via DB-wrapper

        ArrayList<Game> games = null;
        return games;

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
>>>>>>> Temporary merge branch 2

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
    public static boolean deleteGame(int gameId) {

        //TODO: Delete specific game from DB via DB-wrapper;

        return false;
    }



}
