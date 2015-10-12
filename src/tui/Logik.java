package tui;

import java.util.*;

/**
 * Created by Jakob Frederik Frank on 12-10-2015.
 */
public class Logik {

   private ArrayList<User> userList = new ArrayList<User>();

    

    public User login(String username, String password){

        for (User usr : userList) {
            if (usr.getUsername().equals(username) && usr.getPassword().equals(password))
            {
                return usr;
            }
        }
        return null;
    }
    public ArrayList<User> getUsers(){

        return userList;
    }

    public void createUser(String username, String password){

        userList.add(new User(username, password));
    }

}
