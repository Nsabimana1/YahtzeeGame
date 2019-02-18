package com.example.yahtzeegame.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ScoreValues {
    HashMap<Integer, Integer> diceToScoreCount;
    HashMap<ScoreCategory, Integer> categoryToValue;
    ArrayList<ScoreButton> allScoreButtons;

    public ScoreValues(){
        this.diceToScoreCount = new HashMap<>();
        this.categoryToValue = new HashMap<>();
    }

    public void setAllScoreButtons(ArrayList<ScoreButton> scoreButtons){
        this.allScoreButtons = scoreButtons;
    }

   public void calculateScore(ArrayList<Dice> allDices){
        for(int i = 1; i <= 6; i++){
            this.diceToScoreCount.put(i, ScoreValues.getScore(allDices, i));
        }
   }

   public static int getScore(ArrayList<Dice> allDices, int num ){
       int score = 0;
       for(Dice d: allDices){
           if(d.getValue() == num){
               score += 1;
           }
       }
       return score ;
   }

   public int getScoreFor(ScoreCategory sc) {
        return categoryToValue.get(sc);
   }

   public ScoreButton getScoreButtonByScoreCategory(ScoreCategory category){
        ScoreButton toReturn = null;
        for(ScoreButton b: this.allScoreButtons){
            if(b.getCategory().equals(category)){
                toReturn = b;
                break;
            }
        }
        return toReturn;
   }

   public void assignCategoryValues(){
        for(Integer e: this.diceToScoreCount.keySet()){
            if(e == 1 && !this.getScoreButtonByScoreCategory(ScoreCategory.Aces).isScored()){
                this.categoryToValue.put(ScoreCategory.Aces, this.diceToScoreCount.get(e) * e);
            }else if(e == 2 && !this.getScoreButtonByScoreCategory(ScoreCategory.Twos).isScored()){
                this.categoryToValue.put(ScoreCategory.Twos, this.diceToScoreCount.get(e) * e);
            }
            else if(e == 3 && !this.getScoreButtonByScoreCategory(ScoreCategory.Threes).isScored()){
                this.categoryToValue.put(ScoreCategory.Threes, this.diceToScoreCount.get(e) * e);
            }
            else if(e == 4 && !this.getScoreButtonByScoreCategory(ScoreCategory.Fours).isScored()){
                this.categoryToValue.put(ScoreCategory.Fours, this.diceToScoreCount.get(e) * e);
            }
            else if(e == 5 && !this.getScoreButtonByScoreCategory(ScoreCategory.Fives).isScored()){
                this.categoryToValue.put(ScoreCategory.Fives, this.diceToScoreCount.get(e) * e);
            }
            else if(e == 6 && !this.getScoreButtonByScoreCategory(ScoreCategory.Sixes).isScored()){
                this.categoryToValue.put(ScoreCategory.Sixes, this.diceToScoreCount.get(e) * e);
            }
        }
        if(!this.getScoreButtonByScoreCategory(ScoreCategory.Three_of_a_kind).isScored()){
            this.categoryToValue.put(ScoreCategory.Three_of_a_kind, this.getNumOfaKind(3) * 3);
        }

       if(!this.getScoreButtonByScoreCategory(ScoreCategory.Four_of_a_kind).isScored()){
           this.categoryToValue.put(ScoreCategory.Four_of_a_kind, this.getNumOfaKind(4) * 4);
       }

       if(!this.getScoreButtonByScoreCategory(ScoreCategory.Yahtzee).isScored()){
           this.categoryToValue.put(ScoreCategory.Yahtzee, this.getNumOfaKind(5) > 0 ? 50:0);
       }else if(this.getScoreButtonByScoreCategory(ScoreCategory.Yahtzee).isScored() && this.getNumOfaKind(5) > 0){
           int newScore = this.categoryToValue.get(ScoreCategory.Yahtzee) + 100;
           this.categoryToValue.put(ScoreCategory.Yahtzee, newScore);
       }

       if(!this.getScoreButtonByScoreCategory(ScoreCategory.Chance).isScored()){
           this.categoryToValue.put(ScoreCategory.Chance, this.ScoreAChance());
       }
       if(!this.getScoreButtonByScoreCategory(ScoreCategory.SM_Straight).isScored()){
           this.categoryToValue.put(ScoreCategory.SM_Straight, this.scoreStraight(3) ? 30:0);
       }

       if(!this.getScoreButtonByScoreCategory(ScoreCategory.LG_Straight).isScored()){
           this.categoryToValue.put(ScoreCategory.LG_Straight, this.scoreStraight(4) ? 40:0);
       }

       if(!this.getScoreButtonByScoreCategory(ScoreCategory.Full_House).isScored()){
           if(this.getNumOfaKind(3) != 0 && this.getNumOfaKind(2) != 0 && this.getNumOfaKind(3) != this.getNumOfaKind(2)){
               this.categoryToValue.put(ScoreCategory.Full_House, 25);
           }else {
               this.categoryToValue.put(ScoreCategory.Full_House, 0);
           }
       }
   }


   public Integer getNumOfaKind(int num){
        for(Integer e: this.diceToScoreCount.keySet()){
            if (this.diceToScoreCount.get(e) == num){
                return e;
            }
        }
        return 0;
   }

   public Integer ScoreAChance(){
        int tatal = 0;
       for(Integer e: this.diceToScoreCount.keySet()){
           tatal += e * this.diceToScoreCount.get(e);
           }
       return tatal;
   }

   public ArrayList<Integer> getSortedArrayOFDiceForStraightScoring(){
       ArrayList<Integer> dices  = new ArrayList<>();
       for(Integer d: this.diceToScoreCount.keySet()){
           if(diceToScoreCount.get(d) > 0){
               dices.add(d);
           }
       }
       Collections.sort(dices);
       return dices;
   }

   public boolean scoreStraight(int val){
        ArrayList<Integer> dices = this.getSortedArrayOFDiceForStraightScoring();
        int count = 0;
        for(int i = 0; i < dices.size() - 1; i ++){
            if(dices.get(i) + 1 == dices.get(i + 1)){
                count += 1;
            }else{
                count = 0;
            }
        }
        if (count == val){
            return true;
        }else {
            return false;
        }
   }

   public HashMap<Integer, Integer> getDiceToScoreCount(){
        return this.diceToScoreCount;
   }

   public Integer getInitialSum(){
        int initialSum = 0;
        for (ScoreCategory sc: ScoreCategory.values()) {
            if (ScoreCategory.getCategoryValue(sc) > 0) {
                initialSum += this.categoryToValue.get(sc);
            }
        }
        return initialSum;
   }

   public Integer getBonusScore(){
        return this.getInitialSum() >= 63 ? 35 : 0;
   }

   public Integer getTotalScore(){
        this.assignCategoryValues();
        int totalSum = 0;
        for(ScoreCategory c: this.categoryToValue.keySet()){
            totalSum += this.categoryToValue.get(c);
        }
        return totalSum + this.getBonusScore();
   }

   public HashMap<ScoreCategory, Integer> getCategoryToValueMap(){
        return this.categoryToValue;
   }
}
