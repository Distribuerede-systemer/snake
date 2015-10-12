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
    public Game (int newResult, String newContols, int newNewGame, int newEndGame) {
        this.result = result;
        this.controls = controls;
        this.newGame = newGame;
        this.endGame = endGame;
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



}
