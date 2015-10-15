package model;

/**
 * Created by nicolaiostergaard on 12/10/15.
 */


// Creates class Score
public class Score {

    // Declare highScore
    private int id;
    private int userId;
    private int hostId;
    private int gameId;
    private int score;

    // Creates constructor
    public Score(int id, int userId, int gameId, int hostId, int score)
    {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.hostId = hostId;
        this.score = score;
    }

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

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
