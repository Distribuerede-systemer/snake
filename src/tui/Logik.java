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

    public User getUserFromUsername(String username){

        for (User usr : userList) {
            if (usr.getUsername().equals(username)){
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

    public boolean deleteUser(){

        String username = tui.deleteUserScreen();

        if(removeUser(getUserFromUsername(username))) {
            tui.miscOut(username + " was deleted.");
            if(username.equals(usr.getUsername()))
                start();
            else
                return true;
        }
        else
            tui.miscOut(username + " was not found.");
            return false;

    }

    public void addUser(String username, String password){

        userList.add(new User(username, password));
    }

    public boolean removeUser(User u){
        try {
            if (userList.remove(u))
                return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
