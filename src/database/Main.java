package database;


import model.Gamer;

import java.util.ArrayList;

//
public class Main {

    public static void main(String[] args) {
        DatabaseWrapper db = new DatabaseWrapper();

        ArrayList<Gamer> gamers = db.getScore();

        for(Gamer gamer : gamers){
            System.out.println("User: " + gamer.getUserName() + " Highscore: " + gamer.getTotalScore());
        }
//        Game game = new Game("asasa", "Fifth Snake Game", db.getUser(1), db.getUser(3), "pending");
//
//
//        int gameId = db.createGame(game);
//
//
//        game.setId(gameId);
//        game.setOpponentControls("sss");
//        game.setStatus("Finished");
//        game.setWinner(db.getUser(1));
//
//        db.updateGame(game);
//
//        Gamer host = new Gamer();
//        host.setScore(66);
//        host.setId(1);
//
//        Gamer opponent = new Gamer();
//        opponent.setScore(45);
//        opponent.setId(3);
//
//        db.createScore(gameId, host, opponent);


    }



}