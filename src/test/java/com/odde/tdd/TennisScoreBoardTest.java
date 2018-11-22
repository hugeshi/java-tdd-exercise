package com.odde.tdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TennisScoreBoardTest {
    private TennisScoreBoard tennisScoreBoard = new TennisScoreBoard("hujshi","mike");

    public void setRightScore(int rightScore) {
        for (int i = 0; i < rightScore;i++) {
            tennisScoreBoard.rightAdd();
        }
    }
    public void setLeftScore(int leftScore) {
        for (int i = 0; i < leftScore;i++) {
            tennisScoreBoard.leftAdd();
        }
    }
    @Test
    public void testLoveAll(){
        assertEquals("LOVE ALL", tennisScoreBoard.getScore());
    }


    @Test
    public void testFifteenLove(){
        setLeftScore(1);
        assertEquals("FIFTEEN LOVE", tennisScoreBoard.getScore());
    }

    @Test
    public void testLoveFifteen(){
        setRightScore(1);
        assertEquals("LOVE FIFTEEN", tennisScoreBoard.getScore());
    }

    @Test
    public void testFifteenAll(){
        setLeftScore(1);
        setRightScore(1);
        assertEquals("FIFTEEN ALL", tennisScoreBoard.getScore());
    }

    @Test
    public void testThirtyLove(){
        setLeftScore(2);
        assertEquals("THIRTY LOVE", tennisScoreBoard.getScore());
    }

    @Test
    public void testFortyLove(){
        setLeftScore(3);
        assertEquals("FORTY LOVE", tennisScoreBoard.getScore());
    }

    @Test
    public void testDeuce() {
        setLeftScore(3);
        setRightScore(3);
        assertEquals("DEUCE", tennisScoreBoard.getScore());
    }

    @Test
    public void testRightAdvance() {
        setLeftScore(3);
        setRightScore(4);
        assertEquals("mike ADVANCE", tennisScoreBoard.getScore());
    }

    @Test
    public void testLeftAdvance() {
        setLeftScore(3);
        setRightScore(3);
        setLeftScore(1);

        assertEquals("hujshi ADVANCE", tennisScoreBoard.getScore());
    }

    @Test
    public void testAdvanceToDeuce() {
        setLeftScore(3);
        setRightScore(3);
        setLeftScore(1);
        setRightScore(1);
        assertEquals("DEUCE", tennisScoreBoard.getScore());
    }

    @Test
    public void testLeftWin(){
        setLeftScore(4);
        assertEquals("hujshi WIN", tennisScoreBoard.getScore());
    }
    @Test
    public void TestLeftAdvanceToWin(){
        setLeftScore(3);
        setRightScore(3);
        setLeftScore(1);
        setRightScore(1);
        setLeftScore(2);
        assertEquals("hujshi WIN", tennisScoreBoard.getScore());
    }

}
