package tui; /**
 * Created by Jakob Frederik Frank on 12-10-2015.
 */
import java.util.*;

public class Tui {

    private Scanner input;

    public Tui(){
        input = new Scanner(System.in);
    }

    public void userMenu(){

        boolean isAuthenticated = true;

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
/*
    public void listUsers(){

        List<User> userList = Logic.getUsers();

        for(User usr : userList){
            System.out.println("User: " + usr.getUsername());
        }
        System.out.print("\n");
    }

    public void listGames(){

        List<Game> gameList = Logic.getGames();

        for(Game gm : gameList){
            System.out.println("Game: " + gm.getGameId() + " Host: " + gm.getHost() + " Opponent: " + gm.getOpponent() + " Winner: " + gm.getResult());
        }
        System.out.print("\n");
    }
    */

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
