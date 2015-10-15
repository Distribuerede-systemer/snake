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

        Game game = db.getGame(1);
        game.setHostControls("sadasas");
        db.updateGame(game);
        Score score = db.getScore(1);

        db.deleteGame(1);
        db.createUser(user);

        db.createGame(game);
        db.createScore(score);

//        System.out.println(db.getUser("Users", 1).getFirstName());

    }



}