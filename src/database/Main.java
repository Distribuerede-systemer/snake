package database;


import model.Game;
import model.Score;
import model.User;

import java.util.ArrayList;

//
public class Main {

    public static void main(String[] args) {
        DatabaseWrapper db = new DatabaseWrapper();

        User user = db.getUser(1);
        user.setFirstName("Tobias");
        db.updateUser(user);

//        System.out.println(db.getUser("Users", 1).getFirstName());

    }



}