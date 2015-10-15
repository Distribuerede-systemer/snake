package database;


import model.Game;
import model.Gamer;
import model.Score;
import model.User;

import java.util.ArrayList;

//
public class Main {

    public static void main(String[] args) {
        DatabaseWrapper db = new DatabaseWrapper();



        Game game = new Game("asasa", "Henrik Thorns Snake game", db.getUser(1), db.getUser(2), "pending");


        int gameId = db.createGame(game);


        game.setId(gameId);
        game.setOpponentControls("sss");
        game.setStatus("Finished");
        game.setWinner(db.getUser(1));

        db.updateGame(game);

        Gamer host = new Gamer();
        host.setScore(66);
        host.setId(1);

        Gamer opponent = new Gamer();
        opponent.setScore(45);
        opponent.setId(2);

        db.createScore(gameId, host, opponent);








    }



}