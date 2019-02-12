package com.example.yahtzeegame;

import com.example.yahtzeegame.backend.Dice;
import com.example.yahtzeegame.backend.ScoreCategory;
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

    ScoreValues scoreValues = new ScoreValues();
    ArrayList<Dice> dices = new ArrayList<>();

    public void loadDice(int... values) {
        for (int value: values) {
            dices.add(new Dice(value));
        }
    }

    @Test
    public void straightScoreIsCorrect(){
        loadDice(1, 2, 3, 4, 5);
        scoreValues.calculateScore(dices);
        assertTrue(scoreValues.scoreStraight(1));
    }

    @Test
    public void straightScoreIsNotCorrect(){
        loadDice(1, 1, 3, 4, 5);
        scoreValues.calculateScore(dices);
        assertFalse(scoreValues.scoreStraight(1));
    }

    @Test
    public void diceTotalTest1() {
        loadDice(1, 1, 1, 4, 4);
        scoreValues.calculateScore(dices);
        scoreValues.assignCategoryValues();
        for (ScoreCategory sc: ScoreCategory.values()) {
            if (sc.equals(ScoreCategory.Aces)) {
                assertEquals(3, scoreValues.getScoreFor(sc));
            } else if (sc.equals(ScoreCategory.Fours)) {
                assertEquals(8, scoreValues.getScoreFor(sc));
            } else if (sc.equals(ScoreCategory.Chance)) {
                assertEquals(11, scoreValues.getScoreFor(sc));
            } else {
                assertEquals(0, scoreValues.getScoreFor(sc));
            }
        }
    }
}