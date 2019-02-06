package com.example.yahtzeegame.backend;

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

    public void unselectDice(){
        this.buttonDice.setBackgroundColor(0x00000000);
        this.selected = false;
    }

    public boolean isSelected(){
        return this.selected == true;
    }
    public Button getButtonDice() {
        return buttonDice;
    }
}
