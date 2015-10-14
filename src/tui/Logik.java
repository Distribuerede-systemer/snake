package tui;

import java.util.*;

public class Logik {


    private Tui tui;
    private User usr;
    private ArrayList<User> userList;
    private boolean isAuthenticated;


    public Logik(){

        tui = new Tui();
        userList = new ArrayList<User>();
        isAuthenticated = false;

        addUser("Mads","123");
        addUser("Ole", "321");
        addUser("Helle", "asd");
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

            int menu = tui.userMenuScreen();

            switch (menu) {

                case 1:
                    // listUsers();
                    System.out.println("Du har valgt at se alle spil");
                    break;
                case 2:
                    System.out.println("Du har valgt at se alle brugere");
                    tui.listUsers(userList);
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


        try {
            usr = getUserLogin(tui.enterUsername(), tui.enterPassword());

            if (usr.getUsername() != null)
                return true;
            else
                return false;
        } catch (NullPointerException n) {
            System.out.println("Invalid login");
        }
        return false;
    }



    public User getUserLogin(String username, String password){

        for (User usr : userList) {
            if (usr.getUsername().equals(username) && usr.getPassword().equals(password))
            {
                return usr;
            }
        }
        return null;
    }

    public ArrayList<User> getUserList(){

        return userList;
    }

    public void createUser(){

        addUser(tui.enterUsername(), tui.enterPassword());
    }

    public void addUser(String username, String password){

        userList.add(new User(username, password));
    }

}
