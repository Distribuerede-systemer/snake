package tui; /**
 * Created by Jakob Frederik Frank on 12-10-2015.
 */
import java.util.*;

public class Tui {

    private Scanner input;
    boolean isAuthenticated;
    private User usr;
    private Logik lg;

    public Tui(){
        input = new Scanner(System.in);
        lg = new Logik();
    }

    public void start(){

        while (true) {
            isAuthenticated = login();

            if(isAuthenticated){
                userMenu();
            }
        }
    }

    public void userMenu(){

        while(isAuthenticated) {

            int menu = userMenuScreen();

            switch (menu) {

                case 1:
                    // listUsers();
                    System.out.println("Du har valgt at se alle spil");
                    break;
                case 2:
                    System.out.println("Du har valgt at se alle brugere");
                    break;
                case 3:
                    System.out.println("Du har valgt at oprette en bruger");
                    createUser();
                    break;
                case 4:
                    System.out.println("Du har valgt at slette en bruger");
                    break;
                case 5:
                    System.out.println("Du har valgt at logge ud");
                    isAuthenticated = false;
                    break;
                default:
                    System.out.println("Ugyldigt input");
                    break;

            }
        }
    }

    public boolean login() {

        System.out.print("Please type your username: "); // Brugeren bliver spurgt om username
        String username = input.next();
        System.out.print("Please type your password: "); // Brugeren bliver spurgt om password
        String password = input.next();

        try {
            usr = lg.login(username, password);

            if (usr.getUsername() != null)
                return true;
            else
                return false;
        } catch (NullPointerException n) {
           System.out.println("Invalid login");
        }
        return false;
    }
/*
    public void listUsers(){

        ArrayList<User> userList = Logic.getUsers();

        for(User usr : userList){
            System.out.println("User: " + usr.getUsername());
        }
        System.out.print("\n");
    }

    public void listGames(){

        ArrayList<Game> gameList = Logic.getGames();

        for(Game gm : gameList){
            System.out.println("Game: " + gm.getGameId() + " Host: " + gm.getHost() + " Opponent: " + gm.getOpponent() + " Winner: " + gm.getResult());
        }
        System.out.print("\n");
    }
    */

    public void createUser(){

        String username = enterUsername();
        String password = enterPassword();

        lg.createUser(username, password);
    }

    public int userMenuScreen(){

        System.out.println("1: List all games");
        System.out.println("2: List all users");
        System.out.println("3: Create user");
        System.out.println("4: Delete user");
        System.out.println("5: Log out");

        return input.nextInt();
    }

    public String deleteUserScreen(){
        // listUsers();

        System.out.println("Type username you wish to delete: ");
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
}
