package model;

import java.util.Date;

/**
 * Created by Peter on 12-10-2015.
 */

public class Game {


    //Creating the variables needed for the game
    private int id;
    private User winner;
    private String name;
    private String hostControls;
    private User host;
    private User opponent;
    private String opponentControls;
    private String status;
    private Date created;

    //Creating the constructor and initiating the variables
    public Game( int id, User winner, String hostControls, Date created, String name, User host, User
            opponent, String status){
        this.id = id;
        this.winner = winner;
        this.created = created;
        this.hostControls = hostControls;
        this.name = name;
        this.host = host;
        this.opponent = opponent;
        this.status = status;
    }

    //Creating the constructor and initiating the variables
    public Game(String hostControls, String name, User host, User
            opponent, String status){
        this.hostControls = hostControls;
        this.name = name;
        this.host = host;
        this.opponent = opponent;
        this.status = status;
    }



    public Game(){}

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //methods that the API can use
    //Creating get/set method for all the variables, so they can be used by other classes
    public int getId() {
        return id;
    }

    public void setGameId(int id) {
        this.id = id;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public String getHostControls() {
        return hostControls;
    }

    public void setHostControls(String hostControls) {
        this.hostControls = hostControls;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public User getOpponent() {
        return opponent;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getOpponentControls() {
        return opponentControls;
    }

    public void setOpponentControls(String opponentControls) {
        this.opponentControls = opponentControls;
    }
} //end of class
