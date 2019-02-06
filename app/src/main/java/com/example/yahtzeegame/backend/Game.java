package com.example.yahtzeegame.backend;

import android.widget.Button;
import java.util.ArrayList;


public class Game {
    private ArrayList<Dice> alldices = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    public Game(){
    }

    public void addDiceButton(Button dice) {
        alldices.add(new Dice(dice));
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void roll(){
        for(Dice d:alldices){
            if(!d.isSelected()){
                d.Roll();
            }
        }
    }

    public Dice getDice(Button dice){
        Dice temPdice = null;
        for(Dice d: alldices){
            if (d.getButtonDice().equals(dice)){
                temPdice =  d;
            }
        }
        return temPdice;
    }

    public void deleteAllPlayers(){
        players.clear();
    }

    public void startGame(){
//        for (Player p: this.players){
//            while (p.getScore() != 100){
//                roll.setOnClickListener( (V) -> );
//            }
//        }
    }
}
