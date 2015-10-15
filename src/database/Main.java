package database;


import model.Game;
import model.Score;
import model.User;

import java.util.ArrayList;

//
public class Main {

    public static void main(String[] args) {
        DatabaseWrapper db = new DatabaseWrapper();

        System.out.println(db.getUser(1).getFirstName());

        System.out.println(db.getGame(2).getGameName());
        System.out.println(db.getScore(1).getHighScore());

        ArrayList<User> users = db.getUsers();

        for(User user : users){
            System.out.println(user.getFirstName());
        }

        ArrayList<Game> games = db.getGames();

        for(Game game : games){
            System.out.println(game.getHostControls());
        }

        ArrayList<Score> scores = db.getScores();

        for(Score score : scores){
            System.out.println(score.getGameId());
        }

//        System.out.println(db.getUser("Users", 1).getFirstName());

    }



}