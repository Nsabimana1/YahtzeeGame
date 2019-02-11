package com.example.yahtzeegame.backend;

import android.widget.Button;

public class ScoreButton {
    private ScoreCategory category;
    private Button button;
    private int value;
    private boolean isScored;
    public ScoreButton(Button scorebutton, ScoreCategory category){
        this.category = category;
        this.button = scorebutton;
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
    }
}
