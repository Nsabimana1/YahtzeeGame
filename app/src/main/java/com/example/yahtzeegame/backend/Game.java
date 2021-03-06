package com.example.yahtzeegame.backend;

import android.widget.Button;
import java.util.ArrayList;


public class Game {
    private ArrayList<Dice> allDices = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;

    public Game(){
    }

    public void addDiceButton(Button dice) {
        allDices.add(new Dice(dice));
    }

    public void roll(){
        this.activateAllDices();
        for(Dice d: allDices){
            if(!d.isSelected()){
                d.Roll();
            }
        }
        this.activateScoreButtons(this.currentPlayer);
        this.currentPlayer.getScoreValues().calculateScore(this.allDices);
        this.currentPlayer.displayResult();
    }

    public void setInitialPlayer() {
        this.disActivateAllDices();
        this.currentPlayer = this.players.get(0);
        for(Player p: this.players){
            this.deactivateScoreButtons(p);
            p.getInitialSumView().setText("0");
            p.getBonusView().setText("0");
            p.getTotalScoreView().setText("0");

        }
    }

    public void switchPlayer(){
        this.disActivateAllDices();
        if (players.size() == 1){
            this.currentPlayer = players.get(0);
            this.deactivateScoreButtons(this.currentPlayer);
        }else {
            if(this.currentPlayer.equals(players.get(0))) {
                this.currentPlayer = players.get(1);
                this.deactivateScoreButtons(players.get(1));
                this.deactivateScoreButtons(players.get(0));
                this.currentPlayer.displayResult();
            } else {
                this.currentPlayer = players.get(0);
                this.deactivateScoreButtons(players.get(0));
                this.deactivateScoreButtons(players.get(1));
                this.currentPlayer.displayResult();
            }
        }
    }



    public void deactivateScoreButtons(Player player){
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
        Dice tempDice = null;
        for(Dice d: allDices){
            if (d.getButtonDice().equals(dice)){
                tempDice =  d;
            }
        }
        return tempDice;
    }

    public void deleteAllPlayers(){
        players.clear();
    }

    public void resetAllDices(){
        for(Dice d: allDices){
            d.resetDice();
        }
    }

    public void initializePlayers(int num){
        for(int i = 0; i <num; i ++){
            this.players.add(new Player(this.allDices));
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

    public void activateAllDices(){
        for(Dice d: this.allDices){
            d.activateDice();
        }
    }

    public void disActivateAllDices(){
        for(Dice d: this.allDices){
            d.disActivateDice();
        }
    }

    public void restAllScoreButtons(){
        for(Player p: this.players){
            for(ScoreButton b: p.getAllScoreButtons()){
                b.resetScoreButton();
            }
        }
    }

    public boolean checkGameOver(){
        for(Player p: this.players){
            for(ScoreButton b: p.getAllScoreButtons()){
                if(!b.isScored()){
                    return false;
                }
            }
        }
        return true;
    }

}
