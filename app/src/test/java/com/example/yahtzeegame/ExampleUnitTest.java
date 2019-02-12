package com.example.yahtzeegame;

import com.example.yahtzeegame.backend.ScoreValues;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    ScoreValues scoreValues = new ScoreValues();
    ArrayList<Integer> dices = new ArrayList<Integer>();

    @Test
    public void straightScoreIsCorrect(){
        dices.add(1);
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(5);
        int result = scoreValues.scoreStraight(1, dices);
        assertEquals(result, 5);
    }
}