package com.example.yahtzeegame.backend;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

public class Dice {
    private int value;
    private boolean selected;
    private Button buttonDice;

    public Dice(Button buttonDice){
        this.value = 1;
        this.selected = false;
        this.buttonDice = buttonDice;
    }

    public Dice(Integer value) {
        this.value = value;

    }

    public void Roll(){
        this.value = (int )(Math. random() * 6 + 1);
        Log.i("Dice", "Rolled a " + this.value);
        this.buttonDice.setText(Integer.toString(this.value));
    }
    public int getValue(){
        return this.value;
    }

    public void selectDice(){
        this.buttonDice.setBackgroundColor(0xff99cc00);
        this.selected = true;
    }

        public void resetDice(){
        this.buttonDice.setBackgroundColor(0xFFFFFFFF);
        this.value = 0;
        this.buttonDice.setText("0");
        this.selected = false;
    }

    public boolean isSelected(){
        return this.selected == true;
    }
    public Button getButtonDice() {
        return buttonDice;
    }
}
