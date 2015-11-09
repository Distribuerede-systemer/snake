package tui;

import java.util.*;

import controller.Logic;
import controller.Security;
import model.Game;
import model.User;

public class Tui {

    private static Scanner input = new Scanner(System.in);

    public void login() {
        miscOut("Please log in.");

        int[] result = Logic.authenticateUser(enterUsername(), Security.hashing(enterPassword()));
        //Sets index 0 to 0, so user cannot login as admin
        if (result[0] == 1) {
            result[1] = 0;
        }

        int code = result[1];
        if (code == 0)
            miscOut("User does not exist.");
        else if (code == 1) {
            miscOut("Wrong password.");
        } else if (code == 2) {
            miscOut("Success.");
            Logic.adminIsAuthenticated = true;
            userMenu();
        }

    }

    public static void userMenu() {

        while (Logic.adminIsAuthenticated) {

            int menu = userMenuScreen();

            switch (menu) {
                case 1:
                    miscOut("Game List: ");
                    ArrayList<Game> gameList = Logic.getGames(Logic.ALL_GAMES, 0);
                    listGames(gameList);
                    break;
                case 2:
                    miscOut("User List: ");
                    ArrayList<User> userList = Logic.getUsers();
                    listUsers(userList);
                    break;
                case 3:
                    miscOut("Create User: ");
                    Logic.createUser(createUser());
                    break;
                case 4:
                    miscOut("Delete User: ");
                    Logic.deleteUser(deleteUserScreen());
                    break;
                case 5:
                    miscOut("You Logged Out.");
                    Logic.adminIsAuthenticated = false;
                    break;
                default:
                    miscOut("Unassigned key.");
                    break;

            }
        }
    }

    public static int userMenuScreen() {

        System.out.println("Please make a choice");
        System.out.println("\n1: List all games");
        System.out.println("2: List all users");
        System.out.println("3: Create user");
        System.out.println("4: Delete user");
        System.out.println("5: Log out");

        return input.nextInt();
    }

    public static void listUsers(ArrayList<User> userList) {

        for (User user : userList) {
            System.out.println("Id: " + user.getId() + "\tUser: " + user.getUsername() + "\tStatus: " + user.getStatus());
        }
    }

    public static void listGames(ArrayList<Game> gameList) {

        for (Game game : gameList) {
            System.out.println("GameId: " + game.getGameId() + "\tHostId: " + game.getHost().getId() + "\tOpponentId: " + game.getOpponent().getId() + "\tWinner: " + game.getWinner().getId());
        }
    }

    public static User createUser() {

        //TODO: This should work! - DONE
        User usr = new User();
        usr.setFirstName(enterFirstName());
        usr.setLastName(enterLastName());
        usr.setEmail(enterEmail());
        usr.setUsername(enterUsername());
        usr.setPassword(enterPassword());
        usr.setType(enterUserType());

        return usr;
    }


    public static int deleteUserScreen() {
        listUsers(Logic.getUsers());
        System.out.print("Type id on the user you wish to delete: ");

        return input.nextInt();
    }

    public static String enterUsername() {

        System.out.print("Please enter username: "); // Brugeren bliver spurgt om username
        String username = input.next();

        return username;
    }

    public static String enterPassword() {

        System.out.print("Please enter password: "); // Brugeren bliver spurgt om password
        String password = input.next();

        return password;
    }

    public static String enterFirstName() {
        System.out.print("Please enter first name: "); // Brugeren bliver spurgt om fornavn
        String firstName = input.next();

        return firstName;
    }

    public static String enterLastName() {
        System.out.print("Please enter last name: "); // Brugeren bliver spurgt om efternavn
        String lastName = input.next();

        return lastName;
    }

    public static String enterEmail() {
        System.out.print("Please enter email: "); // Brugeren bliver spurgt om email
        String email = input.next();

        return email;
    }

    public static int enterUserType() {
        boolean userTypeApproved = false;
        System.out.print("Please enter user type (1 or 2) \n1) Admin\n2) User\n");
        int userType = input.nextInt();

        do {
            if (userType != 1 && userType != 2)
                System.out.println("Type must be either admin or user. P try again");
            else{
                userTypeApproved = true;
            }


        } while (!userTypeApproved);

        return userType-1;
    }

    public static void miscOut(String s) {
        System.out.println(s);
    }
}