package com.odde.tdd;

import java.util.HashMap;

public class TennisScoreBoard {


    private final String rightPlayer;
    private final String leftPlayer;
    int leftPlayerScore = 0;
    int rightPlayerScore = 0;
    String[] scoreArray = {"LOVE", "FIFTEEN", "THIRTY", "FORTY"};

    public TennisScoreBoard(String hujshi, String mike) {
        rightPlayer = mike;
        leftPlayer = hujshi;
    }

    public String getScore() {

        if(leftPlayerScore > rightPlayerScore + 1 && leftPlayerScore >= 4){
            return leftPlayer + " WIN" ;
        }

        if (leftPlayerScore == rightPlayerScore && leftPlayerScore >= 3) {
            return "DEUCE";
        }

        if(leftPlayerScore == rightPlayerScore) {
            return scoreArray[leftPlayerScore] +" ALL";
        }

        if (rightPlayerScore >= 3 && leftPlayerScore >= 3 && leftPlayerScore > rightPlayerScore) {
            return leftPlayer + " ADVANCE";
        }

        if (rightPlayerScore >= 3 && leftPlayerScore >= 3 && rightPlayerScore > leftPlayerScore) {
            return rightPlayer + " ADVANCE";
        }


        return scoreArray[leftPlayerScore] + " " + scoreArray[rightPlayerScore];
    }

    public void leftAdd() {
        leftPlayerScore ++;
    }

    public void rightAdd() {
        rightPlayerScore ++;
    }
}
