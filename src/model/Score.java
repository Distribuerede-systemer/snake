package model;

/**
 * Created by nicolaiostergaard on 12/10/15.
 */


// Creates class Score
public class Score {

    // Declare highScore
    private int id;
    private User user;
    private int opponent;
    private Game game;
    private int score;

    //TODO: Kan slettes?! Bruges ikke i wrapper længere.
    // Creates constructor
    public Score(int id, User user, Game game, int opponent, int score)
    {
        this.id = id;
        this.user = user;
        this.game = game;
        this.opponent = opponent;
        this.score = score;
    }

    public Score(){}

    // Creates get method which returns the highScore

    public int getOpponent() { return opponent; }

    public void setOpponent(int opponent) { this.opponent = opponent;  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
