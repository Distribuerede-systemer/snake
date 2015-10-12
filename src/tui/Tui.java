package tui; /**
 * Created by Jakob Frederik Frank on 12-10-2015.
 */
import java.util.*;

public class Tui {

    private Scanner input;
    private Logik db;
    private User usr;
    boolean isAuthenticated;

    public Tui(){
        input = new Scanner(System.in);
        db = new Logik();
    }

    public void start(){

        while (true) {
            isAuthenticated = loginAuth();

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
                    listUsers();
                    break;
                case 2:
                    System.out.println("Du har valgt at oprette en bruger");
                    break;
                case 3:
                    System.out.println("Du har valgt at slette en bruger");
                    break;
                case 4:
                    System.out.println("Du har valgt at se alle spil");
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


    public void listUsers(){

        List<User> userList = db.getUsers();

        for(User usr : userList){
            System.out.println("User: " + usr.getUsername() + " " + usr.getPassword());
        }
        System.out.print("\n");
    }
/*
    public void listGames(){
        List<Game> gameList = db.getGames();

        for(Game gm : gameList){
            System.out.println("Game " + usr.getFirstName() + " " + usr.getLastName());
        }

    }
*/

    public boolean loginAuth(){

        System.out.print("Please type your username: "); // Brugeren bliver spurgt om username
        String username = input.next();
        System.out.print("Please type your password: "); // Brugeren bliver spurgt om password
        String password = input.next();


        usr = db.login(username, password);
        if(usr.getUsername() != null)
        return true;
        else
            return false;
    }

    public int userMenuScreen(){

        System.out.println("1: Show all users");
        System.out.println("2: Add user");
        System.out.println("3: Delete user");
        System.out.println("4: Show all games");
        System.out.println("5: Log off");

        return input.nextInt();
    }
}
