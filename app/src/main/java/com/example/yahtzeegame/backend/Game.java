package com.example.yahtzeegame.backend;

import android.widget.Button;
import java.util.ArrayList;


public class Game {
    private ArrayList<Dice> alldices = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;

    public Game(){
    }

    public void addDiceButton(Button dice) {
        alldices.add(new Dice(dice));
    }

    public void roll(){
        for(Dice d:alldices){
            if(!d.isSelected()){
                d.Roll();
            }
        }
        this.currentPlayer.getScoreValues().calculateScore(this.alldices);
        this.currentPlayer.displayResult();
    }

    public void setInitialPlayer() {
        this.currentPlayer = this.players.get(0);
        if(players.size() > 1){
            this.disactivateScoreButtons(players.get(1));
        }
    }

    public void switchPlayer(){
        if (players.size() == 1){
            this.currentPlayer = players.get(0);
            this.currentPlayer.displayResult();
        }else {
            if(this.currentPlayer.equals(players.get(0))) {
                this.currentPlayer = players.get(1);
                this.activateScoreButtons(players.get(1));
                this.disactivateScoreButtons(players.get(0));
                this.currentPlayer.displayResult();
            } else {
                this.currentPlayer = players.get(0);
                this.activateScoreButtons(players.get(0));
                this.disactivateScoreButtons(players.get(1));
                this.currentPlayer.displayResult();
            }
        }
    }

    public void disactivateScoreButtons(Player player){
        for(ScoreButton b: player.getAllScoreButtons()){
            b.getButton().setEnabled(false);
        }
    }

    public void activateScoreButtons(Player player){
        for(ScoreButton b: player.getAllScoreButtons()){
            if(!b.isScored()) {
                b.getButton().setEnabled(true);
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

    public void resetAllDices(){
        for(Dice d: alldices){
            d.resetDice();
        }
    }

    public void initializePlayers(int num){
        for(int i = 0; i <num; i ++){
            this.players.add(new Player(this.alldices));
        }

        for(int i = 0; i <num; i ++){
            this.players.get(i).assignScoreButtonsToScoreValues();
        }
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public ScoreButton getCurrntScoreButton( Button clickedButton){
        ScoreButton toreturn = null;
        for(ScoreButton b: this.currentPlayer.getAllScoreButtons()){
            if(b.getButton().equals(clickedButton)){
                toreturn = b;
                break;
            }
        }
        return toreturn;
    }

    public void restAllScoreButtons(){
        for(Player p: this.players){
            for(ScoreButton b: p.getAllScoreButtons()){
                b.resetScoreButton();
            }
        }
    }
}
