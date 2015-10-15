package controller;
import model.Gamer;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Gamer gamer1 = new Gamer();
        gamer1.setControls("awdawdawd");
        Gamer gamer2 = new Gamer();
        gamer2.setControls("dwadwadwa");

        Map gamers = (Map) GameEngine.playGame(15, gamer1, gamer2);
        // Get Host (h)
        Gamer host = (Gamer) gamers.get('h');

        // Get Opponent (o)
        Gamer opponent = (Gamer) gamers.get('o');

        System.out.println(host.getScore());
    }
}