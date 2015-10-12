package model;

/**
 * Created by Peter on 12-10-2015.
 */
public class Game {

    //naming the variables needed for the game
    private int result;
    private String controls;
    private int newGame;
    private int endGame;
    private String host;
    private String opponent;


    //making the constructor and initiating the variables
    public Game (int result, String controls, int newGame, int endGame, String host, String opponent) {
        this.result = result;
        this.controls = controls;
        this.newGame = newGame;
        this.endGame = endGame;
        this.host = host;
        this.opponent = opponent;
    }

    //making get/set method for all the variables, so they can be used by other classes
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getControls() {
        return controls;
    }

    public void setControls(String controls) {
        this.controls = controls;
    }

    public int getNewGame() {
        return newGame;
    }

    public void setNewGame(int newGame){
        this.newGame = newGame;
    }

    public int getEndGame(){
        return endGame;
    }

    public void setEndGame(int endGame){
        this.endGame = endGame;
    }

    public String getHost(){
        return host;
    }

    public void setHost(String host){
        this.host = host;
    }

    public String getOpponent(){
        return opponent;
    }

    public void setOpponent(String opponent){
        this.opponent = opponent;
    }



}
