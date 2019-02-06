package com.example.yahtzeegame.backend;

import java.util.ArrayList;
import java.util.HashSet;

public class Player {
    private ArrayList<Dice> allDices;
    private HashSet<Dice> selectedDices;
    private int score;
    public void Player(ArrayList<Dice> allDices){
        this.score = 0;
        this.allDices = allDices ;
        this.selectedDices = new HashSet<>();
    }

    public int getScore() {
        return score;
    }

    public void addSelectedDice(Dice dice){
        this.selectedDices.add(dice);
    }
}
