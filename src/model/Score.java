package model;

import com.google.gson.Gson;

/**
 * Created by nicolaiostergaard on 12/10/15.
 */


// Creates class Score
public class Score {

    // Declare highScore
    int highScore;

    // Creates constructor
    public Score(int _highScore)
    {
        highScore = _highScore;
    }

    // Creates get method which returns the highScore
    public int getHighScore()
    {
        return highScore;
    }
    
}
