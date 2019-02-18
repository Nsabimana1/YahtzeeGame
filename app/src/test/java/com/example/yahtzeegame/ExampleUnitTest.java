package com.example.yahtzeegame;

import com.example.yahtzeegame.backend.Dice;
import com.example.yahtzeegame.backend.ScoreButton;
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
    ArrayList<ScoreButton> scoreButtonsForTesting = new ArrayList<>();

    public void loadDice(int... values) {
        for (int value: values) {
            dices.add(new Dice(value));
        }
    }

    public void loadScoreButtons(){
        for (ScoreCategory category: ScoreCategory.values()){
            scoreButtonsForTesting.add(new ScoreButton(category));
        }
    }

    @Test
    public void smallStraightScoreIsCorrect(){
        loadDice(3, 2, 3, 4, 5);
        scoreValues.calculateScore(dices);
        assertTrue(scoreValues.scoreStraight(3));
    }

    @Test
    public void smallStraightScoreIsNotCorrect(){
        loadDice(1, 2, 3, 6, 5);
        scoreValues.calculateScore(dices);
        System.out.print(scoreValues.getDiceToScoreCount());
        assertFalse(scoreValues.scoreStraight(3));
    }

    @Test
    public void largeStraightScoreIsCorrect(){
        loadDice(1, 2, 3, 4, 5);
        scoreValues.calculateScore(dices);
        System.out.print(scoreValues.getDiceToScoreCount());
        assertTrue(scoreValues.scoreStraight(4));
    }

    @Test
    public void largeStraightScoreIsNotCorrect(){
        loadDice(1, 2, 3, 4, 6);
        scoreValues.calculateScore(dices);
        System.out.print(scoreValues.getDiceToScoreCount());
        System.out.print(scoreValues.scoreStraight(4));
        assertFalse(scoreValues.scoreStraight(4));
    }

    @Test
    public void scoreChanceIsCorrect(){
        loadDice(1, 2, 1, 3, 4);
        this.loadScoreButtons();
        scoreValues.setAllScoreButtons(this.scoreButtonsForTesting);
        scoreValues.calculateScore(dices);
        scoreValues.assignCategoryValues();
        int chance = scoreValues.ScoreAChance();
        assertEquals(chance, 11);
    }

    @Test
    public void scoreChanceIsNotCorrect(){
        loadDice(1, 2, 1, 3, 4);
        this.loadScoreButtons();
        scoreValues.setAllScoreButtons(this.scoreButtonsForTesting);
        scoreValues.calculateScore(dices);
        scoreValues.assignCategoryValues();
        int chance = scoreValues.ScoreAChance();
        assertNotEquals(chance, 13);
    }


    @Test
    public void getNumOfaKindIsCorrect(){
        loadDice(2, 2, 2, 2, 4);
        this.loadScoreButtons();
        scoreValues.setAllScoreButtons(this.scoreButtonsForTesting);
        scoreValues.calculateScore(dices);
        scoreValues.assignCategoryValues();
        int numofaKind = scoreValues.getNumOfaKind(4);
        assertEquals(numofaKind, 2);
    }

    @Test
    public void getNumOfaKindIsNotCorrect(){
        loadDice(2, 1, 5, 2, 4);
        this.loadScoreButtons();
        scoreValues.setAllScoreButtons(this.scoreButtonsForTesting);
        scoreValues.calculateScore(dices);
        scoreValues.assignCategoryValues();
        int numofaKind = scoreValues.getNumOfaKind(4);
        assertNotEquals(numofaKind, 2);
    }


    @Test
    public void diceTotalWithCategoryScores() {
        loadDice(1, 1, 1, 4, 4);
        this.loadScoreButtons();
        scoreValues.setAllScoreButtons(this.scoreButtonsForTesting);
        scoreValues.calculateScore(dices);
        scoreValues.assignCategoryValues();
        for (ScoreCategory sc: ScoreCategory.values()) {
            if (sc.equals(ScoreCategory.Aces)) {
                assertEquals(3, scoreValues.getScoreFor(sc));
            } else if (sc.equals(ScoreCategory.Fours)) {
                assertEquals(8, scoreValues.getScoreFor(sc));
            } else if (sc.equals(ScoreCategory.Chance)) {
                assertEquals(11, scoreValues.getScoreFor(sc));
            } else if (sc.equals(ScoreCategory.Three_of_a_kind)) {
                assertEquals(3, scoreValues.getScoreFor(sc));
            }else if (sc.equals(ScoreCategory.Full_House)) {
                assertEquals(25, scoreValues.getScoreFor(sc));
            }else {
                assertEquals(0, scoreValues.getScoreFor(sc));
            }
        }
    }

    @Test
    public void YahtzeeBonusCategoryIsCorrect(){
        loadDice(1, 1, 1, 1, 1);
        this.loadScoreButtons();
        scoreValues.setAllScoreButtons(this.scoreButtonsForTesting);
        scoreValues.calculateScore(dices);
        scoreValues.assignCategoryValues();
        for (ScoreCategory sc: ScoreCategory.values()) {
            if (sc.equals(ScoreCategory.Yahtzee)) {
                assertEquals(50, scoreValues.getScoreFor(sc));
            }
        }

        this.scoreValues.getScoreButtonByScoreCategory(ScoreCategory.Yahtzee).setScored(true);

        loadDice(2, 2, 2, 2, 2);
        this.loadScoreButtons();
        scoreValues.calculateScore(dices);
        scoreValues.assignCategoryValues();
        for (ScoreCategory sc: ScoreCategory.values()) {
            if (sc.equals(ScoreCategory.Yahtzee)) {
                assertEquals(150, scoreValues.getScoreFor(sc));
            }
        }
    }
}