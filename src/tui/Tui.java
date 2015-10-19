package tui;
import java.util.*;

import controller.Logic;
import model.Game;
import model.User;

public class Tui {

    private Scanner input;

    public Tui(){

        input = new Scanner(System.in);
    }

    public void listUsers(ArrayList<User> userList){

        for(User usr : userList){
            System.out.println("User: " + usr.getUserName());
        }
    }

    public void listGames(ArrayList<Game> gameList){

        for(Game gm : gameList){
            System.out.println("Game: " + gm.getGameId() + " Host: " + gm.getHost() + " Opponent: " + gm.getOpponent() + " Winner: " + gm.getResult());
        }
    }

    public void userMenu(){

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
                   // Logic.createUser(); TODO: param efter db-wrap
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
    public int userMenuScreen(){

        System.out.println("\n1: List all games");
        System.out.println("2: List all users");
        System.out.println("3: Create user");
        System.out.println("4: Delete user");
        System.out.println("5: Log out");

        return input.nextInt();
    }

    public String deleteUserScreen(){
       // listUsers();

        System.out.print("Type username you wish to delete: ");
        String username = input.next();

        return username;
    }

    public String enterUsername(){

        System.out.print("Please enter username: "); // Brugeren bliver spurgt om username
        String username = input.next();

        return username;
    }

    public String enterPassword(){

        System.out.print("Please enter password: "); // Brugeren bliver spurgt om password
        String password = input.next();

        return password;
    }

    public String enterFirstName(){
        System.out.print("Please enter first name: "); // Brugeren bliver spurgt om fornavn
        String firstName = input.next();

        return firstName;
    }

    public String enterLastName(){
        System.out.print("Please enter last name: "); // Brugeren bliver spurgt om efternavn
        String lastName = input.next();

        return lastName;
    }

    public String enterEmail(){
        System.out.print("Please enter email: "); // Brugeren bliver spurgt om email
        String email = input.next();

        return email;
    }

    public String enterUserType(){
        System.out.print("Please enter user type. can be api/server");
        String userType = input.next();

        if(!userType.equals("api") && !userType.equals("server")){

            System.out.println("Type must be either api or server. please try again");
            enterUsername();
        }

        return userType;
    }

    public User createUser(){

        User usr = new User(enterFirstName(), enterLastName(), enterEmail(), enterUsername(), enterPassword(),enterUserType() );

        return usr;
    }

    public void miscOut(String s){
        System.out.println(s);
    }
}
