package com.example.yahtzeegame.backend;

import android.util.Log;
import android.widget.Button;

public class Dice {
    private int value;
    private boolean selected;
    private Button buttonDice;

    public Dice(Button buttonDice){
        this.value = 0;
        this.selected = false;
        this.buttonDice = buttonDice;
    }

    // for testing purpose
    public Dice(Integer value) {
        this.value = value;
    }

    public void Roll(){
        this.value = (int )(Math. random() * 6 + 1);
        this.buttonDice.setText(Integer.toString(this.value));
    }
    public int getValue(){
        return this.value;
    }

    public void selectDice(){
        this.buttonDice.setBackgroundColor(0xff99cc00);
        this.selected = true;
    }

    public void deselectDice(){
        this.buttonDice.setBackgroundColor(0xFFFFFFFF);
        this.selected = false;
    }

    public void resetDice(){
        this.buttonDice.setBackgroundColor(0xFFFFFFFF);
        this.value = 0;
        this.buttonDice.setText("0");
        this.selected = false;
    }

    public void disActivateDice(){
        this.buttonDice.setEnabled(false);
    }

    public void activateDice(){
        this.buttonDice.setEnabled(true);
    }

    public boolean isSelected(){
        return this.selected == true;
    }
    public Button getButtonDice() {
        return buttonDice;
    }
}
