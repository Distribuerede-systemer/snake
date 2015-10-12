package tui;

import java.util.*;

/**
 * Created by Jakob Frederik Frank on 12-10-2015.
 */
public class Logik {

    User usr = new User("Mads", "123");
    User usr1 = new User("Jens", "321");

   private List<User> userList = Arrays.asList(usr,usr1);


    public User login(String username, String password){

        for (User usr : userList) {
            if (usr.getUsername().equals(username) && usr.getPassword().equals(password))
            {
                return usr;
            }
        }
        return null;
    }
    public List<User> getUsers(){

        return userList;
    }

}
