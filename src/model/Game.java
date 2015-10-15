package model;

import java.util.Date;

/**
 * Created by Peter on 12-10-2015.
 */

public class Game {

    //Creating the variables needed for the game
    private int gameId;
    private int result;
    private int newGame;
    private int endGame;
    private String gameName;
    private String hostControls;
    private String host;
    private String opponent;
    private String opponentControls;
    private String status;
    private Date created;



    //Creating the constructor and initiating the variables
    public Game( int gameId, int result, String hostControls, Date created, String gameName, int newGame, int endGame, String host, String
            opponent, String status){
        this.gameId = gameId;
        this.result = result;
        this.created = created;
        this.hostControls = hostControls;
        this.gameName = gameName;
        this.newGame = newGame;
        this.endGame = endGame;
        this.host = host;
        this.opponent = opponent;
        this.status = status;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    //methods that the API can use
    //Creating get/set method for all the variables, so they can be used by other classes
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getHostControls() {
        return hostControls;
    }

    public void setHostControls(String hostControls) {
        this.hostControls = hostControls;
    }

    public int getNewGame() {
        return newGame;
    }

    public void setNewGame(int newGame) {
        this.newGame = newGame;
    }

    public int getEndGame() {
        return endGame;
    }

    public void setEndGame(int endGame) {
        this.endGame = endGame;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpponentControls() {
        return opponentControls;
    }

    public void setOpponentControls(String opponentControls) {
        this.opponentControls = opponentControls;
    }
} //end of class