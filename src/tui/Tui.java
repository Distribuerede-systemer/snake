//package tui;
//import java.util.*;
//
//import controller.Logic;
//import model.User;
//
//public class Tui {
//
//    private Scanner input;
//    private User usr;
//
//    public Tui(){
//
//        input = new Scanner(System.in);
//    }
//
//    public void listUsers(ArrayList<User> userList){
//
//        for(User usr : userList){
//            System.out.println("User: " + usr.getUserName());
//        }
//    }
///*
//    public void listGames(ArrayList<Game> gameList){
//
//        for(Game gm : gameList){
//            System.out.println("Game: " + gm.getGameId() + " Host: " + gm.getHost() + " Opponent: " + gm.getOpponent() + " Winner: " + gm.getResult());
//        }
//        System.out.print("\n");
//    }
//*/
//
//    public void userMenu(){
//
//        while(true) {
//
//            int menu = userMenuScreen();
//
//            switch (menu) {
//
//                case 1:
//                    // listUsers();
//                    miscOut("Game List: ");
//                    break;
//                case 2:
//                    miscOut("User List: ");
//                    ArrayList<User> userList = getUsers();
//                    listUsers(userList);
//                    break;
//                case 3:
//                    miscOut("Create User: ");
//                    createUser();
//                    break;
//                case 4:
//                    miscOut("Delete User: ");
//                    deleteUser();
//                    break;
//                case 5:
//                    miscOut("You Logged Out.");
//                    isAuthenticated = false;
//                    break;
//                default:
//                    miscOut("Unassigned key.");
//                    break;
//
//            }
//        }
//    }
//    public int userMenuScreen(){
//
//        System.out.println("\n1: List all games");
//        System.out.println("2: List all users");
//        System.out.println("3: Create user");
//        System.out.println("4: Delete user");
//        System.out.println("5: Log out");
//
//        return input.nextInt();
//    }
//
//    public String deleteUserScreen(){
//        // listUsers();
//
//        System.out.print("Type username you wish to delete: ");
//        String username = input.next();
//
//        return username;
//    }
//
//    public String enterUsername(){
//
//        System.out.print("Please enter username: "); // Brugeren bliver spurgt om username
//        String username = input.next();
//
//        return username;
//    }
//
//    public String enterPassword(){
//
//        System.out.print("Please enter password: "); // Brugeren bliver spurgt om password
//        String password = input.next();
//
//        return password;
//    }
//
//    public String enterFirstName(){
//        System.out.print("Please enter first name: "); // Brugeren bliver spurgt om password
//        String firstName = input.next();
//
//        return firstName;
//    }
//
//    public String enterLastName(){
//        System.out.print("Please enter last name: "); // Brugeren bliver spurgt om password
//        String lastName = input.next();
//
//        return lastName;
//    }
//
//    public String enterEmail(){
//        System.out.print("Please enter email: "); // Brugeren bliver spurgt om password
//        String email = input.next();
//
//        return email;
//    }
//
//    public void miscOut(String s){
//        System.out.println(s);
//    }
//}
