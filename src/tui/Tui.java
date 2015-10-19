package tui;
import java.util.*;

import controller.Logic;
import model.Game;
import model.User;

public class Tui {


    //TODO: Make this class use static methods - DONE
    private static Scanner input = new Scanner(System.in);

    public Tui(){
    }

    public static void listUsers(ArrayList<User> userList){

        for(User usr : userList){
            System.out.println("User: " + usr.getUserName());
        }
    }

    public static void listGames(ArrayList<Game> gameList){

        for(Game gm : gameList){
            System.out.println("Game: " + gm.getGameId() + " Host: " + gm.getHost() + " Opponent: " + gm.getOpponent() + " Winner: " + gm.getResult());
        }
    }

    public static void userMenu(){

        while(Logic.isUserAuthenticated()) {

            int menu = userMenuScreen();

            switch (menu) {

                case 1:
                    miscOut("Game List: ");
                    ArrayList<Game> gameList = Logic.getGames();
                    listGames(gameList);
                    break;
                case 2:
                    miscOut("User List: ");
                    ArrayList<User> userList = Logic.getUsers();
                    listUsers(userList);
                    break;
                case 3:
                    miscOut("Create User: ");
                    createUser(); // TODO: param efter db-wrap - DONE
                    break;
                case 4:
                    miscOut("Delete User: ");
                    Logic.deleteUser(Integer.parseInt(deleteUserScreen()));
                    break;
                case 5:
                    miscOut("You Logged Out.");
                    Logic.setIsUserAuthenticated(false);
                    break;
                default:
                    miscOut("Unassigned key.");
                    break;

            }
        }
    }
    public static int userMenuScreen(){

        System.out.println("\n1: List all games");
        System.out.println("2: List all users");
        System.out.println("3: Create user");
        System.out.println("4: Delete user");
        System.out.println("5: Log out");

        return input.nextInt();
    }

    public static String deleteUserScreen(){
        // listUsers();

        System.out.print("Type username you wish to delete: ");
        String username = input.next();

        return username;
    }

    public static String enterUsername(){

        System.out.print("Please enter username: "); // Brugeren bliver spurgt om username
        String username = input.next();

        return username;
    }

    public static String enterPassword(){

        System.out.print("Please enter password: "); // Brugeren bliver spurgt om password
        String password = input.next();

        return password;
    }

    public static String enterFirstName(){
        System.out.print("Please enter first name: "); // Brugeren bliver spurgt om fornavn
        String firstName = input.next();

        return firstName;
    }

    public static String enterLastName(){
        System.out.print("Please enter last name: "); // Brugeren bliver spurgt om efternavn
        String lastName = input.next();

        return lastName;
    }

    public static String enterEmail(){
        System.out.print("Please enter email: "); // Brugeren bliver spurgt om email
        String email = input.next();

        return email;
    }

    public static String enterUserType(){
        System.out.print("Please enter user type; admin or user.");
        String userType = input.next();

        if(!userType.equals("admin") && !userType.equals("user")){

            System.out.println("Type must be either admin or user. P try again");
            enterUsername();
        }
        return userType;
    }

    public static void createUser(){

        //TODO: This should work! - DONE
        User usr = new User();
        usr.setFirstName(enterFirstName());
        usr.setLastName(enterLastName());
        usr.setEmail(enterEmail());
        usr.setPassword(enterPassword());
        usr.setUserName(enterUsername());
        usr.setPassword(enterPassword());
        usr.setType(enterUserType());

        Logic.createUser(usr);
    }

    public static void miscOut(String s){
        System.out.println(s);
    }
}