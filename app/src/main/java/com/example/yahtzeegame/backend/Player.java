package com.example.yahtzeegame.backend;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Player {
    private ArrayList<Dice> allDices;
    private int totalScore;
    private int bonusScore;
    private int initialSumScore;
    private ScoreValues scoreValues;
    private ArrayList<ScoreButton> allscoreButtons;
    private TextView initialsumView;
    private TextView bonusView;
    private TextView totalScoreView;

    public Player(ArrayList<Dice> allDices){
        this.totalScore = 0;
        this.allDices = allDices ;
        this.allscoreButtons = new ArrayList<>();
        this.scoreValues = new ScoreValues();
        this.bonusScore = 0;
        this.initialSumScore = 0;
        this.totalScore = 0;
    }


    public void setBonusView(TextView bonusView){
        this.bonusView = bonusView;
    }

    public void setSumView(TextView initialSumView){
        this.initialsumView = initialSumView;
    }

    public void setTotalScoreView(TextView totalScoreView){
        this.totalScoreView = totalScoreView;
    }

    public void setTotalScore(int totalScore){
        this.totalScore = totalScore;
    }

    public TextView getInitialsumView(){
        return this.initialsumView;
    }

    public TextView getBonusView(){
        return this.bonusView;
    }

    public TextView getTotalScoreView(){
        return this.totalScoreView;
    }

    public void setBonusScore(int bonusScore){
        this.bonusScore = bonusScore;
    }

    public void setInitialSumScore(int initialSumScore){
        this.initialSumScore = initialSumScore;
    }

    public ArrayList<ScoreButton> getAllScoreButtons(){
        return this.allscoreButtons;
}

    public void addScoreButton(Button scoreButton, ScoreCategory category){
        this.allscoreButtons.add(new ScoreButton(scoreButton, category));
    }

    public ScoreValues getScoreValues(){
        return this.scoreValues;
    }

    public void displayResult(){
        this.scoreValues.calculateScore(this.allDices);
        this.scoreValues.assignCategoryValues();
        HashMap<ScoreCategory, Integer> categoryIntegerHashMap = this.scoreValues.getCategoryToValueMap();
        this.assignTotalScores();

        for(ScoreButton b: this.allscoreButtons) {
            ScoreCategory category = b.getCategory();
            if(categoryIntegerHashMap.containsKey(category)) {
                b.getButton().setText(categoryIntegerHashMap.get(category).toString());
            }
        }

        this.initialsumView.setText(scoreValues.getInitialSum().toString());
        this.bonusView.setText(scoreValues.getBonusScore().toString());
        this.totalScoreView.setText(scoreValues.getTotalScore().toString());
    }

    public Integer getInitialSum(){
        return this.initialSumScore;
    }

    public Integer getBonusScore(){
        return this.bonusScore;
    }

    public Integer getTotalScore(){
        return this.totalScore;
    }


    public void assignScoreButtonsToScoreValues(){
        this.scoreValues.setAllScoreButtons(this.allscoreButtons);
    }


    public void assignTotalScores(){
        this.initialSumScore = scoreValues.getInitialSum();
        this.bonusScore = scoreValues.getBonusScore();
        this.totalScore = scoreValues.getTotalScore();
    }

}
