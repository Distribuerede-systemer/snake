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
    }

    public void start(){

        while (true) {
            if(login() == 1)
                isAuthenticated = true;

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

    public int login() {

        String username;
        String password;
        try {

            username =  tui.enterUsername();
            password = tui.enterPassword();

            for (User usr : userList) {
                if (usr.getUsername().equals(username))
                {
                    if(usr.getPassword().equals(password)) {
                        tui.miscOut("Success.");
                        return 1;
                    }
                    else {
                        tui.miscOut("Wrong password.");
                        return 3;
                    }
                }
                else {
                    tui.miscOut("User does not exist.");
                    return 2;
                }
            }
        } catch (NullPointerException n) {
            tui.miscOut("Invalid login");
        }
        return 2;
    }

    /*public User getUserLogin(String username, String password){

        for (User usr : userList) {
            if (usr.getUsername().equals(username) && usr.getPassword().equals(password))
            {
                return usr;
            }
        }
        return null;
    }
    */

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

        addUser(tui.enterFirstName(), tui.enterLastName(),tui.enterUsername(), tui.enterPassword());
    }

    public void addUser(String firstName, String lastName, String username, String password){ //Venter på DB

        userList.add(new User(firstName, lastName, username, password));
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

    public boolean removeUser(User u){ // Venter på Db
        try {
            if (userList.remove(u))
                return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
