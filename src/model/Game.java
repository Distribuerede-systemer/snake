package model;

/**
 * Created by Peter on 12-10-2015.
 */

public class Game {

    //Creating the variables needed for the game
    private int gameId;
    private int result;
    private int newGame;
    private int endGame;
    private String controls;
    private String host;
    private String opponent;
    private String status;

    //Creating the constructor and initiating the variables
    public Game (int gameId, int result, String controls, int newGame, int endGame, String host, String opponent, String status) {
        this.gameId = gameId;
        this.result = result;
        this.controls = controls;
        this.newGame = newGame;
        this.endGame = endGame;
        this.host = host;
        this.opponent = opponent;
        this.status = status;
    }


    //methods that the API can use
    public int getResultForGame(){
        return result;
    }

    public void validate(){
        
    }

    //Creating get/set method for all the variables, so they can be used by other classes
    public int getGameId(){
        return gameId;
    }

    public void setGameId(int gameId){
        this.gameId = gameId;
    }

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

    public String status(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }


}
