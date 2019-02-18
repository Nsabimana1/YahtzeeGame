package com.example.yahtzeegame.backend;

import android.widget.Button;

public class ScoreButton {
    private ScoreCategory category;
    private Button button;
    private int value;
    private boolean isScored;

    public ScoreButton(Button scoreButton, ScoreCategory category){
        this.category = category;
        this.button = scoreButton;
        this.value = 0;
        this.isScored = false;
    }

    // for testing purpose
    public ScoreButton(ScoreCategory category){
        this.category = category;
        this.value = 0;
        this.isScored = false;
    }

    public Button getButton() {
        return button;
    }

    public boolean isScored() {
        return isScored == true;
    }

    public int getValue() {
        return value;
    }

    // For testing purpose
    public void setScored(boolean value){
        this.isScored = value;
    }

    public void markScored(){
        this.isScored = true;
        this.button.setBackgroundColor(0xff99cc00);
    }

    public ScoreCategory getCategory() {
        return category;
    }

    public void resetScoreButton(){
        this.isScored = false;
        this.button.setBackgroundColor(0xFFFFFFFF);
        this.button.setText("0");
        this.button.setEnabled(true);
    }
}
