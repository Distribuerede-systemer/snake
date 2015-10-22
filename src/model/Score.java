package model;

/**
 * Created by nicolaiostergaard on 12/10/15.
 */


// Creates class Score
public class Score {

    // Declare highScore
    private int id;
    private int userId;
    private int opponentId;
    private int gameId;
    private int score;

    //TODO: Kan slettes?! Bruges ikke i wrapper længere.
    // Creates constructor
    public Score(int id, int userId, int gameId, int opponentId, int score)
    {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.opponentId = opponentId;
        this.score = score;
    }

    public Score(){}

    // Creates get method which returns the highScore


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }





    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
