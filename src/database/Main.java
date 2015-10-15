package database;


import model.Game;
import model.Score;
import model.User;

import java.util.ArrayList;

//
public class Main {

    public static void main(String[] args) {
        DatabaseWrapper db = new DatabaseWrapper();

        Game game = db.getGame(1);

        int i = 0;
        db.deleteUser(4);
        System.out.println(db.createGame(game));

//        System.out.println(db.getUser("Users", 1).getFirstName());

    }



}