package tui;
import java.util.*;

public class Tui {

    private Scanner input;
    boolean isAuthenticated;

    public Tui(){
        input = new Scanner(System.in);
    }

    public void listUsers(ArrayList<User> userList){

        for(User usr : userList){
            System.out.println("User: " + usr.getUsername());
        }
    }
/*
    public void listGames(ArrayList<Game> gameList){

        for(Game gm : gameList){
            System.out.println("Game: " + gm.getGameId() + " Host: " + gm.getHost() + " Opponent: " + gm.getOpponent() + " Winner: " + gm.getResult());
        }
        System.out.print("\n");
    }
*/

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
        System.out.print("Please enter first name: "); // Brugeren bliver spurgt om password
        String firstName = input.next();

        return firstName;
    }

    public String enterLastName(){
        System.out.print("Please enter last name: "); // Brugeren bliver spurgt om password
        String lastName = input.next();

        return lastName;
    }

    public String enterEmail(){
        System.out.print("Please enter email: "); // Brugeren bliver spurgt om password
        String email = input.next();

        return email;
    }

    public void miscOut(String s){
        System.out.println(s);
    }
}
